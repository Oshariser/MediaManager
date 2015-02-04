package com.baptistebr.iem.tdd_gestionfichier;

import android.os.Environment;

/**
 * Created by iem on 04/02/15.
 */
public class Method {

    public static final String DOWNLOAD = "download";
    public static final String FILTRE = "TDD_GestionFichier";
    public static final String URL_LISTE = "http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?serial=AAA&type=medias";
    public static final String URL_MEDIA = "http://lionel.banand.free.fr/lp_iem";
    public static final String URI = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TDD_GestionFichier/";
}
