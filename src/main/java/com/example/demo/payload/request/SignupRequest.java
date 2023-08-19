package com.example.demo.payload.request;

import lombok.Data;

@Data
public class SignupRequest {

	private String name;
	private String username;
	private String email;
	private String password;
	private String role;
}
