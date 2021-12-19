package com.oneencrypt.oneencrypt.central;

public class CreateFileClass {
    public void run(){
        String fileName = "temp";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath);
        FileObject fileObj = new FileObject(filePathName);
        WriteToFileService writeToFileService=new WriteToFileService(fileObj);
        writeToFileService.createFile();
    }
}
