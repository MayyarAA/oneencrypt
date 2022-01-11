package com.oneencrypt.oneencrypt.central.api.input;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/input")
public class InputAPI {

    //add values w/ existing file
    @PostMapping(path = "addValues", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String addValues(@RequestBody ArrayList<KeyValueObject> keyValueListObj) {
        if (keyValueListObj == null) return "Null KeyValueList";
        InputAPIServices inputAPIServices = new InputAPIServices();
        inputAPIServices.createFileAddValuesEncrypted(keyValueListObj);
        File file = inputAPIServices.returnFile();
        //how to return file
        return "String from addValues" + keyValueListObj.get(0).getValue();
    }

//    //add values w/ existing file
//    @PostMapping(path = "addValues", consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public String addValues(@RequestBody ArrayList<KeyValueObject> keyValueListObj) {
//        if (keyValueListObj == null) return "Null KeyValueList";
//        InputAPIServices inputAPIServices = new InputAPIServices();
//        inputAPIServices.createFileAddValuesEncrypted(keyValueListObj);
//        File file = inputAPIServices.returnFile();
//        //how to return file
//        return "String from addValues" + keyValueListObj.get(0).getValue();
//    }

    @PostMapping(path = "addValue", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String addValue(@RequestBody KeyValueObject keyValueObject) {

        return "String from addValues" + keyValueObject.getValue();
    }
}
