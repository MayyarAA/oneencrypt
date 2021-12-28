package com.oneencrypt.oneencrypt.central.FileFeature;

import java.util.*;

public class CreateFileNamePathService {
    static Random ranNum = new Random();
    public static String createFilePathName(String fileName, String filePath){
        if(fileName==null||fileName.length()==0)fileName="temp";
        int fileTag = ranNum.nextInt(4000);
        String filePathName = filePath + fileName + fileTag + ".txt";
        return filePathName;
    }

    public static String createFilePathName(String fileName, String filePath , int fileTag){
        String filePathName = filePath + fileName + fileTag + ".txt";
        return filePathName;
    }
    public static int generateRandomFileTage(){
        int fileTag = ranNum.nextInt(4000);
        return fileTag;
    }

}
