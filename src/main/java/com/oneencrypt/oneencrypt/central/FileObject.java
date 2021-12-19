package com.oneencrypt.oneencrypt.central;

import java.io.File;
import com.oneencrypt.oneencrypt.central.CreateFileService;
import java.util.Random;

public class FileObject {
    File file;
    String filePathName;

    public FileObject(File file, String filePathName){
        this.file = file;
        this.filePathName = filePathName;
    }

    public FileObject(String filePathName){
        this.file = new File(filePathName);
        this.filePathName = filePathName;
    }
    protected  boolean checkIfFileIsWorkable(){
        if(!this.file.canWrite()) return false;
        if(!this.file.canRead()) return false;
        return true;
    }
    protected  boolean checkIfFileExists(){
        if(this.file.exists()){
            return true;
        }
        return false;
    }
    protected void createFile(){
        CreateFileService.createFile(this.filePathName);
    }
}
