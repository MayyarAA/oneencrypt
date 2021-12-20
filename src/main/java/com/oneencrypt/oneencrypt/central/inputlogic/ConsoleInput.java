package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInput implements Input{
    HashMap<String,String> keyValueMap;
    public ConsoleInput(){
        this.keyValueMap = new HashMap<>();
    }

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            KeyValueObject keyValue = new KeyValueObject();
            keyValue.setKey(scanner.next());
            keyValue.setValue(scanner.next());
            addToDataStore(keyValue);
        }
        scanner.close();
    }

    @Override
    public void addToDataStore() {
    }

    @Override
    public HashMap<String, String> getDataStore() {
        return this.keyValueMap;
    }

    public void addToDataStore(KeyValueObject keyValue){
        this.keyValueMap.put(keyValue.key,keyValue.value);
    }
    @Override
    public void takeInput(){

    }

    @Override
    public void takeInput(File file, DataStore dataStore) {

    }
}
