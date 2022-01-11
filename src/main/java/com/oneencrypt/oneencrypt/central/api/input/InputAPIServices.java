package com.oneencrypt.oneencrypt.central.api.input;

import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;
import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFile;
import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileService;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.inputlogic.APIInput;

import java.io.File;
import java.util.ArrayList;

public class InputAPIServices {
    private FileObject fileObject;
    private DataStoreFactory dataStoreFactory= new DataStoreFactory();
    private DataStore dataStore = dataStoreFactory.getDataStoreObject("StringHashMapDataStore");
    private APIInput apiInput;
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
    }

    public File returnFile(){
        return this.writeToFileService.returnFile();
    }
    private void createFileObjHelper(){
        String fileName = "temp";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        int fileTag = CreateFileNamePathService.generateRandomFileTage();
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath,fileTag);
        this.fileObject = new FileObject(filePathName);
    }
}
