package com.oneencrypt.oneencrypt.dataobjects.StringHashMapDataStoreTest;

import com.oneencrypt.oneencrypt.central.dataobjects.DataStore;
import com.oneencrypt.oneencrypt.central.dataobjects.DataStoreFactory;
import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataStoreFactoryTest {
    DataStoreFactory dataStoreFactory;
    String STRINGHASHMAPDATASTORE = "StringHashMapDataStore";
    @BeforeEach
    public void setup(){
        this.dataStoreFactory = new DataStoreFactory();
    }
    @Test
    public void checkIfDataStoreCanReturnNull(){
        DataStore dataStore = dataStoreFactory.getDataStoreObject("returnull");
        Assertions.assertNull(dataStore);
    }
    @Test
    public void checkIfDataStoreCanReturnStringMap(){
        DataStore dataStore = dataStoreFactory.getDataStoreObject(STRINGHASHMAPDATASTORE);
        Class temp = dataStore.getClass();
        Class temp2 = StringHashMapDataStore.class;
        Assertions.assertTrue(dataStore.getClass() == StringHashMapDataStore.class);
    }
}
