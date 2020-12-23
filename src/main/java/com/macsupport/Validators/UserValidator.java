package com.macsupport.Validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.macsupport.Models.User;
import com.macsupport.Services.UserService;

@Component
public class UserValidator implements Validator {
	private final UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService; 
	}
    
    // 1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getConfirmPassWord().equals(user.getPassWord())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }       
        if (userService.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "Unique");
        }
    }

}