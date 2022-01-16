package com.oneencrypt.oneencrypt.central.api.encrypt;

import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;

import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileService;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.inputlogic.APIInput;

import java.io.File;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EncryptAPIServices {
    private FileObject fileObject;
    private DataStoreFactory dataStoreFactory= new DataStoreFactory();
    private DataStore dataStore = dataStoreFactory.getDataStoreObject("StringHashMapDataStore");
    private APIInput apiInput;
    private WriteToFileService writeToFileService;
    private HttpHeaders httpHeaders;
    private File file;
    public void createFileAddValuesEncrypted(ArrayList<KeyValueObject> keyValueListObj){
        //create fileobj
        createFileObjHelper();
        //create input
        apiInput = new APIInput(keyValueListObj);
        //create dataStore
        this.dataStore = apiInput.dataStore;
        //add values to fileobj
        this.writeToFileService = new WriteToFileService(fileObject,apiInput);
        writeToFileService.writeAndCreateFile();
        this.file = writeToFileService.returnFile();
        //return encrypted file
        //make sure to include key
    }

    public File returnFile(){
        return this.file;
    }
    public long getFileLength(){
        return this.file.length();
    }
    private void createFileObjHelper(){
        String fileName = "tempEncrypted";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        int fileTag = CreateFileNamePathService.generateRandomFileTage();
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath,fileTag);
        this.fileObject = new FileObject(filePathName);
    }

    public void createHeaderHelperForFileReturn(String fileName){
        HttpHeaders header = new HttpHeaders();
        header.add(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        this.httpHeaders = header;
    }
    public HttpHeaders getHttpHeaders(){
        return this.httpHeaders;
    }


    public ByteArrayResource createByteArrayResource() throws IOException {
        createHeaderHelperForFileReturn(file.getName());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }

}