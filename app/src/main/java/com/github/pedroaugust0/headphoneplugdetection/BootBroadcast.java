package com.github.pedroaugust0.headphoneplugdetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Class that receives when the boot has completed and begin monitoring the plug of the headphone.
 */

public class BootBroadcast extends BroadcastReceiver{

    private String LOG_TAG = ".BootBroadcast";

    @Override
    public void onReceive(Context context, Intent intent){
        Log.i(LOG_TAG, "Boot was Completed");

        Intent startService = new Intent(context, MonitorsHeadphonePlug.class);
        context.startService(startService);

    }

}
