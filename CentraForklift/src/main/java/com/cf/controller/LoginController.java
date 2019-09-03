package com.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cf.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService service;
	
	@RequestMapping("/validate")
	public int validateUser(@RequestParam("userName") String uName, @RequestParam("pass") String pass) {
		System.out.println("validateUser method Starts...");
		return service.isValidUser(uName, pass);
	}
}
