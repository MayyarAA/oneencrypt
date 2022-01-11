package com.oneencrypt.oneencrypt.central.api.decryptAPI;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObjectList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/decrypt")
public class DecryptAPI {
    @PostMapping(path = "decryptStrings")
    public String decryptStrings(@RequestBody KeyValueObjectList keyValueObjectList){

        return keyValueObjectList.getEncryptionKey() + " " + keyValueObjectList.getKeyValueObjectsLists().get(0).getValue();
    }
}
