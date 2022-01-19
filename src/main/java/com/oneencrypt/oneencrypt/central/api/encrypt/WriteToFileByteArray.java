package com.oneencrypt.oneencrypt.central.api.encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteToFileByteArray {
    File file = new File("temp");
    public void writeToFileWithByteArray(byte[] array){
        try{
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(array);
            outputStream.close();
        }catch (Exception e){

        }
    }

}
