package com.oneencrypt.oneencrypt.central;
import java.util.*;
import java.io.*;
public class WriteToFileService {
//sout
    public static void main(String[] args){
        String fileName = "temp";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        String filePathName =  createFileName(fileName,filePath);
        boolean fileExists = checkIfFileExists(filePathName);
        File file = new File(filePathName);
        if(!fileExists){createFile(filePathName);}
        if(!checkIfFileIsWorkable(file)){System.out.println("File is not workable check permissions");return;}
        HashMap<String,String> keyValuePair = new HashMap<>();
        buildKeyValuePair(keyValuePair);
        boolean writeToFileResult = writeToFile(filePathName,keyValuePair);
        if(!writeToFileResult){System.out.println("Error File was not written to0");return;}
        System.out.println("Success File was written too ");
    }

    private static boolean writeToFile(String filePathName, HashMap<String,String> keyValuePair){
        if(!writeToFileWithKeyValueMap(filePathName,keyValuePair)) return false;
        return true;
    }
    private static boolean writeToFileWithKeyValueMap(String filePathName,HashMap<String,String> keyValuePair ){
        try{
            FileWriter fileWriter = new FileWriter(filePathName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String key:keyValuePair.keySet()){
                String keyValueStr = key + " : " + keyValuePair.getOrDefault(key," null");
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

    private static boolean checkIfFileIsWorkable(File file){
        if(!file.canWrite()) return false;
        if(!file.canRead()) return false;
        return true;
    }
    private static boolean checkIfFileExists(String filePathName){
        File file = new File(filePathName);
        if(file.exists()){
            return true;
        }
        return false;
    }

    private static boolean createFile(String filePathName){
        try{
            File file = new File(filePathName);
            if(file.createNewFile()){
                System.out.println("File created");
                return true;
            }
            System.out.println("File already exits");
            return false;
        }catch(IOException e){
            System.out.println("Error file not created due to IOException");
            e.printStackTrace();
            return false;
        }
    }
    private static String createFileName(String fileName, String filePath){
        Random ranNum = new Random();
        int fileTag = ranNum.nextInt(4000);
        String filePathName = filePath + fileName + fileTag + ".txt";
        return filePathName;
    }


}
