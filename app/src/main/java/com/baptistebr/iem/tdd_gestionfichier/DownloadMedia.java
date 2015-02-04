package com.baptistebr.iem.tdd_gestionfichier;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

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
public class DownloadMedia extends AsyncTask<String,Object,Bitmap> {
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
        /**try {
            URL url = new URL(Method.URL_MEDIA + params[0]);
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
        }*/

        try {
            URL url = new URL(Method.URL_MEDIA + params[0]);
            File extStore = Environment.getExternalStorageDirectory();
            File file = new File(extStore, params[1]);

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
        mPD.dismiss();
        super.onPostExecute(aBitmap);
    }
}
