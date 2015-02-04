package com.baptistebr.iem.tdd_gestionfichier;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.baptistebr.iem.tdd_gestionfichier.DAO.MediaObjectDAO;
import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by root on 2/4/15.
 */
public class DownloadMedia extends AsyncTask<MediaObject,Object,Bitmap> {
    ProgressDialog mPD;
    MediaObject mMediaObject;
    Context mContext;
    public DownloadMedia(ProgressDialog aPD, Context aContext) {
        mPD = aPD;
        mContext = aContext;
    }

    @Override
    protected void onPreExecute() {
        mPD.setMessage("Downloading media...");
        mPD.show();
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(MediaObject... params) {
        mMediaObject = params[0];
        try {
            URL url = new URL(Method.URL_MEDIA + mMediaObject.path);
            File extStore = Environment.getExternalStorageDirectory();
            File file = new File(extStore, mMediaObject.name);

            long startTime = System.currentTimeMillis();
            Log.d("ImageManager", "download begining");
            Log.d("ImageManager", "download url:" + url);
            Log.d("ImageManager", "downloaded file name:");
                        /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();

                        /*
                         * Define InputStreams to read from the URLConnection.
                         */
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

                        /*
                         * Read bytes to the Buffer until there is nothing more to read(-1).
                         */
            ByteArrayBuffer baf = new ByteArrayBuffer(1024);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }

                        /* Convert the Bytes read to a String. */
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baf.toByteArray());
            fos.close();
            Log.d("ImageManager", "download ready in"
                    + ((System.currentTimeMillis() - startTime) / 1000)
                    + " sec");

        } catch (IOException e) {
            Log.e("ImageManager", "Error: " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap aBitmap) {
        MediaObjectDAO bdd = new MediaObjectDAO(mContext);
        bdd.open();
        mMediaObject.download = 1;
        bdd.modifierMediaObject(mMediaObject);
        bdd.close();
        mPD.dismiss();
        super.onPostExecute(aBitmap);
    }
}
