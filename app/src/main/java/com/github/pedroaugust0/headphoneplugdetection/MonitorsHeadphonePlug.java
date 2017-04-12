package com.github.pedroaugust0.headphoneplugdetection;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Class that is responsible for monitoring the plug of the headphone.
 */

public class MonitorsHeadphonePlug extends Service {

    private String LOG_TAG = ".MonitorsHeadphonePlug";

    private boolean alreadyOn = false;

    PlugReceiver plugReceiver = new PlugReceiver();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Not ussed, but implementation is necessary.
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(LOG_TAG, "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int stardID){

        if(!alreadyOn){
            IntentFilter plugFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
            registerReceiver(plugReceiver, plugFilter);
            alreadyOn = true;
            Log.i(LOG_TAG, "ON");
            return super.onStartCommand(intent, flags, stardID);
        }
        Log.i(LOG_TAG, "Already ON");
        return super.onStartCommand(intent, flags, stardID);
    }

    @Override
    public void onDestroy(){
        stopMonitor();
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy()");
    }

    public void stopMonitor(){
        unregisterReceiver(plugReceiver);
        alreadyOn = false;
        Log.i(LOG_TAG, "OFF");

    }

    private class PlugReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent){

            if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)){
                int state = intent.getIntExtra("state", -1);
                if(state == 1){
                    Log.i(LOG_TAG, "Headphone ON");
                } else if ( state == 0){
                    Log.i(LOG_TAG, "Headphone OFF");
                } else {
                    Log.i(LOG_TAG, "WTF?");
                }
            }
        }

    }

}
