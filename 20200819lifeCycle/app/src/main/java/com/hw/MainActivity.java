package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String key = savedInstanceState.getString("data_key");
            Log.i(TAG, key);
        }

        Log.i(TAG, "---------------->MainActivity_onCreate");

        Button normalButton = (Button) findViewById(R.id.start_normal_activity);
        Button dialogButton = (Button) findViewById(R.id.start_dialog_activity);

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i(TAG, "---------------->MainActivity_onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "---------------->MainActivity_onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "---------------->MainActivity_onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "---------------->MainActivity_onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "---------------->MainActivity_onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "---------------->MainActivity_onRestart");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "--------------------->MainActivity_onSaveInstanceState";
        outState.putString("data_key", tempData);
    }
}