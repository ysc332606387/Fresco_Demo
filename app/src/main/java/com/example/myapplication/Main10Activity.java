package com.example.myapplication;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class Main10Activity extends AppCompatActivity implements View.OnClickListener{

    private SimpleDraweeView sdv_fresco_gif;
    private Button bt_fresco_askImg;
    private Button bt_fresco_stopAnim;
    private Button bt_fresco_startAnim;

//http://img.zcool.cn/community/01965756f0a5de6ac7257d202cc205.gif
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main10);
        //初始化控件uogyuiyt
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //请求GIF动画,采用MVC的设计模式(注意加载GIF动画还要添加依赖)
            /*  支持 GIF 动图，需要添加
            compile 'com.facebook.fresco:animated-gif:0.14.1'   */
            case R.id.bt_fresco_askImg:
                //GIF动画网址,加载需要一段时间
                Uri uri = Uri.parse("http://img.zcool.cn/community/01965756f0a5de6ac7257d202cc205.gif");

                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)//设置GIF网址
                        .setAutoPlayAnimations(false)//是否自动播放动画,false为不播放
                        .setOldController(sdv_fresco_gif.getController())//内存优化
                        .build();

                sdv_fresco_gif.setController(controller);
                break;

            //  动画停止
            case R.id.bt_fresco_stopAnim:
                //拿到动画对象
                Animatable animatableStop = sdv_fresco_gif.getController().getAnimatable();
                //进行非空及是否动画在播放判断
                if(animatableStop != null && animatableStop.isRunning()) {
                    //动画在播放,停止动画播放
                    animatableStop.stop();
                }
                break;

            // 动画开始
            case R.id.bt_fresco_startAnim:
                //拿到动画对象
                Animatable animatableStart = sdv_fresco_gif.getController().getAnimatable();
                //进行非空及是否动画在播放判断
                if(animatableStart != null && !animatableStart.isRunning()) {
                    //动画停止播放,播放动画
                    animatableStart.start();
                }
                break;
        }
    }

    private void initView() {
        sdv_fresco_gif = (SimpleDraweeView) findViewById(R.id.sdv_fresco_gif);
        bt_fresco_askImg = (Button) findViewById(R.id.bt_fresco_askImg);
        bt_fresco_stopAnim = (Button) findViewById(R.id.bt_fresco_stopAnim);
        bt_fresco_startAnim = (Button) findViewById(R.id.bt_fresco_startAnim);

        bt_fresco_askImg.setOnClickListener(this);
        bt_fresco_stopAnim.setOnClickListener(this);
        bt_fresco_startAnim.setOnClickListener(this);
    }
}
