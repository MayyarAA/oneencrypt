package com.oneencrypt.oneencrypt.central.inputlogic;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;

import java.io.File;
import java.util.HashMap;

public interface Input {
    void takeInput();
    void takeInput(File file, DataStore dataStore);
    void run();
    void addToDataStore();
    HashMap<String,String> getDataStore();
}
