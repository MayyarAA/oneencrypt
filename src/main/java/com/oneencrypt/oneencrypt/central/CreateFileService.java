package com.oneencrypt.oneencrypt.central;

import java.io.File;
import java.io.IOException;

public class CreateFileService {
    public static boolean createFile(String filePathName){
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
}
