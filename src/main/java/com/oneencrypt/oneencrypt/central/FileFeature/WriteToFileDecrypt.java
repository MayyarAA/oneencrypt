package com.oneencrypt.oneencrypt.central.FileFeature;

import com.oneencrypt.oneencrypt.central.CreateFileService;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;
import com.oneencrypt.oneencrypt.central.services.encryption.EncryptionService;
import com.oneencrypt.oneencrypt.central.services.keyservices.KeyStoreUtils;

import javax.crypto.SecretKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WriteToFileDecrypt extends  WriteToFile{
    private String filePathNameDecrypted;
    File file;
    EncryptionService encryptionService;
    public WriteToFileDecrypt(FileObject fileObject, Input input){
        super(fileObject,input);
    }
    public WriteToFileDecrypt(String filePathNameDecrypted, FileObject fileObject, Input input){
        super(fileObject,input);
        this.filePathNameDecrypted = filePathNameDecrypted;
        if(CreateFileService.createFile(this.filePathNameDecrypted)){
            this.file = new File(filePathNameDecrypted);
        }else{
            this.file = new File("Error");
        };
    }

    @Override
    public void writeAndCreateFile() {
        writeToFile();
    }
    public void writeAndCreateFileAlreadyDecrypted() {
        writeToFileWithKeyValueMapAlreadyDecrypted();
    }

    protected void run(){
    }
    @Override
    protected boolean writeToFile() {
        HashMap<String,String> keyValuePairMap =  super.input.getDataStore();
        SecretKey secretKey = KeyStoreUtils.loadKey(super.input.getHexKey());
        this.encryptionService = new EncryptionService(secretKey);
        writeToFileWithKeyValueMap();
//        for(String key:keyValuePairMap.keySet()){
//            String encryptedValue = keyValuePairMap.get(key);
//            System.out.println(key + " " + encryptedValue + " decrypted value-> " + encryptionService.decryptString(encryptedValue));
//        }
        return true;
    }
    private  boolean writeToFileWithKeyValueMap() {
        try {
            FileWriter fileWriter = new FileWriter(super.fileObj.filePathName);
            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
            for (String key : super.input.getDataStore().keySet()) {
                String encryptedValue = super.input.getDataStore().get(key);
                String decryptedValue = encryptionService.decryptString(encryptedValue);
                String keyValueStr = key + " : " + decryptedValue;
                bufferedWriter.write(keyValueStr);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (IOException ioException) {
            System.out.println("ioException thrown from WriteToFileDecrypt.writeToFileWithKeyValueMap");
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private  boolean writeToFileWithKeyValueMapAlreadyDecrypted() {
        try {
            FileWriter fileWriter = new FileWriter(super.fileObj.filePathName);
            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
            for (String key : super.input.getDataStore().keySet()) {
                String decryptedValue = super.input.getDataStore().get(key);
//                String decryptedValue = encryptionService.decryptString(encryptedValue);
                String keyValueStr = key + " : " + decryptedValue;
                bufferedWriter.write(keyValueStr);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (IOException ioException) {
            System.out.println("ioException thrown from WriteToFileDecrypt.writeToFileWithKeyValueMap");
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public File returnFile(){
        return new File(this.fileObj.filePathName);
    }
}
