package com.baptistebr.iem.tdd_gestionfichier;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 2/4/15.
 */
public class DownloadMedia extends AsyncTask<String,Object,Object> {
    ProgressDialog mPD;

    public DownloadMedia(ProgressDialog aPD) {
        mPD = aPD;
    }

    @Override
    protected void onPreExecute() {
        mPD.setMessage("Downloading media...");
        mPD.show();
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            Log.e("DownloadMedia", e.getMessage());
            mPD.dismiss();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        mPD.dismiss();
        super.onPostExecute(o);
    }
}
