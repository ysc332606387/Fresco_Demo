package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bt_fresco_spimg;
    private Button bt_fresco_crop;
    private Button bt_fresco_circleAndCorner;
    private Button bt_fresco_jpeg;
    private Button bt_fresco_gif;
    private Button bt_fresco_multi;
    private Button bt_fresco_listener;
    private Button bt_fresco_resize;
    private Button bt_fresco_modifyImg;
    private Button bt_fresco_autoSizeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.layout_main);
        initView();

    }

    private void initView() {
        bt_fresco_spimg = (Button) findViewById(R.id.bt_fresco_spimg);
        bt_fresco_crop = (Button) findViewById(R.id.bt_fresco_crop);
        bt_fresco_circleAndCorner = (Button) findViewById(R.id.bt_fresco_circleAndCorner);
        bt_fresco_jpeg = (Button) findViewById(R.id.bt_fresco_jpeg);
        bt_fresco_gif = (Button) findViewById(R.id.bt_fresco_gif);
        bt_fresco_multi = (Button) findViewById(R.id.bt_fresco_multi);
        bt_fresco_listener = (Button) findViewById(R.id.bt_fresco_listener);
        bt_fresco_resize = (Button) findViewById(R.id.bt_fresco_resize);
        bt_fresco_modifyImg = (Button) findViewById(R.id.bt_fresco_modifyImg);
        bt_fresco_autoSizeImg = (Button) findViewById(R.id.bt_fresco_autoSizeImg);

        bt_fresco_spimg.setOnClickListener(this);
        bt_fresco_crop.setOnClickListener(this);
        bt_fresco_circleAndCorner.setOnClickListener(this);
        bt_fresco_jpeg.setOnClickListener(this);
        bt_fresco_gif.setOnClickListener(this);
        bt_fresco_multi.setOnClickListener(this);
        bt_fresco_listener.setOnClickListener(this);
        bt_fresco_resize.setOnClickListener(this);
        bt_fresco_modifyImg.setOnClickListener(this);
        bt_fresco_autoSizeImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fresco_spimg:
                Intent intent = new Intent(MainActivity.this,Main11Activity.class);
                startActivity(intent);
                break;
            case R.id.bt_fresco_crop:
                Intent intent2 = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent2);
                break;
            case R.id.bt_fresco_circleAndCorner:
                Intent intent3 = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent3);
                break;
            case R.id.bt_fresco_jpeg:
                Intent intent4 = new Intent(MainActivity.this,Main4Activity.class);
                startActivity(intent4);
                break;
            case R.id.bt_fresco_gif:
                Intent intent5 = new Intent(MainActivity.this,Main10Activity.class);
                startActivity(intent5);
                break;
            case R.id.bt_fresco_multi:
                Intent intent6 = new Intent(MainActivity.this,Main5Activity.class);
                startActivity(intent6);
                break;
            case R.id.bt_fresco_listener:

                Intent intent7 = new Intent(MainActivity.this,Main6Activity.class);
                startActivity(intent7);
                break;
            case R.id.bt_fresco_resize:

                Intent intent8 = new Intent(MainActivity.this,Main7Activity.class);
                startActivity(intent8);
                break;
            case R.id.bt_fresco_modifyImg:

                Intent intent9 = new Intent(MainActivity.this,Main8Activity.class);
                startActivity(intent9);
                break;
            case R.id.bt_fresco_autoSizeImg:

                Intent intent10 = new Intent(MainActivity.this,Main9Activity.class);
                startActivity(intent10);
                break;
        }
    }
}
