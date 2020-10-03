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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button testBtn;
    private Button startServiceBtn;
    private Button stopServiceBtn;
    private Button bindServiceBtn;
    private Button unbindServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
        stopServiceBtn = (Button) findViewById(R.id.stopServiceBtn);
        bindServiceBtn = (Button) findViewById(R.id.bindServiceBtn);
        unbindServiceBtn = (Button) findViewById(R.id.unbindServiceBtn);

        testBtn = (Button) findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    public void startService1(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
        Log.i(TAG, "------------------->startService");
    }

    public void stopService1(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        stopService(intent);
        Log.i(TAG, "------------------->stopService");
    }

    //--------------------------------------------------
    ServiceConnection serviceConnection = null;

    public void bindService1(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
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

    public void unbindService1(View v) {
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
            Log.i(TAG, "------------------->unbind Service");
        } else {

            Log.i(TAG, "------------------->Service is not bind");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
            Log.i(TAG, "===================>unbind Service");
        } else {

            Log.i(TAG, "===================>Service is not bind");
        }
    }
}