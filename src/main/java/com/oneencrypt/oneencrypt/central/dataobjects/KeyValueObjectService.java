package com.oneencrypt.oneencrypt.central.dataobjects;

import java.util.ArrayList;

public class KeyValueObjectService {

    public static ArrayList<KeyValueObject> convertStringToKeyValueObject(String keyValueString){
        String lines[] = keyValueString.split("\\r?\\n");
        ArrayList<KeyValueObject> keyValueObjectArrayList = new ArrayList<>();
        for(int i=0;i<lines.length;i++){
            ///remove all whitespce
            String curr = lines[i].replaceAll("\\s+","");
            String[] keyValyeSplit = curr.split(":");
            String key = (keyValyeSplit.length<=0||keyValyeSplit[0].isBlank()
                    ||keyValyeSplit[0].isEmpty()
                    ?"emptyKey":keyValyeSplit[0]);
            String value = (keyValyeSplit.length<=1||keyValyeSplit[1].isBlank()
                    ||keyValyeSplit[1].isEmpty()
                    ?"emptyValue":keyValyeSplit[1]);

            KeyValueObject currKeyValueObject = new KeyValueObject(key,value);
            keyValueObjectArrayList.add(currKeyValueObject);
        }
        return keyValueObjectArrayList;
    }
}
