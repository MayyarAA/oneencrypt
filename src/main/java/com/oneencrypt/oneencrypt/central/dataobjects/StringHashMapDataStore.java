package com.oneencrypt.oneencrypt.central.dataobjects;

import java.util.HashMap;

public class StringHashMapDataStore extends DataStore{
    HashMap<String,String> keyValueMap;
    public StringHashMapDataStore(){
        this.keyValueMap = new HashMap<>();
    }
    public void addToDataStore(KeyValueObject keyValue){
        this.keyValueMap.put(keyValue.key,keyValue.value);
    }
    public HashMap<String, String> getDataStore() {
        return this.keyValueMap;
    }
}
