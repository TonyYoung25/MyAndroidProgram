package com.hw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {

    private static final String TAG = "MyReceiver2";

    public void MyReceiver2(){
        Log.i(TAG, "=========================>MyReceiver2: Construction");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String result = intent.getStringExtra("msg");
        Log.i(TAG, "==============================>MyReceiver2 onReceive: " + result);

        //中断广播
        if(isOrderedBroadcast() && MainActivity.abortFlag){
            abortBroadcast();
        }
    }
}
