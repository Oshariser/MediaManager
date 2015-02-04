package com.baptistebr.iem.tdd_gestionfichier.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.baptistebr.iem.tdd_gestionfichier.DAO.DatabaseHandler;

/**
 * Created by iem on 03/12/14.
 */
public abstract class DAOBase {

    protected final static int VERSION = 1;
    protected final static String NOM = "media";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
