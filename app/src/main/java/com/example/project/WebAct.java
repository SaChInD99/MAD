package com.example.project;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url = "https://www.paypal.com/lk/home";
        WebView web= (WebView) findViewById(R.id.webvi);
        web.loadUrl(url);
    }
}