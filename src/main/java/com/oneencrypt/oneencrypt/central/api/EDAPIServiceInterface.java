package com.oneencrypt.oneencrypt.central.api;

import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.inputlogic.APIInput;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class EDAPIServiceInterface {
    private HttpHeaders httpHeaders;
    public FileObject fileObject;
    private File file;
    public APIInput apiInput;

    public  long getFileLength(){return this.file.length();}
    public File getFile(){return this.file;}
    public void setFile(File file){
        this.file = file;
    }
    public abstract void createFileObjHelper();
    public void createHeaderHelperForFileReturn(){
        HttpHeaders header = new HttpHeaders();
        header.add(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + this.getFile().getName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        this.httpHeaders = header;
    }


    public HttpHeaders getHttpHeaders(){
        return this.httpHeaders;
    }
    public void setHttpHeaders(HttpHeaders httpHeaders){
        this.httpHeaders  = httpHeaders;
    }
    public File returnFile(){
        return this.file;
    }

    public ByteArrayResource createByteArrayResource() throws IOException {
        createHeaderHelperForFileReturn();
        Path path = Paths.get(getFile().getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return resource;
    }
}
