package com.demo.rest.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.rest.dao.RoleRepository;
import com.demo.rest.dao.UserRepository;
import com.demo.rest.model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
		
	}
	
	
}
