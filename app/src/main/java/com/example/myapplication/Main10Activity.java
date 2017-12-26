package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class Main10Activity extends AppCompatActivity {

    private Button but_gif;
    private SimpleDraweeView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main10);
        //加载控价
        gif = (SimpleDraweeView) findViewById(R.id.gif);
        but_gif = (Button) findViewById(R.id.but_gif);
        //点击事件
        but_gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //所要加载图片的网址
                Uri uri = Uri.parse("http://img.zcool.cn/community/01965756f0a5de6ac7257d202cc205.gif");
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)//设置GIF网址
                        .setAutoPlayAnimations(true)//是否自动播放动画,false为不播放
                        .setOldController(gif.getController())//内存优化
                        .build();

                gif.setController(controller);
            }
        });
    }
}
