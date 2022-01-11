package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class APIInput extends Input{
    public DataStore dataStore;
    DataStoreFactory dataStoreFactory = new DataStoreFactory();
    ArrayList<KeyValueObject> listOfKeyValueObj;
    public APIInput(ArrayList<KeyValueObject> listOfKeyValueObj){
        this.listOfKeyValueObj = listOfKeyValueObj;
        this.dataStore = dataStoreFactory.getDataStoreObject("StringHashMapDataStore");
        addToDataStore();
    }
    private void decomposeListToStringHashMapDataStore(){
        for(KeyValueObject keyValueObject: listOfKeyValueObj){
            this.dataStore.addToDataStore(keyValueObject);
        }
    }
    @Override
    public void takeInput() {

    }

    @Override
    public void takeInput(File file, DataStore dataStore) {

    }

    @Override
    public void run() {

    }

    @Override
    public void addToDataStore() {
        decomposeListToStringHashMapDataStore();
    }

    //To do: Refactor return type to be DataStore
    @Override
    public HashMap<String, String> getDataStore() {
//        HashMap<String,String> map = new HashMap<>();
//        for(String key: dataStore.getDataStore().keySet()){
//            map.
//        }
        return dataStore.getDataStore();
    }

    @Override
    public void takeInputForDecryption(File file, DataStore dataStore) {

    }
}
