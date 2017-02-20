package com.example.zhangboshu.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.zhangboshu.myapplication.R;
import com.example.zhangboshu.myapplication.utils.DisplayUtil;

/**
 * Created by ZhangBoshu on 2017/2/14.
 */

public class RatioImageView extends ImageView {

    private int mWidth;
    private int mHeight;
    private Paint paint;
    private float roundSize;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        mWidth = ta.getInteger(R.styleable.RatioImageView_ratioWidth, 1);
        mHeight = ta.getInteger(R.styleable.RatioImageView_ratioHeight, 1);
        roundSize = ta.getDimension(R.styleable.RatioImageView_roundedSize, 0);
        ta.recycle();
        paint = new Paint();
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        heightSize = (widthSize / mWidth * mHeight);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null){
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap roundBitmap = getRoundBitmap(bitmap, DisplayUtil.dip2px(getContext(), roundSize));
            Rect rectSrc = new Rect(0, 0, roundBitmap.getWidth(), roundBitmap.getHeight());
            Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
            paint.reset();
            canvas.drawBitmap(roundBitmap, rectSrc, rectDest, paint);
        }else{
            super.onDraw(canvas);
        }
    }

    private Bitmap getRoundBitmap(Bitmap bitmap, int roundPx){

        final int color = 0xff424242;

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0,0,0,0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
