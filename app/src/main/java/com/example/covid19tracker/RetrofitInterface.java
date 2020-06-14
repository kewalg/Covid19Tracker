package com.example.covid19tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("summary")
    Call<Response> getStats();
}
