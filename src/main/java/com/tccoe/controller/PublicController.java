package com.tccoe.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

	
	@GetMapping("/public")
	public String home() {
		return "hey public";
	}
			
}
