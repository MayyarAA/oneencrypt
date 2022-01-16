package com.oneencrypt.oneencrypt.central.api.encrypt;

import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;

import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileService;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.api.EDAPIServiceInterface;
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

public class EncryptAPIServices extends EDAPIServiceInterface {

    private DataStoreFactory dataStoreFactory= new DataStoreFactory();
    private DataStore dataStore = dataStoreFactory.getDataStoreObject("StringHashMapDataStore");
    private WriteToFileService writeToFileService;

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
        //return encrypted file
        //make sure to include key
        setFile(writeToFileService.returnFile());
    }


    public long getFileLength(){
        return super.getFileLength();
    }
    public void createFileObjHelper(){
        String fileName = "tempEncrypted";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        int fileTag = CreateFileNamePathService.generateRandomFileTage();
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath,fileTag);
        this.fileObject = new FileObject(filePathName);
    }


    public ByteArrayResource createByteArrayResource() throws IOException {
        createHeaderHelperForFileReturn(super.getFile().getName());
        Path path = Paths.get(super.getFile().getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }

}
