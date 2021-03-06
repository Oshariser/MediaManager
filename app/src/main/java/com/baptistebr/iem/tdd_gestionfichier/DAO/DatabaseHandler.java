package com.baptistebr.iem.tdd_gestionfichier.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.baptistebr.iem.tdd_gestionfichier.Method;

/**
 * Created by iem on 03/12/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MediaObjectDAO.SCRIPT_CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MediaObjectDAO.SCRIPT_SUPPRESSION_TABLE);
        onCreate(db);
    }
}
