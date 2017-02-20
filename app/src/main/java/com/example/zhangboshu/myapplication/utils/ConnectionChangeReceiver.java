package com.example.zhangboshu.myapplication.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ZhangBoshu on 2017/2/20.
 */

public class ConnectionChangeReceiver extends BroadcastReceiver {

    private final NetStateInterface netStateInterface;

    public ConnectionChangeReceiver(NetStateInterface netStateInterface) {
        this.netStateInterface = netStateInterface;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        boolean success = false;

        //获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // State state = connManager.getActiveNetworkInfo().getState();
        // 获取WIFI网络连接状态
        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        // 判断是否正在使用WIFI网络
        if (NetworkInfo.State.CONNECTED == state) {
            success = true;
            netStateInterface.getNetState("wifi");
        }
        // 获取GPRS网络连接状态
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        // 判断是否正在使用GPRS网络
        if (NetworkInfo.State.CONNECTED == state) {
            success = true;
            netStateInterface.getNetState("mobile");
        }

        if (!success) {
            netStateInterface.getNetState("noNet");
        }
    }

    public interface NetStateInterface {
        void getNetState(String state);
    }
}