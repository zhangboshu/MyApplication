package com.example.zhangboshu.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.zhangboshu.myapplication.adapter.MyGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridViewTextActivity extends BaseActivity {

    private GridView myGridView;
    private List<String> imgList;
    private MyGridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_text);

        initViews();
    }

    private List<String> getData(){
        imgList = new ArrayList<>();
        imgList.add("http://img.chinatimes.com/newsphoto/2016-04-25/656/20160425002275.jpg");
        imgList.add("http://userimage3.360doc.com/13/0615/00/11263728_201306150037540167.jpg");
        imgList.add("http://media.steampowered.com/steamcommunity/public/images/avatars/70/70f64859006f9f182a232d051dc0810499a31761_full.jpg");
        imgList.add("http://wiki.komica.org/pix/img1647.jpg");
        imgList.add("http://a1.att.hudong.com/82/83/28300534451857135324833965118_140.jpg");

        imgList.add("http://pic.qiantucdn.com/58pic/18/82/10/56554d4f8efce_1024.jpg");
        imgList.add("http://comic.imgshangman.com/13/06/20/cb2c6f9adf0f577c964eb9cf97c3352d.thumb.jpg");
        imgList.add("http://www.sliwu6.com/images/201605/goods_img/4476_P_1463498146051.jpg");
        imgList.add("http://www.youyix.com/uploads/allimg/160902/1-160Z21514334P.jpg");
        imgList.add("http://www.sliwu6.com/images/201605/goods_img/4476_P_1463498146932.jpg");
        return imgList;
    }

    private void initViews() {
        myGridView = (GridView) findViewById(R.id.text_gridview);
        imgList = getData();
        adapter = new MyGridViewAdapter(this, imgList);
        myGridView.setAdapter(adapter);

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setMySelect(position);
            }
        });


    }

}
