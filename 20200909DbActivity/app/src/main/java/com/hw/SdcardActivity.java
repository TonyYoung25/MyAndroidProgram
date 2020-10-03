package com.hw;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SdcardActivity extends AppCompatActivity {

    private EditText scFileName;
    private EditText scFileContent;
    private Button scFileWriteBtn1;
    private Button scFileReadBtn1;
    private Button scFileWriteBtn2;
    private Button scFileReadBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);

        scFileName = (EditText) findViewById(R.id.scFileName);
        scFileContent = (EditText) findViewById(R.id.scFileContent);
        scFileWriteBtn1 = (Button) findViewById(R.id.scFileWriteBtn1);
        scFileReadBtn1 = (Button) findViewById(R.id.scFileReadBtn1);
        scFileWriteBtn2 = (Button) findViewById(R.id.scFileWriteBtn2);
        scFileReadBtn2 = (Button) findViewById(R.id.scFileReadBtn2);
    }

    //---------------------------路径1：存储至 storage/sdcard/Android/data/packageName/files/xxx----------------------------------
    public void scFileWrite1(View v) throws IOException {
        //判断sd卡是否在挂载状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String scFileNameStr = scFileName.getText().toString();//文件名
            String scFileContentStr = scFileContent.getText().toString();//文件内容

            /*
               Environment#DIRECTORY_MUSIC 音乐
               Environment#DIRECTORY_PODCASTS 播客
               Environment#DIRECTORY_RINGTONES 铃声
               Environment#DIRECTORY_ALARMS 警报
               Environment#DIRECTORY_NOTIFICATIONS 通知
               Environment#DIRECTORY_PICTURES 图片
               Environment#DIRECTORY_MOVIES 电影
            */
            //String filesPath = getExternalFilesDir(null).getPath();//文件夹路径：/storage/sdcard/Android/data/packageName/files
            String filesPath = getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getPath();//文件夹路径：/storage/sdcard/Android/data/packageName/files/Notifications
            String filePath = filesPath + "/" + scFileNameStr;//文件路径
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(scFileContentStr.getBytes("UTF-8"));
            fos.close();
            Toast.makeText(SdcardActivity.this, "ok", Toast.LENGTH_SHORT).show();

        } else {
            //sd卡未挂载...

        }
    }

    public void scFileRead1(View v) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String scFileNameStr = scFileName.getText().toString();//获取文件名

            String filePath = getExternalFilesDir(null).getAbsolutePath() + "/" + scFileNameStr;//拼接文件路径
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            scFileContent.setText(byteArrayOutputStream.toString());//将读取的值显示

            byteArrayOutputStream.close();
            fileInputStream.close();

        } else {
            //sd卡未挂载...
        }
    }

    //---------------------------路径2：存储至 storage/sdcard/xxx----------------------------------
    public void scFileWrite2(View v) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String scFileNameStr = scFileName.getText().toString();//文件名
            String scFileContentStr = scFileContent.getText().toString();//文件内容

            String filesPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String dirName = "/huawei";
            File file = new File(filesPath + dirName);
            if (!file.exists()) {//如果不存在，创建文件夹
                file.mkdir();
            }

            String filePath = filesPath + dirName + "/" + scFileNameStr;

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);//创建输出流
            fileOutputStream.write(scFileContentStr.getBytes("UTF-8"));

            fileOutputStream.close();

            Toast.makeText(SdcardActivity.this, "ok", Toast.LENGTH_SHORT).show();
        }
    }

    public void scFileRead2(View v) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String scFileNameStr = scFileName.getText().toString();//文件名

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/huawei/" + scFileNameStr;//拼接文件路径

            FileInputStream fileInputStream = new FileInputStream(filePath);//用于读取文件的输入流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//用于文本栏显示的输出流
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes);
            }

            scFileContent.setText(byteArrayOutputStream.toString());//将读取的值显示
            byteArrayOutputStream.close();
            fileInputStream.close();

        }
    }
}