package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tvtest);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<com.example.covid19tracker.Response> call = retrofitInterface.getStats();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (!response.isSuccessful()) {
                    tv.setText("Code: " + response.code());
                    return;
                }
                List<CountriesItem> countriesItemList = response.body().getCountries();
                for (CountriesItem countriesItem : countriesItemList) {
                    String content = "";
                    content += "Country Name: " + countriesItem.getCountry() + "\n";
                    content += "New Cases: " + countriesItem.getNewConfirmed() + "\n";
                    content += "Total Cases: " + countriesItem.getTotalConfirmed() + "\n";
                    content += "New Deaths: " + countriesItem.getNewDeaths() + "\n";
                    content += "Total Deaths: " + countriesItem.getTotalDeaths() + "\n";
                    content += "Recovered Today: " + countriesItem.getNewRecovered() + "\n";
                    content += "Total Recovered: " + countriesItem.getTotalRecovered() + "\n\n";
                    tv.append(content);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                tv.setText(t.getMessage());

            }
        });
    }
}