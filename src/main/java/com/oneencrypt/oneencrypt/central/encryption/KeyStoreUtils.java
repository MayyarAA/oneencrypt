package com.oneencrypt.oneencrypt.central.encryption;
import ch.qos.logback.core.encoder.EchoEncoder;
import com.oneencrypt.oneencrypt.central.inputlogic.FileInput;

import static org.apache.commons.codec.binary.Hex.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;

public class KeyStoreUtils {

    public static char[] decodeKey(SecretKey key){
        char[] hex = encodeHex(key.getEncoded());
        return  hex;
    }
    public static void saveKey(SecretKey key, File file){
        try{
            char[] hex = encodeHex(key.getEncoded());
//            writeToFileService(file,String.valueOf(hex));
        }catch(Exception e){
            System.out.println("Error from KeyStoreUtils.saveKey");
            e.printStackTrace();
        }
    }
    public static SecretKey loadKey(File file){
        String data = new String(FileInput.readFileKeyToByteArray(file));
        if(data==null){
            System.out.println("Error secretKey not read");
        }
        byte[] encoded;
        try{
            encoded = decodeHex(data.toCharArray());
        }catch(Exception e){
            System.out.println("Error from KeyStoreUtils.loadKey");
            e.printStackTrace();
            return null;
        }
        return new SecretKeySpec(encoded,"AES/CBC/PKCS5Padding");
    }
    public static SecretKey loadKey(String hexKey){
        String data = new String(hexKey);
        if(data==null){
            System.out.println("Error secretKey not read");
        }
        byte[] encoded;
        try{
            encoded = decodeHex(data.toCharArray());
        }catch(Exception e){
            System.out.println("Error from KeyStoreUtils.loadKey");
            e.printStackTrace();
            return null;
        }
//        return new SecretKeySpec(encoded,"AES/CBC/PKCS5Padding");
        return new SecretKeySpec(encoded,"AES");
    }

    void writeToFileService(File file, String hex){

    }
}
