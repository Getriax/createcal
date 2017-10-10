package com.demo.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import com.demo.rest.service.SecurityService;
import com.demo.rest.service.SecurityServiceImpl;
import com.demo.rest.service.UserService;
import com.demo.rest.service.UserServiceImpl;

@Configuration
@ComponentScan("com.demo.rest")
public class WebConfiguration {
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	@Bean 
	public SecurityService securityService() {
		return new SecurityServiceImpl();
	}
	
}
