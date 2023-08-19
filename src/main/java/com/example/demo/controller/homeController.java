package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class homeController {

	@GetMapping
	private String index() {
		return "home";
	}
	
	@GetMapping("/user")
	private String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	private String admin() {
		return "admin";
	}
	
}
