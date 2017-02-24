package com.example.zhangboshu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zhangboshu.myapplication.widget.ArcDisplay;
import com.example.zhangboshu.myapplication.widget.TopBar;

public class MainActivity extends BaseActivity {

    private TopBar topBar;
    private ArcDisplay myArc;
    private EditText myEdt;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topBar = (TopBar) findViewById(R.id.topBar);
        topBar.setTopBarListener(new TopBar.TopBarListener() {
            @Override
            public void leftClick() {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }

            @Override
            public void rightClick() {
                Intent intent = new Intent(MainActivity.this, SvgTextActivity.class);
                startActivity(intent);
            }
        });

        myArc = (ArcDisplay) findViewById(R.id.my_arc);
        myEdt = (EditText) findViewById(R.id.my_edt);
        mButton = (Button) findViewById(R.id.my_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myArc.setProgress(Float.valueOf(myEdt.getText().toString().trim()));
            }
        });
    }
}
