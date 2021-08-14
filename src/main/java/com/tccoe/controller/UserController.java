package com.tccoe.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/hello")
	public String getUser() {
		return "user 123 ";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user-greeting")
	public String getUserGreeting() {
		return "user good morning ";
	}
	
}