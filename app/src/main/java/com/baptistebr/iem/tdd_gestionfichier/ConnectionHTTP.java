package com.baptistebr.iem.tdd_gestionfichier;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by iem on 03/12/14.
 */
public class ConnectionHTTP {

    private static String url = "http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?serial=AAA&type=medias";

    public static String recupererDonneesXML() {

        String resultat = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        Log.v("TDD_GestionFichier", "ConnectionHTTP/url : " + url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder stringBuilder = new StringBuilder();
            String ligne = null;

            while ((ligne = bufferedReader.readLine()) != null) {
                stringBuilder.append(ligne + "\n");
            }

            inputStream.close();
            resultat = stringBuilder.toString();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return resultat;
    }
}
