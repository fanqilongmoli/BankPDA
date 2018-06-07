package com.flc.bankpda.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.flc.bankpda.R;

public class DashLineView extends View {

    private Paint mPaint;
    private Path mPath;

    public DashLineView(Context context) {
        this(context,null);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.dash_line));
        // 需要加上这句，否则画不出东西
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setPathEffect(new DashPathEffect(new float[] {5, 5}, 0));

        mPath = new Path();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        mPath.reset();
        mPath.moveTo(0, centerY);
        mPath.lineTo(getWidth(), centerY);
        canvas.drawPath(mPath, mPaint);
    }

}
