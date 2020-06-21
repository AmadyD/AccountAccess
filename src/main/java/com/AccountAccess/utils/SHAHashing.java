package com.AccountAccess.utils;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
 
public class SHAHashing {
     
    public static String sha_256(String password) throws NoSuchAlgorithmException 
    {
        byte[] salt = getSalt();
        
         
        String securePassword = get_SHA_256_SecurePassword(password, salt);
        //System.out.println(securePassword);
        return securePassword; 
    }
 
     
    private static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        //Use MessageDigest md = MessageDigest.getInstance("SHA-256");
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
     
    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}