package com.baptistebr.iem.tdd_gestionfichier;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by iem on 03/12/14.
 */
public class MediaManager {

    public static void synchroniserDonnees() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resultat = ConnectionHTTP.recupererDonneesXML();
                Log.v("TDD_GestionFichier", "MediaManager/resultat : " + resultat);
                try {
                    ArrayList<MediaObject> donnees = ParserXML.recupererDonnesParsees(resultat);
                    Log.v("TDD_GestionFichier", "MediaManager/donnees.size() : " + donnees.size());
                }
                catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
