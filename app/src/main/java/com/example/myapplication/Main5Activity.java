package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView sdv_fresco_multi;
    private Button bt_fresco_multiImg;
    private Button bt_fresco_thumbnailImg;
    private Button bt_fresco_multiplexImg;
    private Button next_six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main5);
        //初始化控件
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 先显示低分辨率的图，然后是高分辨率的图,MVC的设计模式
            case R.id.bt_fresco_multiImg:
                // 同一个图片,不同分辨率的两个URL地址
                Uri lowUri = Uri.parse("http://img1.gamedog.cn/2012/03/11/19-120311133617-50.jpg");
                Uri highUri = Uri.parse("http://img5.duitang.com/uploads/item/201312/03/20131203153823_Y4y8F.jpeg");

                // 控制加载图片
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        //一开始加载一个低分辨率的URL
                        .setLowResImageRequest(ImageRequest.fromUri(lowUri))
                        //然后加载一个高分辨率的URL,你真正要加载的图片
                        .setImageRequest(ImageRequest.fromUri(highUri))
                        .build();

                // 加载图片
                sdv_fresco_multi.setController(controller);
                break;

            // 本地缩略图预览
            case R.id.bt_fresco_thumbnailImg:

                // 图片地址,参数1.File对象,       从手机手机SD卡里加载一张图片
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() +"/shuaige.jpg"));
                // 加载图片的请求
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        //开启缩略图预览模式
                        .setLocalThumbnailPreviewsEnabled(true)
                        .build();

                // 控制图片的加载
                DraweeController preview = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .build();

                // 加载图片
                sdv_fresco_multi.setController(preview);
                break;

            //本地图片的复用
            case R.id.bt_fresco_multiplexImg:
                //在请求之前，还会去内存中请求一次图片，没有才会先去本地，最后去网络uri
                //本地准备复用图片的uri  如果本地这个图片不存在，会自动去加载下一个uri

                // 本地图片的地址
                Uri uri1 = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/shuaige.jpg"));
                //图片的网址
                Uri uri2 = Uri.parse("http://img5.duitang.com/uploads/item/201312/03/20131203153823_Y4y8F.jpeg");

                //创建ImageRequest对象,将其放入ImageRequest[]数组中.
                ImageRequest request1 = ImageRequest.fromUri(uri1);
                ImageRequest request2 = ImageRequest.fromUri(uri2);
                ImageRequest[] requests = {request1, request2};

                // 控制加载图片
                DraweeController reuse = Fresco.newDraweeControllerBuilder()
                        //设置加载图片的顺序.参数ImageRequest[]数组
                        .setFirstAvailableImageRequests(requests)
                        .setOldController(sdv_fresco_multi.getController())
                        .build();

                // 加载图片
                sdv_fresco_multi.setController(reuse);
                break;
            case R.id.next_six:
                Intent intent = new Intent(Main5Activity.this,Main6Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void initView() {
        sdv_fresco_multi = (SimpleDraweeView) findViewById(R.id.sdv_fresco_multi);
        bt_fresco_multiImg = (Button) findViewById(R.id.bt_fresco_multiImg);
        bt_fresco_thumbnailImg = (Button) findViewById(R.id.bt_fresco_thumbnailImg);
        bt_fresco_multiplexImg = (Button) findViewById(R.id.bt_fresco_multiplexImg);
        next_six = (Button) findViewById(R.id.next_six);

        bt_fresco_multiImg.setOnClickListener(this);
        bt_fresco_thumbnailImg.setOnClickListener(this);
        bt_fresco_multiplexImg.setOnClickListener(this);
        next_six.setOnClickListener(this);
    }
}
