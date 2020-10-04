package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hw.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ClientMainActivity";

    private IMyStudentService myStudentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bindBtn = (Button) findViewById(R.id.bindBtn);
        Button invokeBtn = (Button) findViewById(R.id.invokeBtn);

        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.hw.myservice");
                intent.setPackage("com.hw");

                ServiceConnection serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        myStudentService = IMyStudentService.Stub.asInterface(service);
                        Log.i(TAG, "------------------------->onServiceConnected");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };

                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        invokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Student result = myStudentService.getStudentInfo(1);

                    Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
