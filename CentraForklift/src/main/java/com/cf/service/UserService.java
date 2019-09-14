package com.cf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cf.dto.UserDTO;
import com.cf.email.EmailBL;

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

	public UserDTO isValidUser(String uName, String pass) {
		System.out.println("User Name : " + uName);
		System.out.println("Password : " + pass);

		UserDTO user = users.get(uName);
		if (user == null) {
			return null;
		}

		return user;
	}

	public boolean registerUser(UserDTO user) {
		boolean userExists = users.keySet().stream().anyMatch(email -> email.equalsIgnoreCase(user.getEmailAddress()));
		if (!userExists) {
			users.put(user.getEmailAddress(), user);
			return true;
		}
		return false;
	}

	public Map<String, Set<String>> getAllUsers(String userId) {
		Map<String, Set<String>> userDetails = new HashMap<String, Set<String>>();
		UserDTO userDTO = users.get(userId);
		if (userDTO.getUserRole().equalsIgnoreCase("Admin")) {
			users.values().stream().forEach(user -> {
				if (userDetails.get(user.getCompanyName()) == null) {
					Set<String> emailIds = new HashSet<String>();
					emailIds.add(user.getEmailAddress());
					userDetails.put(user.getCompanyName(), emailIds);
				} else {
					Set<String> emailIds = userDetails.get(user.getCompanyName());
					emailIds.add(user.getEmailAddress());
				}
			});
		} else if (userDTO.getUserRole().equalsIgnoreCase("Dealer")) {
			Set<String> emailIds = userDetails.get(userDTO.getCompanyName());
			if(emailIds != null) {
				emailIds.add(userDTO.getEmailAddress());
			}
			else {
				emailIds = new HashSet<String>();
				emailIds.add(userDTO.getEmailAddress());
			}
			userDetails.put(userDTO.getCompanyName(), emailIds);
		}

		return userDetails;
	}

	public void sendEmail(int invoiceId, String userId){
		System.out.println("Send E-mail -> Invoice Id : " + invoiceId);
		System.out.println("Send E-mail -> User Id : " + userId);
		EmailBL email = new EmailBL();
		try {
			String messageBody = email.getInvoiceHTMLContent(invoiceId);
			email.sendEmail(userId, messageBody, invoiceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
