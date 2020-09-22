package com.hw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button sharePreferencesBtn;
    private Button fileBtn;
    private Button sdcardBtn;
    private Button dbBtn;
    private Button httpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePreferencesBtn = (Button) findViewById(R.id.sharePreferencesBtn);
        sharePreferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SharePreferencesActivity.class);
                startActivity(intent);
            }
        });

        fileBtn = (Button) findViewById(R.id.fileBtn);
        fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FileActivity.class);
                startActivity(intent);
            }
        });

        sdcardBtn = (Button) findViewById(R.id.sdcardBtn);
        sdcardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SdcardActivity.class);
                startActivity(intent);
            }
        });

        dbBtn = (Button) findViewById(R.id.dbBtn);
        dbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbActivity.class);
                startActivity(intent);
            }
        });

        httpBtn = (Button) findViewById(R.id.httpBtn);
        httpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HttpActivity.class);
                startActivity(intent);
            }
        });
    }
}