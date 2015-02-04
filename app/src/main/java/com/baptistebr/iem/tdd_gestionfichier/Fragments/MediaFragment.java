package com.baptistebr.iem.tdd_gestionfichier.Fragments;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baptistebr.iem.tdd_gestionfichier.Adapters.AdapterMedia;
import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;
import com.baptistebr.iem.tdd_gestionfichier.R;

public class MediaFragment extends Fragment {

    public TextView jtv_test;
    public ImageView jiv_test;
    ListView mListViewMedia;

    public String texte;
    public int icone;

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
        return rootView;
    }

    public void setFragmentParameters(String text, int icon) {
        this.texte = text;
        this.icone = icon;

        //ArrayAdapter<MediaObject> adapter = new AdapterMedia(this, );
        //mListViewMedia.setAdapter(adapter);
    }
}
