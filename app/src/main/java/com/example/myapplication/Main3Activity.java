package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    private SimpleDraweeView sdv_fresco_circleandcorner;
    private Button bt_fresco_circle;
    private Button bt_fresco_corner;

    private Uri uri;
    private GenericDraweeHierarchyBuilder builder;
    private RoundingParams parames;
    private Button next_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main3);
        //初始化控件
        initView();
        //加载图片的网址
        uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");
        //builder对象用一个即可,在这里创建出成员变量
        builder = new GenericDraweeHierarchyBuilder(getResources());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 设置圆形图片
            case R.id.bt_fresco_circle:
                // 设置形状对象,形状为圆形
                parames = RoundingParams.asCircle();
                //创建设置参数,设置一个形状,把形状对象塞入
                GenericDraweeHierarchy roundness = builder.setRoundingParams(parames).build();
                //将参数对象设置给图片控件
                sdv_fresco_circleandcorner.setHierarchy(roundness);
                //控件加载图片
                sdv_fresco_circleandcorner.setImageURI(uri);
                break;

            // 设置圆角图片
            case R.id.bt_fresco_corner:
                //设置边角的弧度,使其为圆角
                parames = RoundingParams.fromCornersRadius(50f);

/*                //设置图片控件的背景颜色
                parames.setOverlayColor(getResources().getColor(android.R.color.holo_red_light));//覆盖层
                //设置图片的边框颜色及边框的粗细
                parames.setBorder(getResources().getColor(android.R.color.holo_blue_light), 5);//边框*/

                //这里的代码和设置圆形图片这一块代码是一种的,唯一不同就是对parames的设置.
                GenericDraweeHierarchy circularBead = builder.setRoundingParams(parames).build();
                sdv_fresco_circleandcorner.setHierarchy(circularBead);

                // 加载图片
                sdv_fresco_circleandcorner.setImageURI(uri);
                break;
            case R.id.next_four:
                Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        sdv_fresco_circleandcorner = (SimpleDraweeView) findViewById(R.id.sdv_fresco_circleandcorner);
        bt_fresco_circle = (Button) findViewById(R.id.bt_fresco_circle);
        bt_fresco_corner = (Button) findViewById(R.id.bt_fresco_corner);
        next_four = (Button) findViewById(R.id.next_four);

        bt_fresco_circle.setOnClickListener(this);
        bt_fresco_corner.setOnClickListener(this);
        next_four.setOnClickListener(this);
    }


}
