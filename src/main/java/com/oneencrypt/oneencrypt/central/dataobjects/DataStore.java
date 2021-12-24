package com.oneencrypt.oneencrypt.central.dataobjects;

import java.util.HashMap;

public abstract class DataStore {
    public void addToDataStore(KeyValueObject keyValueObject) {}
    public abstract HashMap<String, String>getDataStore();
}
