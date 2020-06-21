/**
 * 
 */
package com.AccountAccess.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AccountAccess.services.SignUpServiceImpl;



/**
 * @author Amady
 *
 */
@RestController
@RequestMapping(value = "/Inscription", method = {RequestMethod.POST})
public class signUpController {
	
	@PostMapping(value = "/user")
	public String newUser(@RequestParam String username,@RequestParam String password) {
	 SignUpServiceImpl	signup = new SignUpServiceImpl();
	 return signup.CreateUserAccount(username, password);
	}

}
