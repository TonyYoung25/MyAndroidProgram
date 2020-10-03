package com.hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button getSubmit1;
    private Button getSubmit2;
    private ProgressDialog progressDialog = null;
    public Button downloadBtn;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                String text = (String) msg.obj;
                editText.setText(text);
                progressDialog.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        getSubmit1 = (Button) findViewById(R.id.getSubmit1);
        getSubmit2 = (Button) findViewById(R.id.getSubmit2);

        downloadBtn = (Button) findViewById(R.id.downloadBtn);

    }

    public void getSubmit1(View v) {
        progressDialog = ProgressDialog.show(MainActivity.this, "load", "loading...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                final String res = httpRequest();

                //加载响应
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText(res);
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();

    }

    public void getSubmit2(View v) {
        progressDialog = ProgressDialog.show(MainActivity.this, "load", "loading...");

        //分线程联网请求，得到相应数据
        new Thread(new Runnable() {
            @Override
            public void run() {

                final String res = httpRequest();

                Message message = new Message();
                message.what = 1;
                message.obj = res;
                handler.sendMessage(message);

            }
        }).start();
    }

    public static String httpRequest() {
        String result = "";
        String path = "http://192.168.1.3:8080/testHttpGet?name=yang123";
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            URL url = new URL(path);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(6000);

            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStream = httpURLConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, length);
                }
            }

            result = byteArrayOutputStream.toString();

        } catch (IOException e) {

        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return result;
    }


    //-------------------------------下载----------------------------------
    private File saveApkFile = null;

    public void downland(View v) {
        new AsyncTask<Void, Integer, Void>() {
            //在execute前，在主线程执行
            @Override
            protected void onPreExecute() {
                //弹出下载进度框
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();

                //保存apk文件，/storage/sdcard/Android/package_name/files/xxx.apk
                saveApkFile = new File(getExternalFilesDir(null), "test.apk");
            }

            //在后台执行
            @Override
            protected Void doInBackground(Void... voids) {
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;

                try {
                    String path = "http://192.168.1.3:8080/getApk";
                    URL url = new URL(path);

                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    //httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(6000);
                    httpURLConnection.connect();

                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        progressDialog.setMax(httpURLConnection.getContentLength());//设置进度条长度

                        inputStream = httpURLConnection.getInputStream();
                        fileOutputStream = new FileOutputStream(saveApkFile);

                        int len;
                        byte[] bytes = new byte[1024];
                        while ((len = inputStream.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0, len);

                            //progressDialog.incrementProgressBy(len);//设置进度条
                            publishProgress(len);//发布进度

                            Thread.sleep(10);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            //在主线程中更新进度值
            @Override
            protected void onProgressUpdate(Integer... values) {
                progressDialog.incrementProgressBy(values[0]);
            }

            //在execute后执行
            @Override
            protected void onPostExecute(Void aVoid) {
                progressDialog.dismiss();//关闭弹出框
                installApk();
            }
        }.execute();
    }

    //安装apk
    private void installApk() {
        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
        intent.setDataAndType(Uri.fromFile(saveApkFile), "application/vnd.android.package-archive");
        startActivity(intent);

    }
}