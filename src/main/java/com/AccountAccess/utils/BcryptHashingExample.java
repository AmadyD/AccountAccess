package com.AccountAccess.utils;

import java.security.NoSuchAlgorithmException;

public class BcryptHashingExample 
{
	public static void test() throws NoSuchAlgorithmException 
	{
		String  originalPassword = "password";
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
		System.out.println(generatedSecuredPasswordHash);
		
		boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
		System.out.println(matched);
	}
}
