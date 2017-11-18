package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Practice11PieChartView extends View {
    private float radius;
    private List<Data> datas;
    private Paint paint;
    private RectF rectF;
    private float total;
    private float max;
    private boolean isMove = false;

    float startAngle = 0f; // 开始的角度
    float sweepAngle;      // 扫过的角度
    float lineAngle;       // 当前扇形一般的角度

    float lineStartX = 0f; // 直线开始的X坐标
    float lineStartY = 0f; // 直线开始的Y坐标
    float lineEndX;        // 直线结束的X坐标
    float lineEndY;        // 直线结束的Y坐标



    {
        radius = 300;
        datas = new ArrayList<>();
        datas.add(new Data("Gingerbread", 10.0f, Color.WHITE));
        datas.add(new Data("Ice Cream Sandwich", 18.0f, Color.MAGENTA));
        datas.add(new Data("Jelly Bean", 22.0f, Color.GRAY));
        datas.add(new Data("KitKat", 27.0f, Color.GREEN));
        datas.add(new Data("Lollipop", 40.0f, Color.BLUE));
        datas.add(new Data("Marshmallow", 60.0f, Color.RED));
        datas.add(new Data("Nougat", 33.5f, Color.YELLOW));
        total = 0.0f;
        max = Float.MIN_VALUE;
        for (Data d : datas) {
            total += d.getNumber();
            max = Math.max(max, d.getNumber());
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        rectF = new RectF(-300, -300, 300, 300);

    }

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);  // 将画布(0，0)坐标点移到画布的中心
        startAngle = 0f; //这句代码很重要，不然有bug
        for (Data data : datas) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(data.getColor());
            sweepAngle = data.getNumber() / total * 360f;
            lineAngle = startAngle + sweepAngle / 2;
            lineStartX = radius * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineStartY = radius * (float) Math.sin(lineAngle / 180 * Math.PI);
            lineEndX = (radius + 50) * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineEndY = (radius + 50) * (float) Math.sin(lineAngle / 180 * Math.PI);
            if (data.getNumber() == max) {
                canvas.save();
                canvas.translate(lineStartX * 0.1f, lineStartY * 0.1f);
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            } else {
                canvas.drawArc(rectF, startAngle, sweepAngle - 1.0f, true, paint);
            }
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);
            if (lineAngle > 90 && lineAngle <= 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX - 50 - 10 - paint.measureText(data.getName()), lineEndY, paint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX + 50 + 10, lineEndY, paint);
            }
            if (data.getNumber() == max) {
                canvas.restore();
            }
            startAngle += sweepAngle;
        }

    }
}