package com.oneencrypt.oneencrypt.central.dataobjects;

public class DataStoreFactory {
    public DataStore getDataStoreObject(String dataStoreType){
        if(dataStoreType=="StringHashMapDataStore") return new StringHashMapDataStore();
        return null;
    }
}
