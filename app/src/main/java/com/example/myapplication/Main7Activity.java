package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class Main7Activity extends AppCompatActivity implements View.OnClickListener{

    private SimpleDraweeView sdv_fresco_resize;
    private Button bt_fresco_resize;
    private Button next_eight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main7);
        //初始化控件
        initView();
    }

    private void initView() {
        sdv_fresco_resize = (SimpleDraweeView) findViewById(R.id.sdv_fresco_resize);
        bt_fresco_resize = (Button) findViewById(R.id.bt_fresco_resize);
        next_eight = (Button) findViewById(R.id.next_eight);
        bt_fresco_resize.setOnClickListener(this);
        next_eight.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //修内存中改图片大小
            case R.id.bt_fresco_resize:
                // 图片地址
                Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");

                //图片的请求
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        //重新设置这张图片的宽高.以便解决内存
                        .setResizeOptions(new ResizeOptions(50, 50))
                        .build();
                // 控制图片的加载
                PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(sdv_fresco_resize.getController())
                        .setImageRequest(request)
                        .build();
                // 加载图片
                sdv_fresco_resize.setController(controller);
                break;
            case R.id.next_eight:
                Intent intent = new Intent(Main7Activity.this,Main8Activity.class);
                startActivity(intent);
                break;
        }
    }
}
