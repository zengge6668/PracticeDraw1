package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Practice10HistogramView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Map<String,Float> data = new LinkedHashMap<>();

    {
        data.put("Froyo",0.001f);
        data.put("GB",0.005f);
        data.put("ICS",0.005f);
        data.put("JB",0.062f);
        data.put("KitKat",0.138f);
        data.put("L",0.272f);
        data.put("M",0.309f);
    }

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        int width = getWidth();
        int height = getHeight();
        int rwidth = width/11; //每个方块宽度
        int space = 20; //间距
        //画坐标轴
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        canvas.drawLine(width/9,4*height/5,width/9,20,paint);
        canvas.drawLine(width/9,4*height/5,8*width/9,4*height/5,paint);

        Iterator it = data.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String key = (String)e.getKey();
            float value = (Float)e.getValue();
            float left = width/9+space + i*(space + rwidth);
            float top = 4*height/5 - value*2*height;
            //句型
            paint.setColor(Color.parseColor("#72b916"));
            canvas.drawRect(left,top,left+rwidth,4*height/5,paint);

            //名字
            paint.setColor(Color.WHITE);
            paint.setTextSize(24);
            Paint.FontMetricsInt metrics = paint.getFontMetricsInt();
            float textheight = metrics.descent - metrics.ascent;
            float textWidth = paint.measureText(key);
            canvas.drawText(key,left + rwidth/2 - textWidth/2,4*height/5+textheight,paint); //实际上文字的高度就等于setTextSize？
            i++;

        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(48);
        float twidth = paint.measureText("直方图");
        canvas.drawText("直方图",width/2 - twidth/2,height-20,paint);

    }
}
