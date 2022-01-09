package com.oneencrypt.oneencrypt.central.dataobjects;

public class KeyValueObject{
    public String key;
    public String value;
    public KeyValueObject(){};
    public KeyValueObject(String key,String value){
        this.key = key;
        this.value=value;
    }
    public void setKey(String key){
        this.key=key;
    }
    public void setValue(String value){
        this.value=value;
    }
    public String getKey(){return this.key;}
    public String getValue(){return this.value;}
}
