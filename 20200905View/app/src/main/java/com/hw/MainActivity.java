package com.hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button button;
    private EditText editText;

    private ImageView imageView;
    private boolean flag = true;

    private CheckBox football;
    private CheckBox basketball;
    private CheckBox pingpong;
    private Button confirmBtn;

    private RadioGroup sexRadioGroup;

    private Button contextMenuBtn;

    private LinearLayout progressBar1;
    private ProgressBar progressBar2;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.myText);
        textView.setText("this is new text");

        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    //设置背景图片
                    imageView.setBackgroundResource(android.R.drawable.alert_light_frame);
                    //设置前景图片
                    imageView.setImageResource(android.R.drawable.ic_media_pause);
                    flag = false;
                } else {
                    //设置背景图片
                    imageView.setBackgroundResource(android.R.drawable.alert_dark_frame);
                    //设置前景图片
                    imageView.setImageResource(android.R.drawable.ic_media_play);
                    flag = true;
                }
            }
        });

        football = (CheckBox) findViewById(R.id.football);
        basketball = (CheckBox) findViewById(R.id.basketball);
        pingpong = (CheckBox) findViewById(R.id.pingpong);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);

        football.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "football is checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "football is unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();

                if (football.isChecked()) {
                    sb.append("football ");
                }
                if (basketball.isChecked()) {
                    sb.append("basketball ");
                }
                if (pingpong.isChecked()) {
                    sb.append("pingpong ");
                }

                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        sexRadioGroup = (RadioGroup) findViewById(R.id.sexRadioGroup);
        sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //选中的RadioButton
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                String text = radioButton.getText().toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

            }
        });


        contextMenuBtn = (Button) findViewById(R.id.contextMenuBtn);
        contextMenuBtn.setOnCreateContextMenuListener(this);

        progressBar1 = (LinearLayout) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progressNum = seekBar.getProgress();
                progressBar2.setProgress(progressNum);

                if (progressNum == seekBar.getMax()) {
                    //progressBar1.setVisibility(View.INVISIBLE);
                    progressBar1.setVisibility(View.GONE);
                } else {
                    progressBar1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "on start: " + seekBar.getProgress() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "on stop: " + seekBar.getProgress() + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //长按显示上下文对话框
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 1, "yes");
        menu.add(0, 2, 1, "no");
    }

    //ContextMenu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                Toast.makeText(MainActivity.this, "click yes", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "click no", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this, "click nothing", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    //optionMenu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0,1,1,"yes");
//        menu.add(0,2,2,"no");
//
//        return super.onCreateOptionsMenu(menu);
//    }

    //menu的菜单弹出
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //选中后的效果
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.yes:
                Toast.makeText(MainActivity.this, "click yes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.no:
                Toast.makeText(MainActivity.this, "click no", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this, "click nothing", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    //普通弹出框
    public void normalAlertDialog(View v) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("delete")
                .setMessage("Are you sure? ")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "delete completed!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "nothing!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //选择项弹出框
    public void selectAlertDialog(View v) {
        final String[] items = {"red", "green", "yellow", "grey", "blue"};
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("select one")
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {//checkedItem默认选中的项
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//which就是选中的item的下标
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();//选中后，弹出框消失
                    }
                })
                .show();
    }

    //自定义弹出框
    public void customizeAlertDialog(View v) {
        final View myView = View.inflate(MainActivity.this, R.layout.customize_alert_dialog, null);//获取自定义的视图view

        new AlertDialog.Builder(MainActivity.this)
                .setView(myView)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText un = (EditText) myView.findViewById(R.id.username);
                        EditText pwd = (EditText) myView.findViewById(R.id.password);
                        Toast.makeText(MainActivity.this, un.getText().toString() + "/" + pwd.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "nothing", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //进度条弹出框
    public void progressAlertDialog1(View v) {
        final ProgressDialog progressDialog1 = ProgressDialog.show(MainActivity.this, "load", "loading...");

        //在子线程中完成长时间的业务操作
        new Thread() {
            @Override
            public void run() {
                //模拟业务耗时
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                progressDialog1.dismiss();//弹出框消失

                //不可以在分线程中直接更新UI，例如Toast，否则会抛出异常
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {//在主线程中执行Toast
                        Toast.makeText(MainActivity.this, "load completed!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();
    }

    //进度条弹出框
    public void progressAlertDialog2(View v) {
        final ProgressDialog progressDialog2 = new ProgressDialog(MainActivity.this);
        progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//水平进度条
        progressDialog2.show();

        new Thread() {
            @Override
            public void run() {
                int count = 10;
                progressDialog2.setMax(count);//设置进度条的最大值
                for (int i = 1; i <= count; i++) {

                    progressDialog2.setProgress(progressDialog2.getProgress() + 1);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                progressDialog2.dismiss();//弹出框消失
            }
        }.start();

        //子线程中执行业务
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int count = 100;
//                progressDialog2.setMax(count);//设置进度条的最大值
//                for (int i = 1; i <= count; i++) {
//
//                    progressDialog2.setProgress(progressDialog2.getProgress() + 1);
//
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                progressDialog2.dismiss();//弹出框消失
//            }
//        }).start();

    }

    //日期弹出框
    public void dataAlertDialog(View v) {
        //系统的年月日
        Calendar calendar = Calendar.getInstance();
        int cyear = calendar.get(Calendar.YEAR);
        int cmonth = calendar.get(Calendar.MONTH);
        final int cday = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {//year,month,dayOfMonth 选中的年月日
                Toast.makeText(MainActivity.this, year + "-" + month + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        }, cyear, cmonth, cday).show();//默认加载系统的年月日

    }

    //时间弹出框
    public void timeAlertDialog(View v) {
        //系统时分秒
        Calendar calendar = Calendar.getInstance();
        int cHour = calendar.get(Calendar.HOUR);
        int cMinute = calendar.get(Calendar.HOUR);

        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this, hourOfDay + " : " + minute, Toast.LENGTH_SHORT).show();
            }
        }, cHour, cMinute, true).show();

    }
}