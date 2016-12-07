package com.example.zhangboshu.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.zhangboshu.myapplication.R;

/**
 * Created by zhangboshu on 2016/12/7.
 */

public class ArcDisplay extends View {

    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mTextPaint;
    private int mShowTextSize;
    private int length;
    private int mCircleXY;
    private float mRadius;
    private RectF mArcRectF;
    private float mSweepValue;
    private String mShowText;

    public ArcDisplay(Context context) {
        super(context);
    }

    public ArcDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public ArcDisplay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ArcDisplay);
        // 给中心圆设置属性
        mCirclePaint = new Paint();
        mCirclePaint.setColor(ta.getColor(R.styleable.ArcDisplay_centerCircleColor, ContextCompat.getColor(context, R.color.color_333333)));
        //给弧形设置属性
        mArcPaint = new Paint();
        mArcPaint.setColor(ta.getColor(R.styleable.ArcDisplay_arcColor, ContextCompat.getColor(context, R.color.color_333333)));
        mArcPaint.setStrokeWidth(ta.getInteger(R.styleable.ArcDisplay_arcWidth, 60));
        mArcPaint.setStyle(Paint.Style.STROKE); //空心效果
        setProgress(ta.getFloat(R.styleable.ArcDisplay_arcAngle, 10));
        //给中间文字设置属性
        mTextPaint = new Paint();
        mTextPaint.setColor(ta.getColor(R.styleable.ArcDisplay_centerTextColor, ContextCompat.getColor(context, R.color.colorAccent)));
        mShowTextSize = ta.getInteger(R.styleable.ArcDisplay_centerFontSize, 10);
        mTextPaint.setTextSize(mShowTextSize);

        ta.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        length = w; //画布的宽
        mCircleXY = length / 2; //中心园的xy坐标
        mRadius = (float) (length * 0.5 / 2); //中心圆的半径
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制弧形的外接矩形属性
        mArcRectF = new RectF(
                (float) (length * 0.2),
                (float) (length * 0.2),
                (float) (length * 0.8), (float)
                (length * 0.8));
        //绘制中心圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        //绘制弧形
        canvas.drawArc(mArcRectF, 270, mSweepValue, false, mArcPaint);
        //绘制文字,测量字体宽度,设置在圆的中心
        float textWidth = mTextPaint.measureText(mShowText);
        //通过文字的大小和长度将文字绘制在圆的中心
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY - (textWidth / 2), mCircleXY + (mShowTextSize / 4), mTextPaint);
    }

    //给弧形设置一个角度（百分比的形式）
    public void setProgress(float mSweepValue) {
        if (mSweepValue != 0) {
            this.mSweepValue = (float) (360.0 * (mSweepValue / 100.0));
            mShowText = mSweepValue + "%";
        } else {
            this.mSweepValue = 0;
            mShowText = mSweepValue + "%";
        }
        invalidate();
    }
}
