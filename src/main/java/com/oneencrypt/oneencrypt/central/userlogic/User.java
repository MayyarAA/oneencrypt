package com.oneencrypt.oneencrypt.central.userlogic;

import com.oneencrypt.oneencrypt.central.FileObject;

import java.util.*;

public abstract class User {
    String name;
    String id;
    ArrayList<FileObject> listOfFiles;
    HashMap<String,String> keyValueMap;
}
