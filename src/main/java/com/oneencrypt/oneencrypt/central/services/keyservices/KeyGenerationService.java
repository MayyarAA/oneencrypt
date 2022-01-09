package com.oneencrypt.oneencrypt.central.services.keyservices;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGenerationService {
    static String algorthim = "AES";
    static int n =256;
    public static SecretKey generateKeyV1(){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorthim);
            keyGenerator.init(n);
            SecretKey key = keyGenerator.generateKey();
            System.out.println("Secret Key Generated" + " ---------> " + key.getEncoded());
            return key;
        }catch(Exception e){
            System.out.println("Error during Key Generation Process");
            return null;
        }
    }
}
