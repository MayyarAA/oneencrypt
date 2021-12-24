package com.oneencrypt.oneencrypt.central;

import java.io.File;
import com.oneencrypt.oneencrypt.central.CreateFileService;
import com.oneencrypt.oneencrypt.central.encryption.KeyStoreUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Random;

public class FileObject {
    File file;
    String filePathName;
    SecretKey encryptionKey;
    EncryptionService encryptionService;
    char[] decodedKey;
    public FileObject(String filePathName){
        this.file = new File(filePathName);
        this.filePathName = filePathName;
        this.encryptionKey = generateKeyV1();
        this.decodedKey = KeyStoreUtils.decodeKey(this.encryptionKey);
//        String algorithm = "AES/CBC/PKCS5Padding";
//        this.encryptionService = new EncryptionService(algorithm,encryptionKey,ivParameterSpec);
        this.encryptionService = new EncryptionService(encryptionKey);
    }
    protected  boolean checkIfFileIsWorkable(){
        if(!this.file.canWrite()) return false;
        if(!this.file.canRead()) return false;
        return true;
    }
    protected  boolean checkIfFileExists(){
        if(this.file.exists()){
            return true;
        }
        return false;
    }
    protected void createFile(){
        CreateFileService.createFile(this.filePathName);
    }

    protected  SecretKey generateKeyV1(){
        int n =256;
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
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
