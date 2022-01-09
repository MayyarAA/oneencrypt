package com.oneencrypt.oneencrypt.dataobjects.StringHashMapDataStoreTest;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.StringHashMapDataStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringHashMapDataStoreTest {
    StringHashMapDataStore stringHashMapDataStore;
    KeyValueObject keyValueObject = new KeyValueObject("keyStr","valueStr");
    @BeforeEach
    public void setup(){
        this.stringHashMapDataStore = new StringHashMapDataStore();
    }

    @Test
    public void checkIfAdditionToMapIsNotNull(){
        this.stringHashMapDataStore.addToDataStore(keyValueObject);
        Assertions.assertNotNull(this.stringHashMapDataStore.getDataStore());
    }
    @Test
    public void checkIfAdditionOfKeyToMapExists(){
        this.stringHashMapDataStore.addToDataStore(keyValueObject);
        Assertions.assertTrue(this.stringHashMapDataStore.getDataStore().containsKey(keyValueObject.key));
    }

}
