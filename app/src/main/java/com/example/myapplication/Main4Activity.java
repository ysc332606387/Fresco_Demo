package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView sdv_fresco_jpeg;
    private Button sdv_fresco_askImg;
    private Button next_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载视图
        setContentView(R.layout.activity_main4);
        //初始化控件
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //渐进式加载图片,采用的是MVC的设计模式
            case R.id.sdv_fresco_askImg:
                // 获取图片URL
                Uri uri = Uri.parse("http://pic3.16pic.com/00/12/61/16pic_1261451_b.jpg");

                // 加载质量配置,为了实现节省CPU,随着图片下载的进行，下载完的扫描序列如下: 1, 4, 5, 10
/*                首次调用getNextScanNumberToDecode返回为2， 因为初始时，解码的扫描数为0。
                那么1将不会解码，下载完成4个扫描时，解码一次。下个解码为扫描数为6(5不会解码，10才会解码)*/
                ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
                    @Override
                    public int getNextScanNumberToDecode(int scanNumber) {
                        return scanNumber + 2;
                    }

                    @Override
                    public QualityInfo getQualityInfo(int scanNumber) {
                        boolean isGoodEnough = (scanNumber >= 5);

                        return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
                    }
                };
                //上面的和下面一行是固定代码.使用使复制粘贴即可
                ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();

                // 创建 ImageRequest 对象.
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)//设置URL
                        .setProgressiveRenderingEnabled(true)//打开渐进 渲染
                        .build();

                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        //必须要设置ImageRequest对象,里面包含了图片的网址.
                        .setImageRequest(request)
                        //开启用户点击重新加载图片的功能
                        .setTapToRetryEnabled(true)
                        //会复用以前的对象,可以节省内存.
                        .setOldController(sdv_fresco_jpeg.getController())
                        .build();

                // 1设置加载的控制
                sdv_fresco_jpeg.setController(draweeController);

                break;
            case R.id.next_five:
                Intent intent = new Intent(Main4Activity.this,Main5Activity.class);
                startActivity(intent);
                break;
        }
    }

    //初始化控件
    private void initView() {
        sdv_fresco_jpeg = (SimpleDraweeView) findViewById(R.id.sdv_fresco_jpeg);
        sdv_fresco_askImg = (Button) findViewById(R.id.sdv_fresco_askImg);
        next_five = (Button) findViewById(R.id.next_five);

        sdv_fresco_askImg.setOnClickListener(this);
        next_five.setOnClickListener(this);
    }
}
