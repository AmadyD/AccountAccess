/**
 * 
 */
package com.AccountAccess.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.AccountAccess.utils.BCrypt;

/**
 * @author Amady
 *
 */
public class SignUpServiceImpl implements SignUpService {

	@Override
	public  String CreateUserAccount(String username, String password) {
		String retour = "Error";
		try {
			if(loginAlreadyExist(username)) {
				return "username ko";
			}
			String insert = insertUser(username, password);
			if(insert.equals("OK")) {
				System.out.println("test 2 : "+initUserFolder(username));
				String init = initUserFolder(username);
				if(init.equals("OK")) retour = initMongoFolder(username);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "KO";
		}
		return retour;
	}
	
	public static Boolean loginAlreadyExist(String username) throws IOException {
		// TODO Auto-generated method stub
	    final String uriVerifLogin = "http://82.165.116.238:8081/MyNas/crud/verifyLogin?login="+username;
	    RestTemplate restTemplate = new RestTemplate();
	    Boolean result = restTemplate.getForObject(uriVerifLogin, Boolean.class);
	    return result;
	}
	
	public String insertUser(String username, String password) throws IOException, NoSuchAlgorithmException  {
		
		String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		final String uriInsertUser = "http://82.165.116.238:8081/MyNas/crud/insertUser";
		RestTemplate restTemplate = new RestTemplate();	
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("login", username);
		map.add("password", generatedSecuredPasswordHash);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity( uriInsertUser, request , String.class );
		System.out.println(response.getStatusCodeValue());
		
		return response.getBody();
	}
	
	public String initUserFolder(String username)  throws IOException {
		
	    final String uri = "http://82.165.116.238:8082/cmd/initUserDir";
	    RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("userName", username);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity( uri, request , String.class );
		
		return response.getBody();
	}
	
	public String initMongoFolder(String username) {
	    String rootFolder="";
	    String folderName = username;
	    final String uri = "http://82.165.116.238:8084/createFolder?userName="+username+"&folderName="+folderName+"&root="+rootFolder;
	    RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("userName", username);
		map.add("root", rootFolder);
		map.add("folderName", folderName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity( uri, request , String.class );
		
		return response.getBody();
	}

}
