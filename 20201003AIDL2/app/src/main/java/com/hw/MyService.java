package com.hw;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
        Log.i(TAG, "===========================>MyService Construction");
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG, "===========================>MyService onBind");
        return studentService;
    }

    public final IMyStudentService.Stub studentService = new IMyStudentService.Stub() {
        @Override
        public Student getStudentInfo(int id) throws RemoteException {
            return new Student(1,"yang");
        }
    };

}
