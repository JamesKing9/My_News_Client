package com.cheng.a360news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 活动界面：新闻详情
 */
public class NewsDetailActivity extends Activity {

    /**绑定布局中的webView控件*/
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置窗口为无标题窗口
        setContentView(R.layout.activity_news_detail);

        /**将使用ButterKnife找到的控件绑定到当前 Activity上*/
        ButterKnife.bind(this);

        //初始化WebView
        webView.setWebViewClient(new WebViewClient(){
            //页面加载完成后，执行该回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //给网页里面的img标签去添加点击事件
                addPictureClick();
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);

        String url = getIntent().getStringExtra("url");
        //加载网页
        webView.loadUrl(url);

        //传递对象给webview
        webView.addJavascriptInterface(new JsCallJava() {
            @JavascriptInterface
            @Override
            public void openImage(String src) {
                Intent intent = new Intent(getApplicationContext(),ShowActivity.class);
                //使用Intent 在 2 个不同的 Activity间传递数据
                intent.putExtra("url",src);
                startActivity(intent);
            }
        },"imagelistner");

    }

    private void addPictureClick() {

        //android调用js代码
        webView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "    objs[i].onclick=function()  " + "   " + " {  "
                + "        window.imagelistner.openImage(this.src);  "
                + "    }  " + "}" + "})()");

    }

    public interface JsCallJava{
        public void openImage(String src);
    }

}
