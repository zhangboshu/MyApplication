package com.example.zhangboshu.myapplication;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webview);

        initWebView(webView, "http://admin.yiliku.com/video.html");
    }

    public static void initWebView(WebView mWebView, String url) {
        WebSettings set = mWebView.getSettings();
//设置开启js支持
        set.setJavaScriptEnabled(true);
//支持Js开启新的窗口
        set.setJavaScriptCanOpenWindowsAutomatically(true);
//设置在WebView内部是否允许访问文件，默认允许访问
        set.setAllowFileAccess(true);
//重写缓存被使用到的方法，该方法基于Navigation Type，加载普通的页面，将会检查缓存同时重新验证是否需要加载，如果不需要重新加载，将直接从缓存读取数据，允许客户端通过指定LOAD_DEFAULT、LOAD_CACHE_ELSE_NETWORK、LOAD_NO_CACHE、LOAD_CACHE_ONLY其中之一重写该行为方法，默认值LOAD_DEFAULT
        set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放。
        set.setSupportZoom(true);
        // 设置出现缩放工具
        set.setBuiltInZoomControls(true);
        //扩大比例的缩放
        set.setUseWideViewPort(true);
        //自适应屏幕
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置WebView是否使用预览模式加载界面
        set.setLoadWithOverviewMode(true);
        //设置WebView是否加载图片资源，默认true，自动加载图片
        set.setLoadsImagesAutomatically(true);
        //webview 支持播放插件
        set.setPluginState(WebSettings.PluginState.ON);
        //辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWebView.setWebChromeClient(new WebChromeClient());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            set.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //WebViewClient就是帮助WebView处理各种通知、请求事件的。
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            //处理http请求时调用的方法
            public void onReceivedSslError(final WebView view, final SslErrorHandler handler, SslError error) {
                handler.proceed(); //等待证书响应
                //     handler.cancel();   //挂起连接
                //     handler.handleMessage(null);  //可做其他处理

            }
            //对网页中超链接按钮的响应
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
            //载入页面完成的事件
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            //载入页面开始的事件
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
            //更新历史记录
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }
        });
        mWebView.loadUrl(url);
    }

}
