package com.hw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
        Log.i(TAG, "------------------>MyService Construction");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "------------------>MyService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "------------------>MyService onDestroy");

    }

    //------------------------------------------------------

    @Override
    public IBinder onBind(Intent intent) {

        return new Binder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "------------------>MyService onUnbind");
        return super.onUnbind(intent);
    }
}
