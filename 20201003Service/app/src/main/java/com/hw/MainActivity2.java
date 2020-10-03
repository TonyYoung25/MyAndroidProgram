package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private Button bindServiceBtn;
    private Button unbindServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bindServiceBtn = (Button) findViewById(R.id.bindServiceBtn);
        unbindServiceBtn = (Button) findViewById(R.id.unbindServiceBtn);
    }


    ServiceConnection serviceConnection = null;

    public void bindService2(View v) {
        Intent intent = new Intent(MainActivity2.this, MyService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "------------------->onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "------------------->onServiceDisconnected");
            }
        };

        /*
        BIND_AUTO_CREATE
        BIND_DEBUG_UNBIND
        BIND_NOT_FOREGROUND
        BIND_ABOVE_CLIENT
        BIND_ALLOW_OOM_MANAGEMENT
        BIND_WAIVE_PRIORITY
        BIND_IMPORTANT
        BIND_ADJUST_WITH_ACTIVITY
         */
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        Log.i(TAG, "------------------->bind Service");
    }

    public void unbindService2(View v) {
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
            Log.i(TAG, "------------------->unbind Service");
        } else {

            Log.i(TAG, "------------------->Service is not bind");
        }
    }

}