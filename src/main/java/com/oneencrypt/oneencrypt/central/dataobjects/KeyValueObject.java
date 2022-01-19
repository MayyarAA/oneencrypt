package com.oneencrypt.oneencrypt.central.dataobjects;

public class KeyValueObject{
    public String key;
    public String value;
    public String encryptionKey;
    public KeyValueObject(){};
    public KeyValueObject(String key,String value){
        this.key = key;
        this.value=value;
    }
    public KeyValueObject(String key,String value, String EncryptionKey){
        this.key = key;
        this.value=value;
        this.encryptionKey = EncryptionKey;
    }
    public void setKey(String key){
        this.key=key;
    }
    public void setValue(String value){
        this.value=value;
    }
    public void setEncryptionKey(String encryptionKey){this.encryptionKey = encryptionKey;}
    public String getKey(){return this.key;}
    public String getValue(){return this.value;}
    public String getEncryptionKey(){return this.encryptionKey;}

}
