package com.oneencrypt.oneencrypt.central.inputlogic;

import java.util.*;

public class InputFactory {
    public Input getInputObject(String inputType){
        HashMap<String,Input> inputTypeMap = new HashMap<>();
        inputTypeMap.put("ConsoleInput",new ConsoleInput());
        inputTypeMap.put("FileInput",new FileInput());
        return inputTypeMap.getOrDefault(inputType,null);

    }
}
