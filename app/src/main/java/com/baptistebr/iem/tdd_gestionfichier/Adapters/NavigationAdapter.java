package com.baptistebr.iem.tdd_gestionfichier.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baptistebr.iem.tdd_gestionfichier.Items.NavigationLigne;
import com.baptistebr.iem.tdd_gestionfichier.R;

import java.util.ArrayList;

/**
 * Created by IEM on 14/11/2014.
 */
public class NavigationAdapter extends BaseAdapter {

    ArrayList<NavigationLigne> alNavigationLigne;
    Context context;

    public NavigationAdapter(Context context, ArrayList<NavigationLigne> alNavigationLigne) {
        this.context = context;
        this.alNavigationLigne = alNavigationLigne;
    }


    @Override
    public int getCount() {
        return alNavigationLigne.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout relativeLayout;
        if(view == null) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.ligne_navigation, null);
        } else {
            relativeLayout = (RelativeLayout)view;
        }
        TextView jtv_nom = (TextView)relativeLayout.findViewById(R.id.tv_nom);
        ImageView jiv_icone = (ImageView)relativeLayout.findViewById(R.id.iv_icone);
        jtv_nom.setText(alNavigationLigne.get(i).nom);
        jiv_icone.setImageResource(alNavigationLigne.get(i).icone);
        return relativeLayout;
    }
}
