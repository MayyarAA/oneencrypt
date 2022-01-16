package com.oneencrypt.oneencrypt.central.api;

import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.inputlogic.APIInput;
import org.springframework.http.HttpHeaders;

import java.io.File;

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
    public void createHeaderHelperForFileReturn(String fileName){
        HttpHeaders header = new HttpHeaders();
        header.add(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        this.httpHeaders = header;
    }
    public HttpHeaders getHttpHeaders(){
        return this.httpHeaders;
    }
    public File returnFile(){
        return this.file;
    }
}
