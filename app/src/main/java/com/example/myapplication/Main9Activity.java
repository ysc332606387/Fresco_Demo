package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class Main9Activity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_fresco_loadsmall;
    private LinearLayout ll_fresco;
    private SimpleDraweeView simpleDraweeView;
    private Button next_ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main9);

        initView();
        //创建Fresco加载图片的控件对象
        simpleDraweeView = new SimpleDraweeView(this);
        // 设置控件对象的宽高比,必须设置,参数浮点型,大于1,则宽度是高度的几倍.
        simpleDraweeView.setAspectRatio(0.5f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fresco_loadsmall:
                // 图片的地址
                Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");
                // 图片的请求
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .build();
                // 加载图片的控制
                PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(simpleDraweeView.getController())
                        .setImageRequest(request)
                        .build();
                // 加载图片
                simpleDraweeView.setController(controller);
                // 将simpleDraweeView控件对象,添加到线性布局中
                ll_fresco.addView(simpleDraweeView);
                break;
            case R.id.next_ten:
                Intent intent = new Intent(Main9Activity.this,Main10Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void initView() {
        bt_fresco_loadsmall = (Button) findViewById(R.id.bt_fresco_loadsmall);
        next_ten = (Button) findViewById(R.id.next_ten);
        ll_fresco = (LinearLayout) findViewById(R.id.ll_fresco);

        bt_fresco_loadsmall.setOnClickListener(this);
        next_ten.setOnClickListener(this);
    }
}
