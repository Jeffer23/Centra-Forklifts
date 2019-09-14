package com.cf.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.dto.UserDTO;
import com.cf.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@RequestMapping("/login")
	public UserDTO validateUser(@RequestParam("userName") String uName, @RequestParam("pass") String pass) {
		System.out.println("validateUser method Starts...");
		return service.isValidUser(uName, pass);
	}
	
	@PostMapping("/register")
	public boolean registerUser(@RequestBody UserDTO user) {
		System.out.println("Register User : " + user);
		return service.registerUser(user);
	}
	
	@RequestMapping("/getAllUser")
	public Map<String, Set<String>> getAllUsers(@RequestParam("userId") String userId){
		return service.getAllUsers(userId);
	}
	
	@RequestMapping("/sendEmail")
	public void sendEmail(@RequestParam("invoiceId") int invoiceId, @RequestParam("userId") String userId){
		service.sendEmail(invoiceId, userId);
	}
}
