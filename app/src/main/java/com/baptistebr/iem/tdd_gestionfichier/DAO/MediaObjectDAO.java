package com.baptistebr.iem.tdd_gestionfichier.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;
import com.baptistebr.iem.tdd_gestionfichier.Method;

import java.util.ArrayList;

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
        + PATH + " TEXT, " + TYPE + " TEXT, " + Method.DOWNLOAD + " INTEGER);";
    public static String SCRIPT_SUPPRESSION_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOM + ";";

    public MediaObjectDAO(Context pContext) {
        super(pContext);
    }

    public void ajouterMediaObject(MediaObject media){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, media.name);
        contentValues.put(VERSIONCODE, media.versionCode);
        contentValues.put(PATH, media.path);
        contentValues.put(TYPE, media.type);
        contentValues.put(Method.DOWNLOAD, media.download);
        mDb.insert(TABLE_NOM, null, contentValues);
    }

    public void modifierMediaObject(MediaObject media){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, media.name);
        contentValues.put(VERSIONCODE, media.versionCode);
        contentValues.put(PATH, media.path);
        contentValues.put(TYPE, media.type);
        contentValues.put(Method.DOWNLOAD, media.download);
        mDb.update(TABLE_NOM, contentValues, ID + " = ?", new String[]{String.valueOf(media.id)});
    }

    public ArrayList<MediaObject> recupererListeMediaObject(String type){
        ArrayList<MediaObject> mediaObjectArrayList = new ArrayList<MediaObject>();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NOM + " WHERE " + TYPE + " = ?", new String[]{type});
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            MediaObject mediaObject = new MediaObject(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
            mediaObjectArrayList.add(mediaObject);
        }
        cursor.close();
        return mediaObjectArrayList;
    }

    public MediaObject recupererMediaObject(MediaObject mediaObject){
        MediaObject media = null;
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_NOM + " WHERE " + NAME + " = ? AND " + PATH + " = ? AND " + TYPE + " = ?", new String[]{mediaObject.name, mediaObject.path, mediaObject.type});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            media = new MediaObject(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
        }
        return media;
    }
}
