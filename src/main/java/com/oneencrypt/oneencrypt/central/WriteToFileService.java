package com.oneencrypt.oneencrypt.central;
import java.util.*;
import java.io.*;
public class WriteToFileService {
    FileObject fileObj;
    public WriteToFileService(){
    }
    public WriteToFileService(FileObject fileObj){
        this.fileObj = fileObj;
    }
//    public static void main(String[] args){
//        String fileName = "temp";
//        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
//        String filePathName =  createFileName(fileName,filePath);
//        boolean fileExists = checkIfFileExists(filePathName);
//        File file = new File(filePathName);
//        if(!fileExists){createFile(filePathName);}
//        if(!checkIfFileIsWorkable(file)){System.out.println("File is not workable check permissions");return;}
//        HashMap<String,String> keyValuePair = new HashMap<>();
//        buildKeyValuePair(keyValuePair);
//        boolean writeToFileResult = writeToFile(filePathName,keyValuePair);
//        if(!writeToFileResult){System.out.println("Error File was not written to0");return;}
//        System.out.println("Success File was written too ");
//    }

    public void createFile(){
        if(!fileObj.checkIfFileExists()){fileObj.createFile();}
        if(!fileObj.checkIfFileIsWorkable()){System.out.println("File is not workable check permissions");return;}
        HashMap<String,String> keyValuePair = new HashMap<>();
        buildKeyValuePair(keyValuePair);
        run();
    }
    private void run(){
        HashMap<String,String> keyValuePair = new HashMap<>();
        buildKeyValuePair(keyValuePair);
        writeToFile(keyValuePair);
    }

    private  boolean writeToFile(HashMap<String,String> keyValuePair){
        if(!writeToFileWithKeyValueMap(keyValuePair)){ System.out.println("Error File was not written too");return false;}
        System.out.println("Success File was written too ");
        return true;
    }
    private  boolean writeToFileWithKeyValueMap(HashMap<String,String> keyValuePair ){
        try{
            FileWriter fileWriter = new FileWriter(this.fileObj.filePathName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String key:keyValuePair.keySet()){
                String keyValueStr = key + " : " + this.fileObj.encryptionService.encryptString(keyValuePair.getOrDefault(key," null"));
                System.out.println(this.fileObj.encryptionService.decryptString(keyValuePair.getOrDefault(key," null")));
                bufferedWriter.write(keyValueStr);
                bufferedWriter.newLine();

            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException e){
            System.out.println("Write to File error from writeToFileWithKeyValueMap");
            e.printStackTrace();
        }
        return true;
    }


    private static void buildKeyValuePair(HashMap<String,String> keyValuePair){
        keyValuePair.put("k1","p1");
        keyValuePair.put("k2","p2");
    }
}
