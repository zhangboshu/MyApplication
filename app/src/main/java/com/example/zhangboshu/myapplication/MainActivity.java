package com.example.zhangboshu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhangboshu.myapplication.widget.ArcDisplay;
import com.example.zhangboshu.myapplication.widget.TopBar;

public class MainActivity extends AppCompatActivity {

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
                Toast.makeText(MainActivity.this, "左边", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Intent intent = new Intent(MainActivity.this, GridViewTextActivity.class);
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
