package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView sdv_fresco_listener;
    private Button bt_fresco_listener;
    private TextView tv_fresco_listener;
    private TextView tv_fresco_listener2;
    private Button next_seven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载视图
        setContentView(R.layout.activity_main6);
        //初始化控件
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fresco_listener:
                // 图片地址
                Uri uri = Uri.parse("http://h.hiphotos.baidu.com/zhidao/pic/item/58ee3d6d55fbb2fbac4f2af24f4a20a44723dcee.jpg");

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

                // 图片请求,
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)//指定加载图片地址
                        .setProgressiveRenderingEnabled(true)////打开渐进 渲染
                        .build();

                // 图片加载的控制
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(sdv_fresco_listener.getController())
                        .setImageRequest(request)
                        //设置监听器监听图片的加载
                        .setControllerListener(controllerListener)
                        .build();
                // 加载图片
                sdv_fresco_listener.setController(controller);
                break;
            case R.id.next_seven:
                Intent intent = new Intent(Main6Activity.this,Main7Activity.class);
                startActivity(intent);
                break;
        }
    }
    private ControllerListener controllerListener = new BaseControllerListener<ImageInfo>(){
        // 加载图片完毕回调
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);
            //图片信息对象非空判断
            if (imageInfo == null) {
                return;
            }
            // 获取图片的质量信息
            QualityInfo qualityInfo = imageInfo.getQualityInfo();
            tv_fresco_listener.setText("Final image received! " +
                    "\nSize: " + imageInfo.getWidth()                           //图片宽
                    + "x" + imageInfo.getHeight()                               //图片高
                    + "\nQuality level: " + qualityInfo.getQuality()            //图片等级
                    + "\ngood enough: " + qualityInfo.isOfGoodEnoughQuality()   //图片是否效果完全显示
                    + "\nfull quality: " + qualityInfo.isOfFullQuality());      //图片是否完全显示
        }

        // 渐进式加载图片回调(只有启用来图片的渐进式,方有效)
        @Override
        public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
            super.onIntermediateImageSet(id, imageInfo);
            tv_fresco_listener2.setText("IntermediateImageSet image receiced");
        }

        // 加载图片失败回调
        @Override
        public void onFailure(String id, Throwable throwable) {
            super.onFailure(id, throwable);
            //这里的id参数就是图片加载失败的打印信息
            tv_fresco_listener.setText("Error loading" + id);
        }
    };

    private void initView() {
        sdv_fresco_listener = (SimpleDraweeView) findViewById(R.id.sdv_fresco_listener);
        bt_fresco_listener = (Button) findViewById(R.id.bt_fresco_listener);
        tv_fresco_listener = (TextView) findViewById(R.id.tv_fresco_listener);
        tv_fresco_listener2 = (TextView) findViewById(R.id.tv_fresco_listener2);
        next_seven = (Button) findViewById(R.id.next_seven);
        bt_fresco_listener.setOnClickListener(this);
        next_seven.setOnClickListener(this);
    }

}
