package com.example.zhangboshu.myapplication;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class SvgTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_text);

    }

    public void anim(View view) {
        ImageView iamgeview = (ImageView) findViewById(R.id.iamgeview);
        Drawable drawable = iamgeview.getDrawable();
        if (drawable instanceof Animatable){
            ((Animatable) drawable).start();
        }
    }
}
