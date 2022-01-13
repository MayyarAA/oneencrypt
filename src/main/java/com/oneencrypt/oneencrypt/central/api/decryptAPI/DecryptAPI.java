package com.oneencrypt.oneencrypt.central.api.decryptAPI;

import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObject;
import com.oneencrypt.oneencrypt.central.dataobjects.KeyValueObjectList;
import org.apache.coyote.Response;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/decrypt")
public class DecryptAPI {
    @PostMapping(path = "decryptStrings")
    //return ArrayList<KeyValueObject> for now temp
    public ResponseEntity<ArrayList<KeyValueObject>> decryptStrings(@RequestBody KeyValueObjectList keyValueObjectList){
        //parse out encryptionKey
        DecryptAPIService decryptAPIService = new DecryptAPIService();
        try{
            ArrayList<KeyValueObject> decryptedKeyValueObjectsArrayList = decryptAPIService.decryptStringServices(keyValueObjectList);
            return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(decryptedKeyValueObjectsArrayList) ;
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path = "decryptStringsToFile")
    //return ArrayList<KeyValueObject> for now temp
    public ResponseEntity<Resource> decryptStringsToFile(@RequestBody KeyValueObjectList keyValueObjectList){
        //parse out encryptionKey
        DecryptAPIService decryptAPIService = new DecryptAPIService();
        try{
            ArrayList<KeyValueObject> decryptedKeyValueObjectsArrayList = decryptAPIService.decryptStringServices(keyValueObjectList);
            decryptAPIService.createFileAddValuesEncrypted(decryptedKeyValueObjectsArrayList);
            File file = decryptAPIService.returnFile();
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            HttpHeaders header = decryptAPIService.createHeaderHelperForFileReturn(file.getName());
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }catch(Exception e){
            return new ResponseEntity<Resource>(HttpStatus.BAD_REQUEST);
        }
    }
}
