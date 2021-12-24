package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;

import java.io.File;
import java.util.HashMap;

public interface Input {
    void takeInput();
    void takeInput(File file, DataStore dataStore);
    void run();
    void addToDataStore();
    HashMap<String,String> getDataStore();

//    void takeInputForDecryption(File userFileForInputEncrypted, StringHashMapDataStore stringHashMapDataStore);
    void takeInputForDecryption(File file, DataStore dataStore);
}
