package com.baptistebr.iem.tdd_gestionfichier.DAO.Objects;

/**
 * Created by iem on 03/12/14.
 */
public class MediaObject {

    public long id;
    public String name;
    public String versionCode;
    public String path;
    public String type;
    public int download;

    public MediaObject(){}

    public MediaObject(String name, String versionCode, String path, String type, int download){
        this.name = name;
        this.versionCode = versionCode;
        this.path = path;
        this.type = type;
        this.download = download;
    }

    public MediaObject(long id, String name, String versionCode, String path, String type, int download){
        this.id = id;
        this.name = name;
        this.versionCode = versionCode;
        this.path = path;
        this.type = type;
        this.download = download;
    }


}
