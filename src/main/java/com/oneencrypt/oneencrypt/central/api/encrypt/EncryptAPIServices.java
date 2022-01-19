package com.oneencrypt.oneencrypt.central.api.encrypt;

import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;

import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileService;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.api.EDAPIService;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObjectService;
import com.oneencrypt.oneencrypt.central.inputlogic.APIInput;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EncryptAPIServices extends EDAPIService {

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
    public void createFileAddValuesEncryptedWithByteArray(byte[] byteArrayOfFile){
        ArrayList<KeyValueObject> keyValueListObj = convertStringToKeyValueObjectList(new String(byteArrayOfFile, StandardCharsets.UTF_8));
        createFileAddValuesEncrypted(keyValueListObj);
    }
    public void convertByteArrayToKeyValueObj(byte[] byteArrayOfFile){
        String keyValueString = new String(byteArrayOfFile, StandardCharsets.UTF_8);
        ArrayList<KeyValueObject> keyValueListObj = convertStringToKeyValueObjectList(keyValueString);
    }
    private ArrayList<KeyValueObject>  convertStringToKeyValueObjectList(String keyValueString){
        ArrayList<KeyValueObject> keyValueListObj = KeyValueObjectService.convertStringToKeyValueObject(keyValueString);
        return keyValueListObj;
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




}
