package com.hw;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DbHelper";

    public DbHelper(@Nullable Context context, int version) {
        /* context 上下文
           name 数据库名
           factory 游标对象
           version 数据库版本号
         */
        super(context, "huawei.db", null, 1);
    }

    //创建表，插入3条数据
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement , name varchar ,age int)");
        db.execSQL("insert into person(name , age) values ('yang' , 12)");
        db.execSQL("insert into person(name , age) values ('peng' , 13)");
        db.execSQL("insert into person(name , age) values ('fei' , 14)");

        Log.i(TAG, "----------------> onCreate");
    }

    //当传入的版本号大于现有数据的版本号的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "----------------> onUpgrade");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
