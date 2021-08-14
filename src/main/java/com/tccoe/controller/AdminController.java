package com.tccoe.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/bye")
	public String getBye() {
		return "bye";
	}
	
	
	

}