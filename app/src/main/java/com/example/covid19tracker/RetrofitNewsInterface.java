package com.example.covid19tracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitNewsInterface {

    @GET("top-headlines?q=coronavirus&apiKey=d18edb0ccb7644ea9679c69556fbb042")
    Call<News> getNews();
}
