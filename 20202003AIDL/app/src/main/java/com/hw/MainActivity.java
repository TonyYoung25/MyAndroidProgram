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
import android.widget.EditText;
import android.widget.Toast;

import lombok.ToString;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ClientMainActivity";

    private Button bindRemoteServiceBtn;
    private Button transferServiceBtn;
    private EditText idText;
    private Button unbindRemoteServiceBtn;

    private ServiceConnection serviceConnection;
    private IStudentService studentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindRemoteServiceBtn = (Button) findViewById(R.id.bindRemoteServiceBtn);
        transferServiceBtn = (Button) findViewById(R.id.transferServiceBtn);
        idText = (EditText) findViewById(R.id.idText);
        idText.setText("1");
        unbindRemoteServiceBtn = (Button) findViewById(R.id.unbindRemoteServiceBtn);
    }

    //绑定服务
    public void bindRemoteService(View v) {
        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.hw" , "MyRemoteService"));
        intent.setAction("com.hw.myremoteservice");
        intent.setPackage("com.hw");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "------------------->onServiceConnected");
                studentService = IStudentService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "------------------->onServiceDisconnected");
                studentService = null;
            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    //远程调用服务
    public void transferService(View v) throws RemoteException {
        if (studentService != null) {
            String student = studentService.getStudentInfoById(Integer.parseInt(idText.getText().toString()));
            Toast.makeText(MainActivity.this, student.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //解绑服务
    public void unbindRemoteService(View v) {
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
            studentService = null;
            Log.i(TAG, "------------------->unbindRemoteService");
        } else {
            Log.i(TAG, "------------------->RemoteService is not bind");
        }
    }

}