package com.oneencrypt.oneencrypt.central.FileFeature;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;

import java.util.*;
import java.io.*;
public class WriteToFileService extends WriteToFile{
    public WriteToFileService(FileObject fileObject, Input input){
        super(fileObject,input);
//        this.fileObj = fileObject;
//        keyValuePairMap = input.getDataStore();
    }

    public void writeAndCreateFile(){
        if(!fileObj.checkIfFileExists()){fileObj.createFile();}
        if(!fileObj.checkIfFileIsWorkable()){System.out.println("File is not workable check permissions");return;}
        run();
    }
    protected void run(){
        writeToFile();
    }

    protected boolean writeToFile(){
        if(!writeToFileWithKeyValueMap(super.input.getDataStore())){ System.out.println("Error File was not written too");return false;}
        System.out.println("Success File was written too ");
        return true;
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

    public File returnFile(){
        return new File(this.fileObj.filePathName);
    }


}
