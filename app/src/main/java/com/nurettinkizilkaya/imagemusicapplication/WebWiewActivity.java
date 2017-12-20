package com.nurettinkizilkaya.imagemusicapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebWiewActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_wiew);
        MyWebViewClient s= new MyWebViewClient();

        webview = (WebView) findViewById(R.id.webview);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        String url="http://google.com.tr";
        webview.loadUrl(url);


        progressDialog=ProgressDialog.show(WebWiewActivity.this,"Sayfa Yükleniyor","Lütfen Bekleyiniz...");
        progressDialog.setCancelable(false);
        s.onPageFinished(webview,url);
    }
    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            progressDialog.dismiss();
            super.onPageFinished(view, url);
        }
    }

    @Override
    public void onBackPressed() {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        else if(webview.canGoBack()){
            webview.goBack();

        }else{
            super.onBackPressed();
        }

    }
}
