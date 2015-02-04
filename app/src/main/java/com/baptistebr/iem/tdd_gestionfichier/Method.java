package com.baptistebr.iem.tdd_gestionfichier;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

/**
 * Created by iem on 04/02/15.
 */
public class Method {

    public static final String DOWNLOAD = "download";
    public static final String FILTRE = "TDD_GestionFichier";
    public static final String URL_LISTE = "http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?serial=AAA&type=medias";
    public static final String URL_MEDIA = "http://lionel.banand.free.fr/lp_iem";
    public static final String URI = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TDD_GestionFichier/";

    public static boolean testerEtatConnexion(Context context){
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null & networkInfo.isAvailable() && networkInfo.isConnected());
        }
        catch (Exception e) {
            Log.v(FILTRE, "testerEtatConnexion : false");
        }
        return false;
    }
}
