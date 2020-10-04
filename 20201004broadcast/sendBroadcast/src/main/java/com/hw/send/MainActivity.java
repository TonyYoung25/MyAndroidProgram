package com.hw.send;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hw.send.R;

public class MainActivity extends AppCompatActivity {

    public Button sendOneBtn;
    public Button sendOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendOneBtn = (Button) findViewById(R.id.sendOneBtn);
        sendOrderBtn = (Button) findViewById(R.id.sendOrderBtn);
    }

    public void sendOne(View v) {
        Intent intent = new Intent("com.hw.myReceiver1");
        intent.putExtra("msg", "ok");
        sendBroadcast(intent);
        Toast.makeText(MainActivity.this, "send one success", Toast.LENGTH_SHORT).show();
    }

    public void sendOrder(View v) {
        Intent intent = new Intent("com.hw.myReceiver1");
        intent.putExtra("msg", "ok");
        sendOrderedBroadcast(intent, null);
        Toast.makeText(MainActivity.this, "send order success", Toast.LENGTH_SHORT).show();
    }
}
