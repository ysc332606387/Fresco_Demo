package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView sdv;
    private Button next_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main);
        //找控件
        sdv = (SimpleDraweeView) findViewById(R.id.main_simple_drawee_view);
        next_two = (Button) findViewById(R.id.next_two);
        //所要加载图片的网址
        Uri uri = Uri.parse("http://pic3.16pic.com/00/12/61/16pic_1261451_b.jpg");
        //创建Builder对象,一般创建出参数对象
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        //创建参数对象,设置其样式为进度条
        GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();
        //将参数对象设置给图片控件
        sdv.setHierarchy(hierarchy);
        //控件加载图片,参数:网络图片的网址.
        sdv.setImageURI(uri);

        next_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
