package com.oneencrypt.oneencrypt.central;

import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;
import com.oneencrypt.oneencrypt.central.inputlogic.FileInput;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;
import com.oneencrypt.oneencrypt.central.inputlogic.InputFactory;

import java.io.File;

public class CreateFileClass {
    public void run(){
        String fileName = "temp";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath);
        FileObject fileObj = new FileObject(filePathName);
        InputFactory inputFactory = new InputFactory();
        Input inputObject = inputFactory.getInputObject("FileInput");
        File userFileForInput = new File("/Users/mayyaral-atari/Desktop/JAVAoneencrypt/temp2888.txt");
        inputObject.takeInput(userFileForInput,new StringHashMapDataStore());
        WriteToFileService writeToFileService=new WriteToFileService(fileObj,inputObject);
        writeToFileService.createFile();

        String encryptedFilePathName = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/temp974.txt";
//        Input inputObjectEncrypted = inputFactory.getInputObject("FileInput");
        FileInput inputObjectEncrypted = new FileInput();
        File userFileForInputEncrypted = new File(filePathName);
        inputObjectEncrypted.takeInputForDecryption(userFileForInputEncrypted,new StringHashMapDataStore());
        WriteToFileService.writeToFileDcrypt(inputObjectEncrypted);
    }
}
