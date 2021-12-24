package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileInput implements Input{
    boolean fileSet=false;
    File file;
    DataStore dataStore;
    @Override
    public void takeInput() {

    }
    public void takeInput(File file, DataStore dataStore) {
        this.file = file;
        DataStoreFactory dataStoreFactory = new DataStoreFactory();
        this.dataStore = dataStoreFactory.getDataStoreObject("StringHashMapDataStore");
        fileSet= true;
        run();
    }
    @Override
    public void run() {
        try{
            if(!fileSet){throw new Exception();}
            readFileService();
        }catch(Exception e){
            System.out.println("Error: Set File for input first FileInput>run");
            e.printStackTrace();
            return;
        }

    }

    @Override
    public void addToDataStore() {

    }

    public void readFileService() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);


//            while(scanner.hasNextLine() ){
            while(scanner.hasNext() ){
                KeyValueObject keyValue = new KeyValueObject();
                String key = "null";
                String pwd = "null";
                try{
                    if(scanner.hasNext()){key = (scanner.next());;}
                    keyValue.setKey(key);
                    scanner.next();
                    if(scanner.hasNext()){pwd = (scanner.next());}
                    keyValue.setValue(pwd);
                    addToDataStore(keyValue);
                }catch(NoSuchElementException e){
                    System.out.println(" readFileService Scanner read empty line");
                    e.printStackTrace();
                }
            }
            scanner.close();
    }

    public void addToDataStore(KeyValueObject keyValueObject) {
        dataStore.addToDataStore(keyValueObject);
    }

    @Override
    public HashMap<String, String> getDataStore() {
        return dataStore.getDataStore();
    }
}
