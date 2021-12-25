package com.oneencrypt.oneencrypt.central.services.fileservices;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.services.keyservices.KeyStoreUtils;
import com.oneencrypt.oneencrypt.central.inputlogic.FileInput;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;
import com.oneencrypt.oneencrypt.central.services.encryption.EncryptionService;

import javax.crypto.SecretKey;
import java.util.*;
import java.io.*;
public class WriteToFileService {
    FileObject fileObj;
    HashMap<String,String> keyValuePairMap;
    public WriteToFileService(){
    }
    public WriteToFileService(FileObject fileObj){
        this.fileObj = fileObj;
    }
    public WriteToFileService(FileObject fileObject, Input input){
        this.fileObj = fileObject;
        keyValuePairMap = input.getDataStore();
    }

    public void createFile(){
        if(!fileObj.checkIfFileExists()){fileObj.createFile();}
        if(!fileObj.checkIfFileIsWorkable()){System.out.println("File is not workable check permissions");return;}
        run();
    }
    private void run(){
//        HashMap<String,String> keyValuePair = new HashMap<>();
//        buildKeyValuePair(keyValuePair);
        writeToFile(this.keyValuePairMap);
    }

    private  boolean writeToFile(HashMap<String,String> keyValuePair){
        if(!writeToFileWithKeyValueMap(keyValuePair)){ System.out.println("Error File was not written too");return false;}
        System.out.println("Success File was written too ");
        return true;
    }
    public  static boolean writeToFileDcrypt(FileInput inputObjectEncrypted){
        HashMap<String,String> keyValuePairMap = inputObjectEncrypted.getDataStore();
        SecretKey secretKey = KeyStoreUtils.loadKey(inputObjectEncrypted.hexKey);
        EncryptionService encryptionService = new EncryptionService(secretKey);
        for(String key:keyValuePairMap.keySet()){
            String encryptedValue = keyValuePairMap.get(key);
//            System.out.println(secretKey);
            System.out.println(key + " " + encryptedValue + " decrypted value-> " + encryptionService.decryptString(encryptedValue));
        }
        return false;
    }


    private  boolean writeToFileWithKeyValueMap(HashMap<String,String> keyValuePair ){
        try{
            FileWriter fileWriter = new FileWriter(this.fileObj.filePathName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String decodedKey = String.valueOf(this.fileObj.decodedKey);
            System.out.println("this.fileObj.decodedKey" + decodedKey);
            bufferedWriter.write(decodedKey);
            bufferedWriter.newLine();
            for(String key:keyValuePair.keySet()){
                String encryptedString= this.fileObj.encryptionService.encryptString(keyValuePair.getOrDefault(key," null"));
                String keyValueStr = key + " : " + encryptedString;
                System.out.println(this.fileObj.encryptionService.decryptString(encryptedString));
//                System.out.println(KeyStoreUtils.loadKey());
                bufferedWriter.write(keyValueStr);
                bufferedWriter.newLine();

            }
            bufferedWriter.close();
            fileWriter.close();
            return true;
        }catch(IOException e){
            System.out.println("Write to File error from writeToFileWithKeyValueMap");
            e.printStackTrace();
            return false;
        }
    }


}
