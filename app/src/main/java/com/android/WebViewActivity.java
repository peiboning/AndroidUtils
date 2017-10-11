package com.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sohu.util.demo.R;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WebViewActivity extends Activity {

    private WebView mWebView;
    boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.ad_view);
        initWebView();
        loadNews();
    }

    private void loadNews() {
        mWebView.loadUrl("http://m.sohu.com");
//        mWebView.loadUrl("https://m.sohu.com/a/195411645_685454?_f=m-index_top_news_2");
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsObj(this), "myObj");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("pbn","onPageFinished.....\nurl: " +url );
                if(isBack){
                    if("http://m.sohu.com/".equals(url)){
                        isBack = false;
                    }else{
                        view.goBack();
                    }
                }
                Log.e("pbn","onPageFinished.....\nisBack: " +isBack );

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.e("pbn","shouldOverrideUrlLoading.....\nurl:" + request.getUrl() + "\n");

                Map<String, String> headers = request.getRequestHeaders();
                if(null != headers){
                    Set<String> set = headers.keySet();
                    for(String key : set){
                        Log.e("pbn",key + ":" + headers.get(key));
                    }
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }

    public void clickAd(View view){
        String js = "var d = document.getElementById('ceans_12416_0');";
        js = js+" var evt = document.createEvent(\"MouseEvents\");" +
                " evt.initMouseEvent(\"click\", false, true, window, 1, 1, 1, 1, 1, false, false, false, false, 0, null);" +
                " d.dispatchEvent(evt);";
        Log.e("pbn","" + js);
        mWebView.loadUrl("javascript:" + js);
    }
    public void clickDetailAd(View view){
        String js = " var middleAdPanel = window.oMsjs.oNices.middle;" +
                " var a = middleAdPanel.oBean.oDatas[0].resource.click;" +
                " middleAdPanel.fReportClick({cx: 10, cy: 10}, a)";
        Log.e("pbn","detail\n" + js);
        mWebView.loadUrl("javascript:" + js);
    }

    public void back(View view){
        isBack = true;
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            Log.e("pbn","goback .......");
        }
    }
}
