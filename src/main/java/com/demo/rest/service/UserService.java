package com.demo.rest.service;

import com.demo.rest.model.User;

public interface UserService{
	void save(User user);
	User findByUsername(String username);
}
