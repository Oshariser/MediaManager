package com.baptistebr.iem.tdd_gestionfichier.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baptistebr.iem.tdd_gestionfichier.DAO.MediaObjectDAO;
import com.baptistebr.iem.tdd_gestionfichier.DAO.Objects.MediaObject;
import com.baptistebr.iem.tdd_gestionfichier.DownloadMedia;
import com.baptistebr.iem.tdd_gestionfichier.FileOpen;
import com.baptistebr.iem.tdd_gestionfichier.Fragments.MediaFragment;
import com.baptistebr.iem.tdd_gestionfichier.Method;
import com.baptistebr.iem.tdd_gestionfichier.R;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 1/8/15.
 */
public class AdapterMedia extends ArrayAdapter<MediaObject>{

    private final List<MediaObject> mMedias;
    private Activity mContext;
    ProgressDialog mPDMedia;

    public AdapterMedia(Activity aContext, List<MediaObject> aMedias) {
        super(aContext, R.layout.ligne_media, aMedias);
        this.mContext = aContext;
        this.mMedias = aMedias;
        mPDMedia = new ProgressDialog(aContext);
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

            if(mMedias.get(aPosition).download == 1) {
                lViewHolder.buttonDownload.setText("Open");
            } else {
                lViewHolder.buttonDelete.setVisibility(View.GONE);
            }
            lViewHolder.buttonUpdate.setVisibility(View.GONE);

            lViewHolder.buttonDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mMedias.get(aPosition).download == 1){
                        File extStore = Environment.getExternalStorageDirectory();
                        File file = new File(extStore, mMedias.get(aPosition).name);
                        try {
                            FileOpen.openFile(mContext, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        DownloadMedia lDownloadMedia = new DownloadMedia(mPDMedia, mContext);
                        lDownloadMedia.execute(mMedias.get(aPosition));
                    }

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
                    File extStore = Environment.getExternalStorageDirectory();
                    File file = new File(extStore, mMedias.get(aPosition).name);
                    file.delete();
                    mMedias.get(aPosition).download = 0;
                    MediaObjectDAO bdd = new MediaObjectDAO(mContext);
                    bdd.open();
                    bdd.modifierMediaObject(mMedias.get(aPosition));
                    Toast.makeText(mContext,"Media supprimer avec succées",Toast.LENGTH_LONG).show();
                    MediaFragment.fillListViewMedias();
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
