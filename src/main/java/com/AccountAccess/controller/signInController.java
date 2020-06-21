/**
 * 
 */
package com.AccountAccess.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AccountAccess.services.SignInServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

/**
 * @author Amady
 *
 */
@RestController
@RequestMapping(value = "/Identification", method = {RequestMethod.POST})
public class signInController {
	
	@PostMapping(value = "/user")
	public String logginIn(@RequestParam String username,@RequestParam String password) {
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		        .withIssuer("auth0")
		        .sign(algorithm);
		    System.out.println("Token is: "+token);
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			System.out.println("Damn !");
		}
		SignInServiceImpl service = new SignInServiceImpl();
		return service.verifyCredentials(username, password);
	}

}
