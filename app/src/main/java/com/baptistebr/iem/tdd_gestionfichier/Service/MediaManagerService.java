package com.baptistebr.iem.tdd_gestionfichier.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baptistebr.iem.tdd_gestionfichier.MediaManager;

/**
 * Created by iem on 04/02/15.
 */
public class MediaManagerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaManager mediaManager = new MediaManager();
        mediaManager.execute(this);
        return super.onStartCommand(intent, flags, startId);
    }
}
