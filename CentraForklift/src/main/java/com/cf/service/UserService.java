package com.cf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cf.dto.UserDTO;


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
		user.setUserRole("Admin");
		
		users.put(user.getEmailAddress(), user);
		
		user = new UserDTO();
		user.setAddress("Australia");
		user.setCompanyName("Hexaware");
		user.setEmailAddress("mani143@gmail.com");
		user.setFirstName("Mani");
		user.setLastName("vannan");
		user.setPassword("12345");
		user.setPhoneNumber(90076275);
		user.setUserRole("Dealer");
		
		users.put(user.getEmailAddress(), user);
		
	}

	public String isValidUser(String uName, String pass) {
		System.out.println("User Name : " + uName);
		System.out.println("Password : " + pass);
		
		UserDTO user = users.get(uName);
		if(user == null) {
			return null;
		}
		
		return user.getEmailAddress();
	}
	
	public boolean registerUser(UserDTO user) {
		boolean userExists = users.keySet().stream().anyMatch(email->email.equalsIgnoreCase(user.getEmailAddress()));
		if(!userExists) {
			users.put(user.getEmailAddress(), user);
			return true;
		}
		return false;
	}
	
	public Map<String, Set<String>> getAllUsers(){
		Map<String, Set<String>> userDetails = new HashMap<String, Set<String>>();
		users.values().stream().forEach(user->{
			if(userDetails.get(user.getCompanyName()) == null) {
				Set<String> emailIds = new HashSet<String>();
				emailIds.add(user.getEmailAddress());
				userDetails.put(user.getCompanyName(), emailIds);
			}
			else {
				Set<String> emailIds = userDetails.get(user.getCompanyName());
				emailIds.add(user.getEmailAddress());
			}
		});
		return userDetails;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> values = new HashMap<Integer, String>();
		System.out.println(values.put(1, "ONE"));
		System.out.println(values.put(1, "ONE"));
	}
}
