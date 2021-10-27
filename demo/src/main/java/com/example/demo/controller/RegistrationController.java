package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.model.User;
import com.example.demo.service.RegistrationService;

@RestController

@CrossOrigin
public class RegistrationController {
	
	@Bean
	public CorsFilter corsFilter() {
	   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   final CorsConfiguration config = new CorsConfiguration();
	   config.setAllowCredentials(true);
	   // Don't do this in production, use a proper list  of allowed origins
	   config.setAllowedOrigins(Collections.singletonList("*"));
	   config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
	   config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	   source.registerCorsConfiguration("/**", config);
	   return new CorsFilter(source);
	}
	  
	@Autowired
	private RegistrationService service;
	

	
	@PostMapping("/registeruser" )
	@CrossOrigin(origins = "http://localhost:4200/")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		if(tempEmailId!= null && "".equals(tempEmailId)) {
			User userobj= service.fetchUserByEmailId(tempEmailId);
			if(userobj != null) {
				throw new Exception("user with "+tempEmailId+"is already exist");
			}
		}
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception {
	String tempEmailId = user.getEmailId();
	String tempPass = user.getPassword();
	User userObj =null;
	if (tempEmailId!= null && tempPass !=null) {
		service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
	}
	if(userObj == null ) {
		throw new Exception("bad credentials");
	}
	return userObj;
}



}
