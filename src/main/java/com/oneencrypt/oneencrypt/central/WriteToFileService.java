package com.oneencrypt.oneencrypt.central;
import java.util.*;
import java.io.*;
public class WriteToFileService {
//sout
    public static void main(String[] args){
        String fileName = "temp";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        String filePathName =  createFileName(fileName,filePath);
        boolean isFileCreated= createFile(filePathName);
        if(!isFileCreated){
            return;
        }
        boolean writeToFileResult = writeToFile(filePathName);

    }

    private static boolean writeToFile(String filePathName){
//        FileWriter fw  = new FileWriter();
        return true;
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
