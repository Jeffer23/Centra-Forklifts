package com.cf.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean isValidUser(String uName, String pass) {
		System.out.println("User Name : " + uName);
		System.out.println("Password : " + pass);
		return false;
	}
}
