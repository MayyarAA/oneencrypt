package com.oneencrypt.oneencrypt.central.services.fileservices;

import java.util.*;

public class CreateFileNamePathService {
    static Random ranNum = new Random();
    public static String createFilePathName(String fileName, String filePath){
        int fileTag = ranNum.nextInt(4000);
        String filePathName = filePath + fileName + fileTag + ".txt";
        return filePathName;
    }
}
