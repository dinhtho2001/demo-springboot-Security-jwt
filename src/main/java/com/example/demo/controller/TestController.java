package com.example.demo.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.payload.repository.UserRepository;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.security.services.UserDetailsServiceImpl;

import io.swagger.annotations.AuthorizationScope;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	
	@GetMapping("/superadmin")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String moderatorAccess() {
		return "super admin Board.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@GetMapping("getdate")
	public String getDate() {
		long date = 24*60*60*1000;
		
		return ( new Date (System.currentTimeMillis()+date)) + 
				" and " + 
				new Date((new Date()).getTime() + jwtExpirationMs);

	}
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@GetMapping("/username/{username}")
	public UserDetails findByUsername(@PathVariable("username") String username) {
		UserDetails userDetails;
		userDetails = userDetailsServiceImpl.loadUserByUsername(username);
		return userDetails;
	}
}
