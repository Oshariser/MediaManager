package com.baptistebr.iem.tdd_gestionfichier;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;
import com.baptistebr.iem.tdd_gestionfichier.DAO.MediaObjectDAO;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by iem on 03/12/14.
 */
public class MediaManager extends AsyncTask<Object, Void, ArrayList<MediaObject>>{

    private Context mContext;

    @Override
    protected ArrayList<MediaObject> doInBackground(Object... params) {
        mContext = (Context) params[0];
        ArrayList<MediaObject> donnees = null;
        String resultat = ConnectionHTTP.recupererDonneesXML();
        Log.v("TDD_GestionFichier", "MediaManager/resultat : " + resultat);
        try {
            donnees = ParserXML.recupererDonnesParsees(resultat);
            Log.v("TDD_GestionFichier", "MediaManager/donnees.size() : " + donnees.size());
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return donnees;
    }

    @Override
    protected void onPostExecute(ArrayList<MediaObject> alMediaObject) {
        super.onPostExecute(alMediaObject);
        MediaObjectDAO mediaObjectDAO = new MediaObjectDAO(mContext);
        mediaObjectDAO.open();
        for(MediaObject mediaObject : alMediaObject){
            mediaObjectDAO.ajouterMediaObject(mediaObject);
        }
        mediaObjectDAO.close();
    }
}
