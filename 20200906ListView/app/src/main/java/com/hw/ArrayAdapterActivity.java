package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterActivity extends AppCompatActivity {

    private ListView arrayListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        arrayListView = (ListView) findViewById(R.id.array_list);

        String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "l", "m", "n"};//数据
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ArrayAdapterActivity.this, R.layout.array_layout_item, data);
        arrayListView.setAdapter(arrayAdapter);
    }
}