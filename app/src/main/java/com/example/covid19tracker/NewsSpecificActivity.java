package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class NewsSpecificActivity extends AppCompatActivity {

    TextView tv_headlines, tv_desc;
    ImageView iv_url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_specific);
        /*iv_url = findViewById(R.id.iv_url_specific);
        tv_headlines = findViewById(R.id.tv_headlines_specific);
        tv_desc = findViewById(R.id.tv_desc_specific);*/
        webView = findViewById(R.id.webview);

        Intent i = getIntent();
        Article article = i.getParcelableExtra("NewsItem");
        String image_url = article.getUrlToImage();
        String title = article.getTitle();
        String url = article.getUrl();
        String desc = article.getDescription();
        String content = String.valueOf(article.getContent());


        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);


        /*Glide.with(this)
                .load(image_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .override(800, 800)
                .into(iv_url);*/
/*
        tv_headlines.setText(title);
        tv_desc.setText(desc);*/
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}