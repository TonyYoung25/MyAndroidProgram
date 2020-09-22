package com.hw;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpActivity extends AppCompatActivity {

    private static final String HTTP_URL = "http://192.168.1.3:8080";//访问的地址

    private EditText showResponse;
    private Button httpUrlConnectionGetBtn;
    private Button httpUrlConnectionPostBtn;
    private Button httpClientGetBtn;
    private Button httpClientPostBtn;
    private Button volleyGetBtn;
    private Button volleyPostBtn;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        showResponse = (EditText) findViewById(R.id.showResponse);

        httpUrlConnectionGetBtn = (Button) findViewById(R.id.httpUrlConnectionGetBtn);
        httpUrlConnectionPostBtn = (Button) findViewById(R.id.httpUrlConnectionPostBtn);
        httpClientGetBtn = (Button) findViewById(R.id.httpClientGetBtn);
        httpClientPostBtn = (Button) findViewById(R.id.httpClientPostBtn);
        volleyGetBtn = (Button) findViewById(R.id.volleyGetBtn);
        volleyPostBtn = (Button) findViewById(R.id.volleyPostBtn);

        requestQueue = Volley.newRequestQueue(HttpActivity.this);
    }

    public void httpUrlConnectionGet(View v) {
        //弹出加载等待框
        final ProgressDialog progressDialog = ProgressDialog.show(HttpActivity.this, "loading", "loading...");

        //分线程处理业务
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                try {
                    String requestUrl = HTTP_URL + "/testHttpGet?name=yang123";
                    URL url = new URL(requestUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(30000);
                    urlConnection.setReadTimeout(40000);

                    urlConnection.connect();
                    int responseCode;
                    if ((responseCode = urlConnection.getResponseCode()) == 200) {
                        inputStream = urlConnection.getInputStream();

                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, len);
                        }

                    }

                    //加载响应
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showResponse.setText(outputStream.toString());
                            progressDialog.dismiss();//关闭loading弹出框
                        }
                    });


                } catch (Exception e) {
                    Log.e("HttpActivity", e.getMessage());
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
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
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
        }.start();
    }

    public void httpUrlConnectionPost(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(HttpActivity.this, "load", "loading...");

        new Thread() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                OutputStream outputStream = null;
                InputStream inputStream = null;

                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                try {
                    String requestUrl = HTTP_URL + "/testHttpPost";
                    URL url = new URL(requestUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(30000);
                    urlConnection.setReadTimeout(40000);

                    urlConnection.connect();

                    //通过连接获取一个输出流，将参数传出去
                    outputStream = urlConnection.getOutputStream();
                    String params = "name=yang456";
                    outputStream.write(params.getBytes("UTF-8"));

                    int responseCode;
                    if ((responseCode = urlConnection.getResponseCode()) == 200) {
                        inputStream = urlConnection.getInputStream();

                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(bytes)) != -1) {
                            byteArrayOutputStream.write(bytes, 0, len);
                        }

                    }

                    //加载响应
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showResponse.setText(byteArrayOutputStream.toString());
                            progressDialog.dismiss();//关闭loading弹出框
                        }
                    });

                } catch (Exception e) {
                    Log.e("HttpActivity", e.getMessage());
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
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
        }.start();
    }

    public void httpClientGet(View v) {
        ProgressDialog progressDialog = ProgressDialog.show(HttpActivity.this, "load", "loading");

        new Thread() {
            @Override
            public void run() {
                String path = HTTP_URL + "/testHttpGet?name=peng123";
                HttpClient httpClient = null;

                try {
                    httpClient = new DefaultHttpClient();//创建HttpClient对象

                    HttpParams params = httpClient.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 5000);
                    HttpConnectionParams.setSoTimeout(params, 6000);

                    //创建get请求对象
                    HttpGet request = new HttpGet(path);

                    HttpResponse response = httpClient.execute(request);
                    int responseCode = response.getStatusLine().getStatusCode();

                    if (responseCode == 200) {
                        HttpEntity httpEntity = response.getEntity();
                        final String result = EntityUtils.toString(httpEntity);

                        // ui主线程加载相应
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showResponse.setText(result);
//                                progressDialog.dismiss();//关闭loading弹出框
                            }
                        });
                    }
                } catch (Exception e) {

                } finally {
                    progressDialog.dismiss();//关闭loading弹出框
                    httpClient.getConnectionManager().shutdown();//断开连接

                }

            }
        }.start();

    }

    public void httpClientPost(View v) {
        ProgressDialog progressDialog = ProgressDialog.show(HttpActivity.this, "load", "loading");

        new Thread() {
            @Override
            public void run() {

                String path = HTTP_URL + "/testHttpPost";
                HttpClient httpClient = null;

                try {

                    httpClient = new DefaultHttpClient();
                    HttpParams params = httpClient.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 5000);
                    HttpConnectionParams.setSoTimeout(params, 6000);

                    //创建post请求对象
                    HttpPost request = new HttpPost(path);
                    List<BasicNameValuePair> list = new ArrayList<>();
                    list.add(new BasicNameValuePair("name", "peng456"));
                    HttpEntity httpEntity = new UrlEncodedFormEntity(list);
                    request.setEntity(httpEntity);

                    HttpResponse response = httpClient.execute(request);
                    int responseCode = response.getStatusLine().getStatusCode();

                    if (responseCode == 200) {
                        httpEntity = response.getEntity();
                        final String result = EntityUtils.toString(httpEntity);

                        // ui主线程加载相应
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showResponse.setText(result);
//                                progressDialog.dismiss();//关闭loading弹出框
                            }
                        });
                    }
                } catch (Exception e) {

                } finally {
                    progressDialog.dismiss();//关闭loading弹出框
                    httpClient.getConnectionManager().shutdown();//断开连接
                }
            }
        }.start();


    }

    public void volleyGet(View v) {
        ProgressDialog progressDialog = ProgressDialog.show(HttpActivity.this, "load", "loading");
        String path = HTTP_URL + "/testHttpGet?name=fei123";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//主线程执行
                showResponse.setText(response);
                progressDialog.dismiss();//关闭loading弹出框
            }
        }, null);

        requestQueue.add(stringRequest);
    }

    public void volleyPost(View v) {
        ProgressDialog progressDialog = ProgressDialog.show(HttpActivity.this, "load", "loading");
        String path = HTTP_URL + "/testHttpPost";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showResponse.setText(response);
                progressDialog.dismiss();//关闭loading弹出框
            }
        }, null) {
            //添加请参数
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("name", "fei456");
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}