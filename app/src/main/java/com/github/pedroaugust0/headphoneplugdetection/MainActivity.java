package com.github.pedroaugust0.headphoneplugdetection;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = this;
    }


    public void onStopMonitor(View view){

//        bindService(new Intent("MonitorsHeadphonePlug", connection, 0));
        Intent stopMonitor = new Intent(this, MonitorsHeadphonePlug.class);
        stopService(stopMonitor);
        Toast.makeText(this, "Monitor OFF", Toast.LENGTH_SHORT).show();

    }

    public void onRestartMonitor(View view){

        Intent startService = new Intent(this, MonitorsHeadphonePlug.class);
        this.startService(startService);
        Toast.makeText(this, "Monitor ON", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
