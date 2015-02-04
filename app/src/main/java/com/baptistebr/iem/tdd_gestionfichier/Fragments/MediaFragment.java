package com.baptistebr.iem.tdd_gestionfichier.Fragments;



import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baptistebr.iem.tdd_gestionfichier.Adapters.AdapterMedia;
import com.baptistebr.iem.tdd_gestionfichier.DAO.MediaObjectDAO;
import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;
import com.baptistebr.iem.tdd_gestionfichier.R;

import java.util.ArrayList;
import java.util.List;

public class MediaFragment extends Fragment {

    public TextView jtv_test;
    public ImageView jiv_test;
    ListView mListViewMedia;

    public String texte;
    public int icone;
    private Activity mActivity;
    private String mType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media, container, false);
        jtv_test = (TextView) rootView.findViewById(R.id.tv_texte);
        jiv_test = (ImageView) rootView.findViewById(R.id.iv_icone);
        mListViewMedia = (ListView) rootView.findViewById(R.id.listViewMedia);
        if(texte != null) {
            jtv_test.setText(texte);
            jiv_test.setImageResource(icone);
        }

        List<MediaObject> lMedias;

        MediaObjectDAO bdd = new MediaObjectDAO(mActivity.getApplicationContext());
        bdd.open();
        lMedias = bdd.recupererListeMediaObject(mType);
        ArrayAdapter<MediaObject> adapter = new AdapterMedia(mActivity, lMedias);
        bdd.close();
        mListViewMedia.setAdapter(adapter);
        return rootView;
    }

    public void setFragmentParameters(String text, int icon, Activity aContext, String type) {
        this.texte = text;
        this.icone = icon;
        this.mActivity = aContext;
        this.mType = type;
    }
}
