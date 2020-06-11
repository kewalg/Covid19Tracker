package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalStatsActivity extends AppCompatActivity {
    TextView new_cases_global, total_cases_global,
            new_deaths_global, total_deaths_global,
            new_recoveries_global, total_recoveries_global;
    Button btn_countrywise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_stats);

        btn_countrywise = findViewById(R.id.btn_countrywise);
        new_cases_global = findViewById(R.id.new_cases_global);
        total_cases_global = findViewById(R.id.total_cases_global);
        new_deaths_global = findViewById(R.id.new_deaths_global);
        total_deaths_global = findViewById(R.id.total_deaths_global);
        new_recoveries_global = findViewById(R.id.new_recovered_global);
        total_recoveries_global = findViewById(R.id.total_recovered_global);
        btn_countrywise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GlobalStatsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        parseJSON();


    }


    private void parseJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Response> call = retrofitInterface.getStats();
        call.enqueue(new Callback<Response>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GlobalStatsActivity.this, "ERROR CODE: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                assert response.body() != null;
                int new_cases = response.body().getGlobal().getNewConfirmed();
                new_cases_global.setText("New Cases Today: " + new_cases);
                int total_cases = response.body().getGlobal().getTotalConfirmed();
                total_cases_global.setText("Total Cases: " + total_cases);
                int new_deaths = response.body().getGlobal().getNewDeaths();
                new_deaths_global.setText("New Deaths Today: " + new_deaths);
                int total_deaths = response.body().getGlobal().getTotalDeaths();
                total_deaths_global.setText("Total Deaths: " + total_deaths);
                int new_recoveries = response.body().getGlobal().getNewRecovered();
                new_recoveries_global.setText("New Recoveries Today: " + new_recoveries);
                int total_recoveries = response.body().getGlobal().getTotalRecovered();
                total_recoveries_global.setText("Total Recoveries: " + total_recoveries);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(GlobalStatsActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}