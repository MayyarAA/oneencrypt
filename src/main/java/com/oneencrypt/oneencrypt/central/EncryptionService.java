package com.oneencrypt.oneencrypt.central;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionService {
    String algorithm;
    SecretKey key;
    IvParameterSpec iv;
    public EncryptionService(String algorithm, SecretKey key, IvParameterSpec iv){
        this.algorithm = algorithm;
        this.key= key;
        this.iv = iv;
    }
    public  String encryptString( String inputString){
        try{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(inputString.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        }catch(Exception e){
            System.out.println("Error during encryption process from: EncryptionService>encryptString");
            e.printStackTrace();
        }
        return null;
    }

    public String decryptString(String encryptedInputString){
        try{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedInputString));
            return new String(plainText);
        }catch (Exception e){
            System.out.println("Error during decryption process from: EncryptionService>decryptString");
        }
            return encryptedInputString;
    }
}
