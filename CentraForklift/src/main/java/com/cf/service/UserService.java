package com.cf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cf.dao.UserDao;
import com.cf.dao.impl.UserDaoImpl;
import com.cf.dto.UserDTO;
import com.cf.email.EmailBL;
import com.cf.entity.User;

@Service
public class UserService {

	public static Map<String, UserDTO> users = new HashMap<String, UserDTO>();

	static {
		UserDTO user = new UserDTO();
		user.setAddress("Trichy");
		user.setCompanyName("IBM");
		user.setEmailAddress("t.isaacjefferson@gmail.com");
		user.setFirstName("Isaac");
		user.setLastName("Jefferon");
		user.setPassword("12345");
		user.setPhoneNumber(90076274);
		user.setUserRole("admin");

		users.put(user.getEmailAddress(), user);

		user = new UserDTO();
		user.setAddress("Australia");
		user.setCompanyName("Hexaware");
		user.setEmailAddress("mani143@gmail.com");
		user.setFirstName("Mani");
		user.setLastName("vannan");
		user.setPassword("12345");
		user.setPhoneNumber(90076275);
		user.setUserRole("dealer");

		users.put(user.getEmailAddress(), user);

	}

	public UserDTO isValidUser(String userId, String pass) {
		System.out.println("User Id : " + userId);
		System.out.println("Password : " + pass);

		UserDao dao = new UserDaoImpl();
		User user = dao.getUser(userId);
		if(null != user && user.getPassword().equals(pass)) {
			UserDTO dto = new UserDTO();
			dto.setAddress(user.getAddress());
			dto.setCompanyName(user.getCompanyName());
			dto.setEmailAddress(user.getEmailAddress());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setPhoneNumber(user.getPhoneNumber());
			dto.setUserRole(user.getUserRole());
			return dto;
		}
		return null;
	}

	public boolean registerUser(UserDTO userDTO) {
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user.setAddress(userDTO.getAddress());
		user.setCompanyName(userDTO.getCompanyName());
		user.setEmailAddress(userDTO.getEmailAddress());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setUserRole(userDTO.getUserRole());
		
		return dao.saveUser(user);
	}

	public Map<String, Set<String>> getAllUsers(String userId) {
		UserDao dao = new UserDaoImpl();
		Map<String, Set<String>> userDetails = new HashMap<String, Set<String>>();
		User userEntity = dao.getUser(userId);
		List<User> userEntities = dao.getAllUsers();
		
		if (userEntity.getUserRole().equalsIgnoreCase("Admin")) {
			userEntities.stream().forEach(user -> {
				if (userDetails.get(user.getCompanyName()) == null) {
					Set<String> emailIds = new HashSet<String>();
					emailIds.add(user.getEmailAddress());
					userDetails.put(user.getCompanyName(), emailIds);
				} else {
					Set<String> emailIds = userDetails.get(user.getCompanyName());
					emailIds.add(user.getEmailAddress());
				}
			});
		} else if (userEntity.getUserRole().equalsIgnoreCase("Dealer")) {
			Set<String> emailIds = userDetails.get(userEntity.getCompanyName());
			if(emailIds != null) {
				emailIds.add(userEntity.getEmailAddress());
			}
			else {
				emailIds = new HashSet<String>();
				emailIds.add(userEntity.getEmailAddress());
			}
			userDetails.put(userEntity.getCompanyName(), emailIds);
		}

		return userDetails;
	}

	public void sendEmail(int invoiceId, String userId){
		System.out.println("Send E-mail -> Invoice Id : " + invoiceId);
		System.out.println("Send E-mail -> User Id : " + userId);
		EmailBL email = new EmailBL();
		try {
			email.getInvoiceHTMLContent(invoiceId);
			email.sendEmail(userId, "", invoiceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
