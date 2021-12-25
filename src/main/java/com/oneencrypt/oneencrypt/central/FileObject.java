package com.oneencrypt.oneencrypt.central;

import java.io.File;

import com.oneencrypt.oneencrypt.central.services.keyservices.KeyGenerationService;
import com.oneencrypt.oneencrypt.central.services.keyservices.KeyStoreUtils;
import com.oneencrypt.oneencrypt.central.services.encryption.EncryptionService;
import javax.crypto.SecretKey;

public class FileObject {
    public File file;
    public String filePathName;
    public SecretKey encryptionKey;
    public EncryptionService encryptionService;
    public char[] decodedKey;
    public FileObject(String filePathName){
        this.file = new File(filePathName);
        this.filePathName = filePathName;
        this.encryptionKey = KeyGenerationService.generateKeyV1();
        this.decodedKey = KeyStoreUtils.decodeKey(this.encryptionKey);
//        String algorithm = "AES/CBC/PKCS5Padding";
        this.encryptionService = new EncryptionService(encryptionKey);
    }
    public   boolean checkIfFileIsWorkable(){
        if(!this.file.canWrite()) return false;
        if(!this.file.canRead()) return false;
        return true;
    }
    public  boolean checkIfFileExists(){
        if(this.file.exists()){
            return true;
        }
        return false;
    }
    public void createFile(){
        CreateFileService.createFile(this.filePathName);
    }



}
