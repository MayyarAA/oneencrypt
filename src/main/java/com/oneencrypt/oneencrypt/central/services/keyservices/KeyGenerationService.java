package com.oneencrypt.oneencrypt.central.services.keyservices;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGenerationService {
    public static SecretKey generateKeyV1(){
        int n =256;
        String algorthim = "AES";
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
