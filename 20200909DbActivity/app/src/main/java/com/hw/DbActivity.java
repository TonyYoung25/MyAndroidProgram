package com.hw;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DbActivity extends AppCompatActivity {

    private Button createDbBtn;
    private Button updateDbBtn;
    private Button addBtn;
    private Button updateBtn;
    private Button queryBtn;
    private Button deleteBtn;
    private Button transactionBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        createDbBtn = (Button) findViewById(R.id.createDbBtn);
        updateDbBtn = (Button) findViewById(R.id.updateDbBtn);

        addBtn = (Button) findViewById(R.id.addBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        queryBtn = (Button) findViewById(R.id.queryBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        transactionBtn = (Button) findViewById(R.id.transactionBtn);

    }

    //创建表，调用onCreate
    public void createDB(View v) {
        DbHelper dbHelper = new DbHelper(DbActivity.this, 1);
        //尝试获取数据库连接，查询表是否存在，才会调用DbHelper类的onCreate方法
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        Toast.makeText(DbActivity.this, "crateDB", Toast.LENGTH_SHORT).show();
    }

    //更新表版本，调用onUpgrade
    public void updateDB(View v) {
        DbHelper dbHelper = new DbHelper(DbActivity.this, 2);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();//尝试获取数据库连接，查询表是否存在
        Toast.makeText(DbActivity.this, "updateDB", Toast.LENGTH_SHORT).show();
    }

    //添加数据
    public void add(View v) {
        DbHelper dbHelper = new DbHelper(DbActivity.this, 2);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();

        ContentValues params = new ContentValues();
        params.put("name", "xie");
        params.put("age", 22);
        //返回主键_id
        long result = readableDatabase.insert("person", null, params);
        readableDatabase.close();
        Toast.makeText(DbActivity.this, "add: " + result, Toast.LENGTH_SHORT).show();
    }

    //更新数据
    public void update(View v) {
        DbHelper dbHelper = new DbHelper(DbActivity.this, 2);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();

        ContentValues params = new ContentValues();
        params.put("name", "yang123");
        int result = readableDatabase.update("person", params, "_id=?", new String[]{"1"});
        readableDatabase.close();
        Toast.makeText(DbActivity.this, "update: " + result, Toast.LENGTH_SHORT).show();
    }

    //查询数据
    public void query(View v) {
        DbHelper dbHelper = new DbHelper(DbActivity.this, 2);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("person", null, null, null, null, null, null);
        int count = cursor.getCount();//总记录数
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            Log.i("DbActivity", "----------> query: id-" + id + ",name-" + name + ",age-" + age);
        }
        cursor.close();
        readableDatabase.close();
    }

    //删除数据
    public void delete(View v) {
        DbHelper dbHelper = new DbHelper(DbActivity.this, 2);
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();

        int result = readableDatabase.delete("person", "_id=?", new String[]{"1"});
        readableDatabase.close();
        Toast.makeText(DbActivity.this, "delete: " + result, Toast.LENGTH_SHORT).show();

    }

    //事务
    public void transaction(View v) {
        DbHelper dbHelper = null;
        SQLiteDatabase readableDatabase = null;

        try {
            dbHelper = new DbHelper(DbActivity.this, 2);
            readableDatabase = dbHelper.getReadableDatabase();

            readableDatabase.beginTransaction();//开启事务

            ContentValues params = new ContentValues();
            params.put("name", "peng123");
            readableDatabase.update("person", params, "_id=?", new String[]{"2"});

            //模拟出现异常
            boolean flag = true;
            if (flag) {
                throw new RuntimeException("------------>Test Exception");
            }

            params = new ContentValues();
            params.put("name", "fei123");
            readableDatabase.update("person", params, "_id=?", new String[]{"3"});

            readableDatabase.setTransactionSuccessful();//提交事务，若出现异常，则回滚
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (readableDatabase != null) {
                readableDatabase.endTransaction();//关闭事务
                readableDatabase.close();
            }
        }


    }
}