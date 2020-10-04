package com.hw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyRemoteService extends Service {
    //private static List<Student> list = null;
    private static final String TAG = "MyRemoteService";

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        list = new ArrayList<>();
//        list.add(new Student(1, "yang"));
//        list.add(new Student(2, "peng"));
//        list.add(new Student(3, "fei"));
//
//        Log.i(TAG, "==================>onCreate");
//    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "==================>onBind");
        //return new StudentService();
        return studentService;
        //return null;
    }

//    @Override
//    public boolean onUnbind(Intent intent) {
//        Log.i(TAG, "==================>onUnbind");
//        return super.onUnbind(intent);
//    }

    //编写远程服务实现
//    class StudentService extends IStudentService.Stub {
//        @Override
//        public Student getStudentInfoById(int id) throws RemoteException {
//            return list.get(id);
//        }
//    }
    public final IStudentService.Stub studentService = new IStudentService.Stub() {
        @Override
        public String getStudentInfoById(int id) throws RemoteException {
            return new Student(2, "peng").toString();
        }
    };
}
