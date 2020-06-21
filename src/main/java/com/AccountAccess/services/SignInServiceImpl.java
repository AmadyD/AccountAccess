/**
 * 
 */
package com.AccountAccess.services;

import java.io.IOException;
import org.springframework.web.client.RestTemplate;

import com.AccountAccess.Model.User;
import com.AccountAccess.utils.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Amady
 *
 */
public class SignInServiceImpl implements SignInService {

	@Override
	public String signIn(String username, String password) {
		return null;
	
	}
	
	public String verifyCredentials(String username, String password){
		try {
			if(!SignUpServiceImpl.loginAlreadyExist(username)) return "uKO";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    final String uri = "http://82.165.116.238:8081/MyNas/crud/getUser?login="+username;
	    RestTemplate restTemplate = new RestTemplate();
	    Object user = restTemplate.getForObject(uri, Object.class);
	    String retour="";
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	      String json = mapper.writeValueAsString(user);
	      System.out.println("ResultingJSONstring = " + json);
	      User us = new ObjectMapper().readValue(json, User.class);
	      System.out.println(us.getPassword());
	      boolean matched = BCrypt.checkpw(password, us.getPassword());
	      System.out.println("verif: "+matched);
	      if(matched) {
	    	  retour = "OK";
	      }else{
	    	  retour = "pKO";
	      }
	      //System.out.println(json);
	    } catch (JsonProcessingException e) {
	       e.printStackTrace();
	    }


	    return retour;
	}
	


}
