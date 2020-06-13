package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {

    TextView new_cases_global, total_cases_global,
            new_deaths_global, total_deaths_global,
            new_recoveries_global, total_recoveries_global;
    Button btn_countrywise;
    private DataAdapterNews dataAdapterNews;
    private ArrayList<Article> articles = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


       // btn_countrywise = findViewById(R.id.btn_countrywise);
        new_cases_global = findViewById(R.id.new_cases_global);
        total_cases_global = findViewById(R.id.total_cases_global);
        new_deaths_global = findViewById(R.id.new_deaths_global);
        total_deaths_global = findViewById(R.id.total_deaths_global);
        new_recoveries_global = findViewById(R.id.new_recovered_global);
        total_recoveries_global = findViewById(R.id.total_recovered_global);

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