package com.oneencrypt.oneencrypt.central.api.encrypt;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/encrypt")
public class EncryptAPI {

    @PostMapping(path="encryptFileContent", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Resource> encryptFileContent(@ModelAttribute MultiPartFileObj multiPartFileObj) throws IOException{
    public ResponseEntity<Resource> encryptFileContent(@ModelAttribute MultipartFile multipartFile,@ModelAttribute String key ) throws IOException{
        //take in file of kv pair
        EncryptAPIServices encryptAPIServices = new EncryptAPIServices();
        encryptAPIServices
                .createFileAddValuesEncryptedWithByteArray(multipartFile.getBytes());
        try{
            ByteArrayResource resource = encryptAPIServices.createByteArrayResource();
            return ResponseEntity.ok()
                    .headers(encryptAPIServices.getHttpHeaders())
                    .contentLength(encryptAPIServices.getFileLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }catch (IOException ioException){
            ioException.printStackTrace();
            return new ResponseEntity<Resource>(HttpStatus.BAD_REQUEST);
        }
    }

    //add values w/ existing file
    @PostMapping(path = "encryptValues", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource> encryptValues(@RequestBody ArrayList<KeyValueObject> keyValueListObj) {
        if (keyValueListObj == null) return null;
        //create input reader
        EncryptAPIServices encryptAPIServices = new EncryptAPIServices();
        //create & write to file
        encryptAPIServices.createFileAddValuesEncrypted(keyValueListObj);
        try{
            ByteArrayResource resource = encryptAPIServices.createByteArrayResource();
            return ResponseEntity.ok()
                .headers(encryptAPIServices.getHttpHeaders())
                .contentLength(encryptAPIServices.getFileLength())
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
