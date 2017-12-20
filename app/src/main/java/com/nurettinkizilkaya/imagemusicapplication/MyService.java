package com.nurettinkizilkaya.imagemusicapplication;

/**
 * Created by user on 17.11.2017.
 */

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";
    MediaPlayer player;
    MainActivity mainActivity=new MainActivity();
    int[] music={R.raw.ag_focus,R.raw.bm_sari,R.raw.m_yana,R.raw.mg_affet,R.raw.mj_dontcare,R.raw.r_work,R.raw.s_galiba,R.raw.t_hatasiz,R.raw.ts_look};
    public static int i;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {


        i=mainActivity.musicSelected;

        Log.d(TAG, "onCreate");

        player=MediaPlayer.create(this,music[i-1]);

        player.setLooping(false);




    }



    @Override
    public void onDestroy() {
        Toast.makeText(this, "Müzik Durduruldu..", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");


        player.stop();
    }



    @Override
    public void onStart(Intent intent, int startid) {

        Log.d(TAG, "onStart");
        if(!player.isPlaying()){
            Toast.makeText(getApplicationContext(),"Çalıyor..",Toast.LENGTH_SHORT).show();
        }
        player.start();



    }


}

