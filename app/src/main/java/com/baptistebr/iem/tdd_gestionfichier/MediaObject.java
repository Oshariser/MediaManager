package com.baptistebr.iem.tdd_gestionfichier;

/**
 * Created by iem on 03/12/14.
 */
public class MediaObject {

    public String name;
    public String versionCode;
    public String path;
    public String type;

    public MediaObject(){}

    public MediaObject(String name, String versionCode, String path, String type){
        this.name = name;
        this.versionCode = versionCode;
        this.path = path;
        this.type = type;
    }
}
