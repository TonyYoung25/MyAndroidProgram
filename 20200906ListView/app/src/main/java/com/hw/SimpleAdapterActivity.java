package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        listView = (ListView) findViewById(R.id.simple_list);

        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("icon", R.drawable.f1);
        map.put("title", "title1");
        map.put("content", "content1");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f2);
        map.put("title", "title2");
        map.put("content", "content2");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f3);
        map.put("title", "title3");
        map.put("content", "content3");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f4);
        map.put("title", "title4");
        map.put("content", "content4");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f5);
        map.put("title", "title5");
        map.put("content", "content5");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f6);
        map.put("title", "title6");
        map.put("content", "content6");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f7);
        map.put("title", "title7");
        map.put("content", "content7");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f8);
        map.put("title", "title8");
        map.put("content", "content8");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f9);
        map.put("title", "title9");
        map.put("content", "content9");
        data.add(map);

        map = new HashMap<>();
        map.put("icon", R.drawable.f10);
        map.put("title", "title10");
        map.put("content", "content10");
        data.add(map);

        //映射关系
        String[] from = {"icon", "title", "content"};
        int[] to = {R.id.icon, R.id.title, R.id.content};

        SimpleAdapter simpleAdapter = new SimpleAdapter(SimpleAdapterActivity.this, data, R.layout.simple_layout_item, from, to);
        listView.setAdapter(simpleAdapter);
    }
}