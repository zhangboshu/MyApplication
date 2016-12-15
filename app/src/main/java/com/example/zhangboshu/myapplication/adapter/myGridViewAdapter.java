package com.example.zhangboshu.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zhangboshu.myapplication.R;

import java.util.List;

/**
 * Created by ZhangBoshu on 2016/12/15.
 */

public class MyGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> imgList;

    public MyGridViewAdapter(Context context, List<String> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList != null ? imgList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_gridview_img, null);
        }
        Glide.with(context).load(imgList.get(position)).asBitmap().into((ImageView) convertView);
        return convertView;
    }
}
