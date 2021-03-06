package com.baptistebr.iem.tdd_gestionfichier;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<MediaObject> doInBackground(Object... params) {
        mContext = (Context) params[0];
        ArrayList<MediaObject> donnees = null;
        if(Method.testerEtatConnexion(mContext)) {
            String resultat = ConnectionHTTP.recupererDonneesXML(Method.URL_LISTE);
            //Log.v(Method.FILTRE, "MediaManager/resultat : " + resultat);
            try {
                donnees = ParserXML.recupererDonnesParsees(resultat);
                //Log.v(Method.FILTRE, "MediaManager/donnees.size() : " + donnees.size());
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return donnees;
    }

    @Override
    protected void onPostExecute(ArrayList<MediaObject> alMediaObject) {
        super.onPostExecute(alMediaObject);
        if(alMediaObject != null) {
            MediaObjectDAO mediaObjectDAO = new MediaObjectDAO(mContext);
            mediaObjectDAO.open();
            for (MediaObject mediaObject : alMediaObject) {
                MediaObject m = mediaObjectDAO.recupererMediaObject(mediaObject);
                if (m != null) {
                    if (!m.versionCode.equals(mediaObject.versionCode)) {
                        mediaObject.id = m.id;
                        mediaObjectDAO.modifierMediaObject(mediaObject);
                    }
                } else {
                    mediaObjectDAO.ajouterMediaObject(mediaObject);
                }
            }
            mediaObjectDAO.close();
            Intent intent = new Intent();
            intent.setAction(Method.DOWNLOAD);
            mContext.sendBroadcast(intent);
        }
        else{
            Toast.makeText(mContext, "Aucune connexion internet detectée ... Impossible de synchroniser les données !", Toast.LENGTH_LONG).show();
        }
    }
}
