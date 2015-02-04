package com.baptistebr.iem.tdd_gestionfichier.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;
import com.baptistebr.iem.tdd_gestionfichier.R;


import java.util.List;

/**
 * Created by root on 1/8/15.
 */
public class AdapterMedia extends ArrayAdapter<MediaObject>{

    private final List<MediaObject> mMedias;
    private Activity mContext;

    public AdapterMedia(Activity aContext, List<MediaObject> aMedias) {
        super(aContext, R.layout.ligne_media, aMedias);
        this.mContext = aContext;
        this.mMedias = aMedias;
    }

    static class ViewHolder {
        protected TextView textView;
        protected Button buttonDownload;
        protected Button buttonUpdate;
        protected Button buttonDelete;
    }

    @Override
    public View getView(final int aPosition, View aConvertView, ViewGroup aParent) {
        View lView = null;
        if (aConvertView == null) {
            LayoutInflater lInflator = mContext.getLayoutInflater();
            lView = lInflator.inflate(R.layout.ligne_media, null);
            final ViewHolder lViewHolder = new ViewHolder();
            lViewHolder.textView = (TextView) lView.findViewById(R.id.tvPath);
            lViewHolder.buttonDownload = (Button) lView.findViewById(R.id.buttonDownload);
            lViewHolder.buttonUpdate = (Button) lView.findViewById(R.id.buttonUpdate);
            lViewHolder.buttonDelete = (Button) lView.findViewById(R.id.buttonDelete);

            lViewHolder.buttonDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            lViewHolder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            lViewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            lView.setTag(lViewHolder);
            lViewHolder.buttonDownload.setTag(mMedias.get(aPosition));
            lViewHolder.buttonUpdate.setTag(mMedias.get(aPosition));
            lViewHolder.buttonDelete.setTag(mMedias.get(aPosition));
        } else {
            lView = aConvertView;
            ((ViewHolder) lView.getTag()).buttonDownload.setTag(mMedias.get(aPosition));
            ((ViewHolder) lView.getTag()).buttonUpdate.setTag(mMedias.get(aPosition));
            ((ViewHolder) lView.getTag()).buttonDelete.setTag(mMedias.get(aPosition));
        }
        ViewHolder holder = (ViewHolder) lView.getTag();
        holder.textView.setText(mMedias.get(aPosition).path);

        return lView;
    }


}
