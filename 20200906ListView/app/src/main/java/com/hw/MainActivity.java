package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button arrayAdapterBtn;
    private Button simpleAdapterBtn;
    private Button baseAdapterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapterBtn = (Button)findViewById(R.id.arrayAdapterBtn);
        simpleAdapterBtn = (Button)findViewById(R.id.simpleAdapterBtn);
        baseAdapterBtn = (Button)findViewById(R.id.baseAdapterBtn);

        arrayAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ArrayAdapterActivity.class);
                startActivity(intent);
            }
        });

        simpleAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SimpleAdapterActivity.class);
                startActivity(intent);
            }
        });

        baseAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BaseAdapterActivity.class);
                startActivity(intent);
            }
        });
    }
}