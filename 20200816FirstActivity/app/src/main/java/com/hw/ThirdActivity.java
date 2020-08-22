package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        //按钮监听事件，将data数据返回上一个Activity，销毁当前活动
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", "===> hello too !");
                setResult(RESULT_OK, intent);
                finish();//销毁当前活动
            }
        });
    }

    //按返回键，将data数据返回上一个Activity，销毁当前活动
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data", "===> hello too !");
        setResult(RESULT_OK, intent);
        finish();//销毁当前活动
    }
}