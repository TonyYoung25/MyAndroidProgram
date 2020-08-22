package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");

//        Toast.makeText(SecondActivity.this, data, Toast.LENGTH_SHORT);
        Log.d("SecondActivity", data);
    }
}