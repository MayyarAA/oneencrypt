package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;

import java.io.File;
import java.util.HashMap;

public abstract class Input {
    private String hexKey;
    public Input(){}
    public Input(String hexKey ){
        this.hexKey = hexKey;
    }
    public void setHexKey(String hexKey){
        this.hexKey = hexKey;
    }
    public String getHexKey(){return this.hexKey;}
    public abstract void takeInput();
    public abstract void takeInput(File file, DataStore dataStore);
    public abstract void run();
    public abstract void addToDataStore();
    public abstract HashMap<String,String> getDataStore();

//    void takeInputForDecryption(File userFileForInputEncrypted, StringHashMapDataStore stringHashMapDataStore);
public abstract void takeInputForDecryption(File file, DataStore dataStore);
}
