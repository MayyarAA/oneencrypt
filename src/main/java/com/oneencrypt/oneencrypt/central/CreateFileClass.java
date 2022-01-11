package com.oneencrypt.oneencrypt.central;

import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;
import com.oneencrypt.oneencrypt.central.inputlogic.FileInput;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;
import com.oneencrypt.oneencrypt.central.inputlogic.InputFactory;
import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;
import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileDecrypt;
import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileService;

import java.io.File;

public class CreateFileClass {
    public void run(){

        int fileTag = CreateFileNamePathService.generateRandomFileTage();
        //take in vanilla file containing user input
        String fileName = "temp";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath,fileTag);
        FileObject fileObj = new FileObject(filePathName);
        InputFactory inputFactory = new InputFactory();
        Input inputObject = inputFactory.getInputObject("FileInput");
        File userFileForInput = new File("/Users/mayyaral-atari/Desktop/JAVAoneencrypt/temp2888.txt");
        inputObject.takeInput(userFileForInput,new StringHashMapDataStore());
        WriteToFileService writeToFileService=new WriteToFileService(fileObj,inputObject);
        writeToFileService.writeAndCreateFile();
        //will result in new file w/ encrypted data + encryption key

        //decryption of file into a new file
        String fileNameDecrypt = "tempDecrypted";
        String filePathDecrypt = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        String filePathNameDecrypted = CreateFileNamePathService.createFilePathName(fileNameDecrypt, filePathDecrypt, fileTag);
        FileObject fileObjDecrypted = new FileObject(filePathNameDecrypted);
        FileInput inputObjectEncrypted = new FileInput();
        File userFileForInputEncrypted = new File(filePathName);
        inputObjectEncrypted.takeInputForDecryption(userFileForInputEncrypted,new StringHashMapDataStore());
        WriteToFileDecrypt writeToFileDecrypt = new WriteToFileDecrypt(filePathNameDecrypted,fileObjDecrypted,inputObjectEncrypted);
        writeToFileDecrypt.writeAndCreateFile();
        //will result in new file w/decrypted data
    }
}
