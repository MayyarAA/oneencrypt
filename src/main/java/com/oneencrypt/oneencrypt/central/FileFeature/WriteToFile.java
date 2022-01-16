package com.oneencrypt.oneencrypt.central.FileFeature;

import com.oneencrypt.oneencrypt.central.FileObject;
import com.oneencrypt.oneencrypt.central.inputlogic.Input;

public abstract class WriteToFile {
    FileObject fileObj;
    Input input;
    public WriteToFile(FileObject fileObj , Input input ){
        this.fileObj = fileObj;
        this.input = input;
    }
    protected abstract void writeToFile() throws Exception;
    public abstract void writeAndCreateFile();
}
