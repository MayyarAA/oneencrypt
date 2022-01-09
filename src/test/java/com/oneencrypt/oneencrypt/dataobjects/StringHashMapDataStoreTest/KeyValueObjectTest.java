package com.oneencrypt.oneencrypt.dataobjects.StringHashMapDataStoreTest;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeyValueObjectTest {
    KeyValueObject keyValueObject;
    String KEY_TEST_VALUE ="keyTest";
    String VALUE_TEST_VALUE ="valueTest";
    @BeforeEach
    public void setup(){
        this.keyValueObject = new KeyValueObject();
    }
    @Test
    public void doesSetKeyWork(){
        keyValueObject.setKey(KEY_TEST_VALUE);
        Assertions.assertTrue(keyValueObject.getKey()==KEY_TEST_VALUE);
    }

    @Test
    public void doesSetValueWork(){
        keyValueObject.setValue(VALUE_TEST_VALUE);
        Assertions.assertTrue(keyValueObject.getValue()==VALUE_TEST_VALUE);
    }
}
