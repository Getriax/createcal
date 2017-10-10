package com.demo.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails)
			return ((UserDetails)userDetails).getUsername(); 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void autologin(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = userDetailService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		
		authenticationManager.authenticate(uPAT);
		
		if (uPAT.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(uPAT);
			logger.debug(String.format("User $s logged successfuly", username));
		}
	}
	
	
}
