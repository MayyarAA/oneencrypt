package com.oneencrypt.oneencrypt.central.api.encrypt;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/encrypt")
public class EncryptAPI {

    //add values w/ existing file
    @PostMapping(path = "addValues", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource> addValues(@RequestBody ArrayList<KeyValueObject> keyValueListObj) {
        if (keyValueListObj == null) return null;
        //create input reader
        EncryptAPIServices inputAPIServices = new EncryptAPIServices();
        //create & write to file
        inputAPIServices.createFileAddValuesEncrypted(keyValueListObj);
        File file = inputAPIServices.returnFile();
        //return file
        HttpHeaders header = inputAPIServices.createHeaderHelperForFileReturn(file.getName());
        try{
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
        }catch (IOException ioException){
            ioException.printStackTrace();
            return new ResponseEntity<Resource>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "addValue", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String addValue(@RequestBody KeyValueObject keyValueObject) {

        return "String from addValues" + keyValueObject.getValue();
    }
}
