package com.oneencrypt.oneencrypt.central.api.input;

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
@RequestMapping(path = "api/v1/input")
public class InputAPI {

    //add values w/ existing file
    @PostMapping(path = "addValues", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource> addValues(@RequestBody ArrayList<KeyValueObject> keyValueListObj) throws IOException {
        if (keyValueListObj == null) return null;
        InputAPIServices inputAPIServices = new InputAPIServices();
        inputAPIServices.createFileAddValuesEncrypted(keyValueListObj);
        File file = inputAPIServices.returnFile();
        //how to return file
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
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
