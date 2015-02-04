package com.baptistebr.iem.tdd_gestionfichier;

import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by iem on 03/12/14.
 */
public class ParserXML {

    public static ArrayList<MediaObject> recupererDonnesParsees (String resultat) throws XmlPullParserException, IOException {

        ArrayList<MediaObject> listeMedias = new ArrayList<MediaObject>();
        MediaObject media = null;

        XmlPullParserFactory xppFactory = XmlPullParserFactory.newInstance();
        xppFactory.setNamespaceAware(true);
        XmlPullParser xpParser = xppFactory.newPullParser();

        xpParser.setInput(new StringReader(resultat));
        int eventType = xpParser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if(xpParser.getName().equalsIgnoreCase("media")) {
                        media = new MediaObject(xpParser.getAttributeValue(null, "name"),
                                xpParser.getAttributeValue(null, "versionCode"),
                                xpParser.getAttributeValue(null, "path"),
                                xpParser.getAttributeValue(null, "type"), 0);
                        listeMedias.add(media);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
                case XmlPullParser.TEXT:
                    break;
            }
            eventType = xpParser.next();
        }
        return listeMedias;
    }
}
