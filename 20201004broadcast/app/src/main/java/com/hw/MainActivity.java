package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button registerBtn;
    private Button unregisterBtn;
    private Button abortBtn;
    public static boolean abortFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        unregisterBtn = (Button) findViewById(R.id.unregisterBtn);
        abortBtn = (Button) findViewById(R.id.abortBtn);
    }

    private MyReceiver2 myReceiver2 = null;

    //手动注册广播接收器
    public void register(View v) {

        myReceiver2 = new MyReceiver2();
        IntentFilter intentFilter = new IntentFilter("com.hw.myReceiver1");
        intentFilter.setPriority(1000);//设置广播接收的优先级
        registerReceiver(myReceiver2, intentFilter);
        Toast.makeText(MainActivity.this, "register ok", Toast.LENGTH_SHORT).show();
    }

    public void unregister(View v) {
        unregisterReceiver(myReceiver2);
        Toast.makeText(MainActivity.this, "unregister ok", Toast.LENGTH_SHORT).show();
    }

    public void abort(View v) {
        if (!abortFlag) {
            abortFlag = true;
        }else{
            abortFlag = false;
        }
    }
}