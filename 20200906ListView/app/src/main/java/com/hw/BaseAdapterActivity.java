package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hw.entity.Component;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity {

    private static final String TAG = "BaseAdapterActivity";

    private ListView base_list;
    List<Component> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        base_list = (ListView) findViewById(R.id.base_list);

        data = new ArrayList<>();
        data.add(new Component(R.drawable.f1, "title1", "content1"));
        data.add(new Component(R.drawable.f2, "title2", "content2"));
        data.add(new Component(R.drawable.f3, "title3", "content3"));
        data.add(new Component(R.drawable.f4, "title4", "content4"));
        data.add(new Component(R.drawable.f5, "title5", "content5"));
        data.add(new Component(R.drawable.f6, "title6", "content6"));
        data.add(new Component(R.drawable.f7, "title7", "content7"));
        data.add(new Component(R.drawable.f8, "title8", "content8"));
        data.add(new Component(R.drawable.f9, "title9", "content9"));
        data.add(new Component(R.drawable.f10, "title10", "content10"));

        MyAdapter myAdapter = new MyAdapter();
        base_list.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter {

        //返回集合中的数量
        @Override
        public int getCount() {
            return data.size();
        }

        //根据下标，返回集合中对应的值
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 根据下标，返回对应的view
         *
         * @param position    下标值
         * @param convertView 可复用的item对象
         * @param parent      ListView
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //View view = View.inflate(BaseAdapterActivity.this, R.layout.base_layout_item, null);
            //ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            //TextView titleTextView = (TextView) view.findViewById(R.id.title);
            //TextView contentTextView = (TextView) view.findViewById(R.id.content);

            //convertView类似缓存复用
            //加载item布局，获取view对象
            if (convertView == null) {
                Log.i(TAG, "getView: position=" + position + ";convertView=" + convertView);
                convertView = View.inflate(BaseAdapterActivity.this, R.layout.base_layout_item, null);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
            TextView contentTextView = (TextView) convertView.findViewById(R.id.content);

            //向view中赋值
            Component component = data.get(position);
            imageView.setImageResource(component.getIcon());
            titleTextView.setText(component.getTitle());
            contentTextView.setText(component.getContent());

            //return view;
            return convertView;
        }
    }
}