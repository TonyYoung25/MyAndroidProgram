package com.hw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver1 extends BroadcastReceiver {

    private static final String TAG = "MyReceiver1";

    public MyReceiver1() {
        Log.i(TAG, "--------------------->MyReceiver1 Construction");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String result = intent.getStringExtra("msg");
        Log.i(TAG, "--------------------->MyReceiver1 onReceive: " + result);
    }
}
