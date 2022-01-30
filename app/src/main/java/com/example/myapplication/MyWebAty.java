package com.example.myapplication;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MyWebAty extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_aty);

        wv = findViewById(R.id.wv);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://blog.csdn.net/m0_46350041/article/details/105394635");
    }
}