package com.example.zhangboshu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhangboshu.myapplication.widget.TopBar;

public class MainActivity extends AppCompatActivity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button1);
        topBar = (TopBar) findViewById(R.id.topBar);
        topBar.setTopBarListener(new TopBar.TopBarListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "左边", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "右边", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
