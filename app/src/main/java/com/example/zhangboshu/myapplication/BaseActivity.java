package com.example.zhangboshu.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangboshu.myapplication.utils.ConnectionChangeReceiver;

public abstract class BaseActivity extends AppCompatActivity implements ConnectionChangeReceiver.NetStateInterface {

    private ConnectionChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new ConnectionChangeReceiver(this);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void getNetState(String state) {

    }
}
