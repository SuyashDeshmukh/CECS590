package edu.csulb.android.arttherapy;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class PlaySound extends Service {
    public static boolean boolIsServiceCreated= false;
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        boolIsServiceCreated = true;
        player = MediaPlayer.create(getApplicationContext(), R.raw.sounds);
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startid) {
         player.start();
        //player.setLooping(false);
       return START_STICKY;
    }
}
