package com.integrate.netstudyapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.integrate.netstudyapplication.R;

/**
 * Created by 梁明伟 on 2019/6/17.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class GradientDemoView extends View {

    private int mode;

    public GradientDemoView(Context context) {
        this(context, null);
    }

    public GradientDemoView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GradientDemoView);
        if (null != ta) {
            mode = ta.getInteger(R.styleable.GradientDemoView_mode, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int colorStart = Color.RED;
        int color1 = Color.GREEN;
        int colorEnd = Color.BLUE;
        int color2 = Color.YELLOW;

        Paint paint = new Paint();

        //重点解析这里的渐变配置
        /**
         53  * Create a shader that draws a linear gradient along a line.   创建一个沿着"一条线"绘制线性渐变的着色器？
         54  *
         55  * @param x0           "一条线"开始位置的X   - 这个坐标是以 左上角为原点，对哦，android里面所有的绘制都是以控件左上角为原点(0,0)
         56  * @param y0           "一条线"开始位置的Y
         57  * @param x1           "一条线"结束为止的X
         58  * @param y1           "一条线"结束为止的Y
         59  * @param colors       颜色数组，可以是3个以上
         60  * @param positions    颜色分段权重：比如说，有3种颜色，红绿蓝渐变，这里的positions的值是new float[]{0, 0.75f, 1f};
         61  *                     则，0到0.75这一段，是红色渐变为绿色，0.75到1这一段是 绿色渐变为蓝色；
         62  * @param tile         填充模式？
         63  *                     详解：
         64  *                     CLAMP : 重复最后一种颜色直到View结束（当你的起始结束的坐标并没有覆盖整个View，那么这种模式将会用最后一种颜色填充剩余的部分）
         65  *                     REPEAT: 当你的起始结束的坐标并没有覆盖整个View，这种模式将会进行颜色的重新渐变；
         66  *                     MIRROR: 镜像模式绘制,当你的起始结束的坐标并没有覆盖整个View，剩余的部分将会尽量和已经绘制的部分颜色对称;
         67  */
        LinearGradient backGradient = null;
        if (mode == 0) {
            backGradient = new LinearGradient(0, 0, width, 0,
                    new int[]{colorStart, color1, colorEnd,color2}, new float[]{0,0.33f,0.66f, 1f}, Shader.TileMode.CLAMP);
        } else if (mode == 1) {
            backGradient = new LinearGradient(0, 0, 0, height / 2,
                    new int[]{colorStart, color1, colorEnd,color2}, new float[]{0, 0.25f,0.75f, 1f}, Shader.TileMode.REPEAT);
        } else if (mode == 2) {
            backGradient = new LinearGradient(0, 0, 0, height / 2,
                    new int[]{colorStart, color1, colorEnd,color2}, new float[]{0, 0.25f,0.75f, 1f}, Shader.TileMode.MIRROR);
        }


        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);

    }
}
