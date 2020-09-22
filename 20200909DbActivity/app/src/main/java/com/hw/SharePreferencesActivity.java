package com.hw;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharePreferencesActivity extends AppCompatActivity {

    private EditText spKey;
    private EditText spValue;
    private Button spRead;
    private Button spWrite;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);

        spKey = (EditText) findViewById(R.id.spKey);
        spValue = (EditText) findViewById(R.id.spValue);
        spRead = (Button) findViewById(R.id.spRead);
        spWrite = (Button) findViewById(R.id.spWrite);


        /*
        Context.MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中。可以使用Context.MODE_APPEND
        Context.MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
        MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
        MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
         */
        sp = getSharedPreferences("yangpengfei", Context.MODE_PRIVATE);
    }

    /**
     * 写入SharePreferences
     *
     * @param v
     */
    public void spWrite(View v) {
        SharedPreferences.Editor editor = sp.edit();
        String key = spKey.getText().toString();
        String value = spValue.getText().toString();
        editor.putString(key, value).commit();

        Toast.makeText(SharePreferencesActivity.this, "save completed!", Toast.LENGTH_SHORT).show();
    }

    /**
     * 从SharePreferences读取
     *
     * @param v
     */
    public void spRead(View v) {
        String spKeyStr = spKey.getText().toString();

        String spValueStr = sp.getString(spKeyStr, null);
        if (spValueStr != null) {
            spValue.setText(spValueStr);
        } else {
            Toast.makeText(SharePreferencesActivity.this, "null", Toast.LENGTH_SHORT).show();
        }
    }
}