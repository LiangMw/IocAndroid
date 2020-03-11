package com.integrate.netstudyapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 梁明伟 on 2019/6/17.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class PaintViewBasic extends View {
    private Paint paint;

    public PaintViewBasic(Context context) {
        super(context);
        paint = new Paint();
    }

    public PaintViewBasic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawStyle(canvas);
        drawPathEffect(canvas);
    }

    private void drawStyle( Canvas canvas ) {

        paint.setColor(Color.RED);//设置画笔的颜色
        paint.setTextSize(60);//设置文字大小
        paint.setStrokeWidth(20);//设置画笔的宽度
        paint.setAntiAlias(true);//设置抗锯齿功能 true表示抗锯齿 false则表示不需要这功能
        paint.setStyle(Paint.Style.STROKE );
//        paint.setAntiAlias( true );
//        paint.setStrokeWidth( 80 );
//        paint.setStyle(Paint.Style.STROKE ); // 默认是填充 Paint.Style.FILL
//        paint.setColor( Color.parseColor("#0000ff") );

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(400, 100);
        path.lineTo(100, 300);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, paint);

        path.moveTo(100, 500);
        path.lineTo(400, 500);
        path.lineTo(100, 700);
        path.lineTo(300, 700);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);

        path.moveTo(100, 900);
        path.lineTo(400, 900);
        path.lineTo(100, 1100);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, paint);

    }

    private float phase;
    private PathEffect[] effects;
    private  int[] colors;

    private void drawPathEffect(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        // 创建,并初始化Path
        Path path = new Path();
        path.moveTo(0, 0);
        for (int i = 1; i <= 45; i++) {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            path.lineTo(i * 20, (float) Math.random() * 60);
        }
        // 初始化七个颜色
        colors = new int[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED,
                Color.GRAY};


        // 将背景填充成白色
        canvas.drawColor(Color.WHITE);
        effects = new PathEffect[7];
        // -------下面开始初始化7中路径的效果
        // 使用路径效果
        effects[0] = null;
        // 使用CornerPathEffect路径效果
        effects[1] = new CornerPathEffect(10);
        // 初始化DiscretePathEffect
        effects[2] = new DiscretePathEffect(3.0f, 5.0f);
        // 初始化DashPathEffect
        effects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);
        // 初始化PathDashPathEffect
        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.TRANSLATE);
        // 初始化PathDashPathEffect
        effects[5] = new ComposePathEffect(effects[2], effects[4]);
        effects[6] = new SumPathEffect(effects[4], effects[3]);
        // 将画布移到8,8处开始绘制
        canvas.translate(8, 8);
        // 依次使用7中不同路径效果,7种不同的颜色来绘制路径
        for (int i = 0; i < effects.length; i++) {
            paint.setPathEffect(effects[i]);
            paint.setColor(colors[i]);
            canvas.drawPath(path, paint);
            canvas.translate(0, 200);
        }
        // 改变phase值,形成动画效果
        phase += 1;
        invalidate();
    }
}
