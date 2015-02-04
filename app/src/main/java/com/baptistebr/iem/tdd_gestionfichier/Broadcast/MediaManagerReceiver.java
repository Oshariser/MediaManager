package com.baptistebr.iem.tdd_gestionfichier.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baptistebr.iem.tdd_gestionfichier.Service.MediaManagerService;

/**
 * Created by iem on 04/02/15.
 */
public class MediaManagerReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action != null){
            context.startService(new Intent(context, MediaManagerService.class));
        }
    }
}
