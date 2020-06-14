package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {


    private DataAdapterNews dataAdapterNews;
    private ArrayList<Article> articles = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TextView tv_lastupdatedon_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        tv_lastupdatedon_news = findViewById(R.id.tv_lastupdatedon_news);

        tv_lastupdatedon_news.setPaintFlags(tv_lastupdatedon_news.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        String date_n = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        tv_lastupdatedon_news.setText("Last Updated On: " + date_n);

        mRecyclerView = findViewById(R.id.recycler_view_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parseNewsJSON();
    }

    private void parseNewsJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitNewsInterface retrofitNewsInterface = retrofit.create(RetrofitNewsInterface.class);
        Call<News> call = retrofitNewsInterface.getNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, retrofit2.Response<News> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(NewsActivity.this, "ERROR CODE: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                articles = new ArrayList<>(response.body().getArticles());
                dataAdapterNews = new DataAdapterNews(articles, NewsActivity.this);
                mRecyclerView.setAdapter(dataAdapterNews);

                dataAdapterNews.setOnItemClickListener(new DataAdapterNews.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(NewsActivity.this, NewsSpecificActivity.class);
                        i.putExtra("NewsItem", articles.get(position));
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}