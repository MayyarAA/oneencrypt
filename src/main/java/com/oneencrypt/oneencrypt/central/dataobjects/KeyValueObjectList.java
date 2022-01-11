package com.oneencrypt.oneencrypt.central.dataobjects;

import java.util.ArrayList;

public class KeyValueObjectList {
    public ArrayList<KeyValueObject> keyValueObjectsLists;
    public String encryptionKey;
    public KeyValueObjectList(ArrayList<KeyValueObject> keyValueObjectsLists, String encryptionKey){
        this.encryptionKey= encryptionKey;
        this.keyValueObjectsLists = keyValueObjectsLists;
    }
    public void setKeyValueObjectsLists(ArrayList<KeyValueObject> keyValueObjectsLists){
        this.keyValueObjectsLists = keyValueObjectsLists;
    }
    public void setEncryptionKey(String encryptionKey){this.encryptionKey = encryptionKey;}
    public String getEncryptionKey(){return this.encryptionKey;}
    public ArrayList<KeyValueObject> getKeyValueObjectsLists(){return this.keyValueObjectsLists;}
}
