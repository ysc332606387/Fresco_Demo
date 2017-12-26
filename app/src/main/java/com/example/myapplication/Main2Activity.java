package com.example.myapplication;

import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private SimpleDraweeView sdv_fresco_crop;
    private TextView tv_fresco_explain;
    private Button bt_fresco_center;
    private Button bt_fresco_centercrop;
    private Button bt_fresco_focuscrop;
    private Button bt_fresco_centerinside;
    private Button bt_fresco_fitcenter;
    private Button bt_fresco_fitstart;
    private Button bt_fresco_fitend;
    private Button bt_fresco_fitxy;
    private Button bt_fresco_none;
    private GenericDraweeHierarchyBuilder builder;
    private Button next_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_main2);
        //初始化控件
        initView();
        //builder对象用一个即可,在这里创建出成员变量
        builder = new GenericDraweeHierarchyBuilder(getResources());
    }

    //根据点击事件,对图片执行不同的裁剪
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 居中，无缩放
            case R.id.bt_fresco_center:
                // 设置描述
                tv_fresco_explain.setText("居中，无缩放");
                // 样式设置,使图片只显示中间的部分
                GenericDraweeHierarchy CENTER = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();
                // 显示图片
                imageDisplay(CENTER);
                break;

            // 保持宽高比缩小或放大，使得两边都大于或等于显示边界(也就是裁剪成正方形)。以中间的点为图片中心
            case R.id.bt_fresco_centercrop:
                // 设置描述
                tv_fresco_explain.setText("保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示");
                // 样式设置,使图片按比例缩小或放大,且裁剪成正方形.
                GenericDraweeHierarchy CENTER_CROP = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                // 图片显示
                imageDisplay(CENTER_CROP);
                break;

            // 同centerCrop, 但居中点不是中点，而是指定的某个点,这里设置为图片的左上角那点
            case R.id.bt_fresco_focuscrop:
                // 设置描述
                tv_fresco_explain.setText("同centerCrop, 但居中点不是中点，而是指定的某个点,这里我设置为图片的左上角那点");
                //指定中心点位置
                PointF point = new PointF(0,0);
                //根据指定的点设置为图片中心,使图片按比例缩小或放大,且裁剪成正方形.
                GenericDraweeHierarchy FOCUS_CROP = builder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                        .setActualImageFocusPoint(point).build();
                // 图片显示
                imageDisplay(FOCUS_CROP);
                break;

            //使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片
            case R.id.bt_fresco_centerinside:
                // 设置描述
                tv_fresco_explain.setText("使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片");
                // 样式设置,使图片按比例显示在控件内,
                GenericDraweeHierarchy CENTER_INSIDE = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();
                // 图片显示
                imageDisplay(CENTER_INSIDE);
                break;

            // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示
            case R.id.bt_fresco_fitcenter:
                // 设置描述
                tv_fresco_explain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示");
                // 样式设置,保持宽高比例,对图片进行缩或放,图片位置居中显示(效果和上面一种类似)
                GenericDraweeHierarchy FIT_CENTER = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();
                // 图片显示
                imageDisplay(FIT_CENTER);
                break;

            // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐
            case R.id.bt_fresco_fitstart:
                // 设置描述
                tv_fresco_explain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐");
                // 样式设置,保持宽高比例,对图片进行缩或放,图片位置,不居中，和显示边界左上对齐
                GenericDraweeHierarchy FIT_START = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                // 图片显示
                imageDisplay(FIT_START);
                break;

            // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐
            case R.id.bt_fresco_fitend:
                // 设置描述
                tv_fresco_explain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐");
                // 样式设置,保持宽高比例,对图片进行缩或放,图片位置,不居中，和显示边界右下对齐
                GenericDraweeHierarchy FIT_END = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();
                // 图片显示
                imageDisplay(FIT_END);
                break;

            // 不保持宽高比，填充满显示边界
            case R.id.bt_fresco_fitxy:
                // 设置描述
                tv_fresco_explain.setText("不保持宽高比，填充满显示边界");
                // 样式设置,使图片填充整个控件,不保证宽高比例.
                GenericDraweeHierarchy FIT_XY = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
                // 图片显示
                imageDisplay(FIT_XY);
                break;

            // 如要使用title mode显示, 需要设置为none
            case R.id.bt_fresco_none:
                // 设置描述
                tv_fresco_explain.setText("如要使用title mode显示, 需要设置为none");
                // 样式设置
                GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(null).build();
                // 图片显示
                imageDisplay(hierarchy);
                break;


            case R.id.next_three:
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
                break;

        }
    }

    /**
     * 把得到的样式进行设置,加载出图片
     * @param hierarchy
     */
    private void imageDisplay(GenericDraweeHierarchy hierarchy) {
        // 加载图片
        Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201305/20/20130520115416_VrUUR.jpeg");
        sdv_fresco_crop.setHierarchy(hierarchy);
        sdv_fresco_crop.setImageURI(uri);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        sdv_fresco_crop = (SimpleDraweeView) findViewById(R.id.sdv_fresco_crop);
        tv_fresco_explain = (TextView) findViewById(R.id.tv_fresco_explain);
        bt_fresco_center = (Button) findViewById(R.id.bt_fresco_center);
        bt_fresco_centercrop = (Button) findViewById(R.id.bt_fresco_centercrop);
        bt_fresco_focuscrop = (Button) findViewById(R.id.bt_fresco_focuscrop);
        bt_fresco_centerinside = (Button) findViewById(R.id.bt_fresco_centerinside);
        bt_fresco_fitcenter = (Button) findViewById(R.id.bt_fresco_fitcenter);
        bt_fresco_fitstart = (Button) findViewById(R.id.bt_fresco_fitstart);
        bt_fresco_fitend = (Button) findViewById(R.id.bt_fresco_fitend);
        bt_fresco_fitxy = (Button) findViewById(R.id.bt_fresco_fitxy);
        bt_fresco_none = (Button) findViewById(R.id.bt_fresco_none);
        next_three = (Button) findViewById(R.id.next_three);

        bt_fresco_center.setOnClickListener(this);
        bt_fresco_centercrop.setOnClickListener(this);
        bt_fresco_focuscrop.setOnClickListener(this);
        bt_fresco_centerinside.setOnClickListener(this);
        bt_fresco_fitcenter.setOnClickListener(this);
        bt_fresco_fitstart.setOnClickListener(this);
        bt_fresco_fitend.setOnClickListener(this);
        bt_fresco_fitxy.setOnClickListener(this);
        bt_fresco_none.setOnClickListener(this);
        next_three.setOnClickListener(this);
    }}
