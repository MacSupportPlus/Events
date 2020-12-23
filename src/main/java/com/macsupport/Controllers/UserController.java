package com.macsupport.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macsupport.Models.User;
import com.macsupport.Services.UserService;
import com.macsupport.Validators.UserValidator;

@Controller
public class UserController {
	  private final UserService userService;
	  private final UserValidator userValidator;
	    
	    public UserController(UserService userService, UserValidator userValidator) {
	        this.userService = userService;
	        this.userValidator = userValidator;
	     
	    }
	    @RequestMapping(path={"/","/sign_in"})
	    public String registerForm(@ModelAttribute("user") User user) {
	        return "signIn.jsp";
	    }
	 
	    @RequestMapping(value="/register", method=RequestMethod.POST)
	    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
	        // if result has errors, return the registration page (don't worry about validations just now)
	    	 userValidator.validate(user, result);
	         if(result.hasErrors()) {
	             return "signIn.jsp";
	    	} else {
	    		
	    			User saveUser = userService.registerUser(user);
	    			session.setAttribute("user_id", saveUser.getId());
	    			return "redirect:/sign_in";
	    		} 
	    }
	    
	    @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String loginUser(@RequestParam("email") String email, @RequestParam("passWord") String passWord, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
	    	        // if the user is authenticated, save their user id in session
	    	boolean isValid = userService.authenticateUser(email, passWord);
	    	if(isValid == true) {
	    		User validUser = userService.findByEmail(email);
	    		session.setAttribute("user_id", validUser.getId());
	    		return "redirect:/events";
	    		
	    	} else { 
	    	        // else, add error messages and return the login page
	    		redirectAttributes.addFlashAttribute("login_errors", "Invalid email/password combination!");
	    		return "redirect:/sign_in";
	    	    }
	    }
	    @RequestMapping("/logout")
	    public String logout(HttpSession session) {
	        // invalidate session
	    	session.invalidate();
	        // redirect to login page
	    	return "redirect:/login";
	    }
	    
	   
}
