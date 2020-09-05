package com.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText numberEdit;
    private EditText msgEdit;
    private Button phoneCallBtn;
    private Button sendMsgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEdit = (EditText) findViewById(R.id.numberEdit);
        msgEdit = (EditText) findViewById(R.id.numberEdit);
        phoneCallBtn = (Button) findViewById(R.id.phoneCallBtn);
        sendMsgBtn = (Button) findViewById(R.id.sendMsgBtn);

        //点击
        phoneCallBtn.setOnClickListener(onClick);
        sendMsgBtn.setOnClickListener(onClick);

        //长按
        phoneCallBtn.setOnLongClickListener(onLongClick);
        sendMsgBtn.setOnLongClickListener(onLongClick);
    }

    //点击
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == phoneCallBtn) {//点击进入打电话界面

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + numberEdit.getText().toString()));
                startActivity(intent);

            } else if (v == sendMsgBtn) {//点击进入发短信界面
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + numberEdit.getText().toString()));
                intent.putExtra("sms_body", msgEdit.getText().toString());
                startActivity(intent);
            }
        }
    };

    //长按
    private View.OnLongClickListener onLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            if (v == phoneCallBtn) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + numberEdit.getText().toString()));
                startActivity(intent);

                return true;//表示已经被消费，不会再触发点击事件
            } else if (v == sendMsgBtn) {

//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(numberEdit.getText().toString(), null,
//                        msgEdit.getText().toString(), null, null);

                SmsManager.getDefault().sendTextMessage(numberEdit.getText().toString(), null,
                        msgEdit.getText().toString(), null, null);
                return true;
            }

            return false;
        }
    };

}