package com.oneencrypt.oneencrypt.central.api.decryptAPI;

import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;
import com.oneencrypt.oneencrypt.central.FileFeature.WriteToFileDecrypt;
import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.api.EDAPIServiceInterface;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObjectList;
import com.oneencrypt.oneencrypt.central.inputlogic.APIInput;
import com.oneencrypt.oneencrypt.central.services.encryption.EncryptionService;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.util.ArrayList;

public class DecryptAPIService extends EDAPIServiceInterface {
    EncryptionService encryptionService;
    private WriteToFileDecrypt writeToFileDecrypt;
    public void createFileAddValuesEncrypted(ArrayList<KeyValueObject> decryptedKeyValueObjectsArrayList){
        //create file
        createFileObjHelper();
        //decompose ArrayList<KeyValueObject> to StringHashMapDataStore
        apiInput = new APIInput(decryptedKeyValueObjectsArrayList);
        this.writeToFileDecrypt = new WriteToFileDecrypt(fileObject,apiInput);
        writeToFileDecrypt.writeAndCreateFileAlreadyDecrypted();
        super.setFile(writeToFileDecrypt.returnFile());
    }
    public  ArrayList<KeyValueObject> decryptStringServices(KeyValueObjectList keyValueObjectList) throws Exception{
        if(keyValueObjectList==null) throw new Exception("Error: keyValueObjectList is null");
        if(keyValueObjectList.getKeyValueObjectsLists().size()<=0) throw new Exception("Error: keyValueList is empty");
        if(keyValueObjectList.getEncryptionKey()==null ||keyValueObjectList.getEncryptionKey().length()<=0 )  throw new Exception("Error: Encryption Key is missing");
        //parse out encryptionKey
        this.encryptionService = new EncryptionService(keyValueObjectList.getEncryptionKey());
        //take each string and drcrypt it
        ArrayList<KeyValueObject> keyValueObjectsArrayList = new ArrayList<>();
        for(int i=0;i<keyValueObjectList.getKeyValueObjectsLists().size();i++){
            KeyValueObject encryptedKvObj = keyValueObjectList.getKeyValueObjectsLists().get(i);
            keyValueObjectsArrayList.add(new KeyValueObject(encryptedKvObj.getKey(),encryptionService.decryptString(encryptedKvObj.getValue())));
        }
        //return the decrytpedString
        return keyValueObjectsArrayList;
    }


    public void createFileObjHelper(){
        String fileName = "tempDecrypted";
        String filePath = "/Users/mayyaral-atari/Desktop/JAVAoneencrypt/";
        int fileTag = CreateFileNamePathService.generateRandomFileTage();
        String filePathName = CreateFileNamePathService.createFilePathName(fileName, filePath,fileTag);
        this.fileObject = new FileObject(filePathName);
    }


    public HttpHeaders createHeaderHelperForFileReturnTemp(){
        HttpHeaders header = new HttpHeaders();
        header.add(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + super.getFile().getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        super.setHttpHeaders(header);
        return header;
    }
}
