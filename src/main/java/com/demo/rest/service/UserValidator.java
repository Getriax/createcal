package com.demo.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demo.rest.model.User;

@Service
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.equals(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
			errors.rejectValue("username", "Size.userForm.username");
		if (userService.findByUsername(user.getUsername()) != null)
			errors.rejectValue("username", "Duplicate.userForm.username");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
			errors.rejectValue("password", "Size.userForm.password");
		if (!user.getPassword().equals(user.getConfirmPassword()))
			errors.rejectValue("password", "Diff.userForm.password");
	}
	
}
