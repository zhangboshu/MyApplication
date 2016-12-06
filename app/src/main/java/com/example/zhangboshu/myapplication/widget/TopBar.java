package com.example.zhangboshu.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhangboshu.myapplication.R;

/**
 * Created by zhangboshu on 2016/12/6.
 */

public class TopBar extends RelativeLayout {

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;
    private Drawable mRightBackground;
    private int mRightTextColor;
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;
    private TopBarListener topBarListener;
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    public TopBar(Context context) { //1参构造器,在new出时被调用
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) { //2参构造器,在xml中被调用
        super(context, attrs);
        //将attrs中自定义的declare-styleable的所有属性值储存到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        //从TypedArray中取出对应的值并且赋值,第二个参数是默认值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);

        mTitle = ta.getString(R.styleable.TopBar_title);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 14);
        //回收资源,必须写
        ta.recycle();
        //设置资源
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);
        //为创建的组件赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setText(mRightText);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setTextColor(mRightTextColor);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);

        mTitleView.setGravity(Gravity.CENTER);
        //为组件元素设置相应布局
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE); //相对布局添加显示位置
        addView(mLeftButton, mLeftParams); //添加到viewGroup中

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView, mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarListener.rightClick();
            }
        });
    }

    public void setTopBarListener(TopBarListener t){
        topBarListener = t;
    }

    public interface TopBarListener{
        void leftClick();
        void rightClick();
    }

    //动态显示, 通过id和flag区分显示按钮
    public void setButtonVisable(int id, boolean flag){
        if (flag){
            if (id == 0){
                mLeftButton.setVisibility(VISIBLE);
            }else{
                mRightButton.setVisibility(VISIBLE);
            }
        }else{
            if (id == 0){
                mLeftButton.setVisibility(GONE);
            }else{
                mRightButton.setVisibility(GONE);
            }
        }
    }
}
