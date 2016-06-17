package com.example.akshi.afpomotion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by akshi on 6/16/2016.
 */
public class AFWebView extends AppCompatActivity {

    private WebView afWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.af_webview);
        afWebview = (WebView) findViewById(R.id.web_view);
        afWebview.getSettings().setJavaScriptEnabled(true);
        afWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String url = getIntent().getStringExtra(AFConstants.TARGET_URL);
        afWebview.loadUrl(url);


    }
}
