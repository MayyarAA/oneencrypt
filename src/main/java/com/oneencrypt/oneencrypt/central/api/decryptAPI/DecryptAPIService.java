package com.oneencrypt.oneencrypt.central.api.decryptAPI;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObjectList;
import com.oneencrypt.oneencrypt.central.services.encryption.EncryptionService;

import java.util.ArrayList;

public class DecryptAPIService {
    EncryptionService encryptionService;
    //return ArrayList<KeyValueObject> for now temp
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
}
