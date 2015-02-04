package com.baptistebr.iem.tdd_gestionfichier;

import android.content.ContentValues;
import android.os.Build;

/**
 * Created by iem on 03/12/14.
 */
public class MediaObjectDAO extends DAOBase {

    public static String TABLE_NOM = "media";
    public static String ID = "id";
    public static String NAME = "name";
    public static String VERSIONCODE = "versionCode";
    public static String PATH = "path";
    public static String TYPE = "type";

    public static String SCRIPT_CREATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOM
        + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + VERSIONCODE + " TEXT,"
        + PATH + " TEXT, " + TYPE + " TEXT);";
    public static String SCRIPT_SUPPRESSION_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    public void ajouterMediaObject(MediaObject media){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", media.name);
        contentValues.put("versionCode", media.versionCode);
        contentValues.put("path", media.path);
        contentValues.put("type", media.type);

    }

    public void modifierMediaObject(MediaObject media){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", media.name);
        contentValues.put("versionCode", media.versionCode);
        contentValues.put("path", media.path);
        contentValues.put("type", media.type);

    }
}
