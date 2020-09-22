package com.hw;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.InputStream;

public class FileActivity extends AppCompatActivity {

    private Button fileReadBtn;
    private Button fileWriteBtn;
    private ImageView fileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        fileReadBtn = (Button) findViewById(R.id.fileReadBtn);
        fileWriteBtn = (Button) findViewById(R.id.fileWriteBtn);
        fileImage = (ImageView) findViewById(R.id.fileImage);


    }

    //将文件从assets文件夹保存至data/data/packageName/files/xxx
    public void fileWrite(View v) throws Exception {
        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open("a.png");
        FileOutputStream fileOutputStream = openFileOutput("a.png", Context.MODE_PRIVATE);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer);
        }

        fileOutputStream.close();
        inputStream.close();

        Toast.makeText(FileActivity.this, "ok", Toast.LENGTH_SHORT).show();
    }

    public void fileRead(View v) throws Exception {
        String filePath = getFilesDir().getAbsolutePath();//获取file文件夹路径
        String imagePath = filePath + "/a.png";//拼接文件路径
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);//加载文件
        fileImage.setImageBitmap(bitmap);
    }
}