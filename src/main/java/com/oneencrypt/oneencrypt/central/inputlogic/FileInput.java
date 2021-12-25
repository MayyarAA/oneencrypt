package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileInput extends Input {
    boolean fileSet=false;

    File file;
    DataStore dataStore;
    public FileInput(String hexKey){
        super(hexKey);
    }
    public FileInput(){
        super("null");
    }
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
    public  void takeInputForDecryption(File file, DataStore dataStore){
        this.file = file;
        DataStoreFactory dataStoreFactory = new DataStoreFactory();
        this.dataStore = dataStoreFactory.getDataStoreObject("StringHashMapDataStore");
        fileSet= true;
        runFileDecryption();
    }
    private void runFileDecryption(){
        try{
            if(!fileSet){throw new Exception();}
            readFileServiceForDecryption();
        }catch(Exception e){
            System.out.println("Error: Set File for input first FileInput>run");
            e.printStackTrace();
            return;
        }

    }

    public void readFileServiceForDecryption() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext())super.setHexKey( scanner.next());//skip the key which is the first line of the file
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
    public static String readFileKeyToByteArray(File file) {
        try{
            Scanner scanner = new Scanner(file);
            if(!file.exists()){throw  new FileNotFoundException();}
            if(!scanner.hasNext()){throw  new NullPointerException();}
            String encodedKeyFromFile = scanner.next();
            return encodedKeyFromFile;
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("FileNotFoundException from readFileToByteArray");
            fileNotFoundException.printStackTrace();
        }catch (IOException ioException){
            System.out.println("IOException from readFileToByteArray");
            ioException.printStackTrace();
        }catch(NullPointerException nullPointerException){
            System.out.println("NullPointerException from readFileToByteArray");
            nullPointerException.printStackTrace();
        }catch (Exception e){
            System.out.println("Exception from readFileToByteArray");
            e.printStackTrace();
        }
        return null;
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
