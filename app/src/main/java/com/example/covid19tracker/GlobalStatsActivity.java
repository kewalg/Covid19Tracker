package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalStatsActivity extends AppCompatActivity {
    TextView new_cases_global, total_cases_global,
            new_deaths_global, total_deaths_global,
            new_recoveries_global, total_recoveries_global, tv_today, tv_total, tv_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_stats);
        new_cases_global = findViewById(R.id.new_cases_global);
        total_cases_global = findViewById(R.id.total_cases_global);
        new_deaths_global = findViewById(R.id.new_deaths_global);
        total_deaths_global = findViewById(R.id.total_deaths_global);
        new_recoveries_global = findViewById(R.id.new_recovered_global);
        total_recoveries_global = findViewById(R.id.total_recovered_global);
        tv_today = findViewById(R.id.tv_today);
        tv_today.setPaintFlags(tv_today.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_total = findViewById(R.id.tv_overall);
        tv_total.setPaintFlags(tv_total.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_date = findViewById(R.id.tv_date);
        tv_date.setPaintFlags(tv_total.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        String date_n = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        tv_date.setText("Last Updated On: " + date_n);

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

                DecimalFormat decim = new DecimalFormat("#,###.##");
                String test = "New Cases: " + decim.format(new_cases);
                new_cases_global.setText(test);

                int total_cases = response.body().getGlobal().getTotalConfirmed();
                DecimalFormat decim1 = new DecimalFormat("#,###.##");
                String test1 = "Total Cases: " + decim1.format(total_cases);
                total_cases_global.setText(test1);

                int new_deaths = response.body().getGlobal().getNewDeaths();
                DecimalFormat decim2 = new DecimalFormat("#,###.##");
                String test2 = "New Deaths: " + decim2.format(new_deaths);
                new_deaths_global.setText(test2);

                int total_deaths = response.body().getGlobal().getTotalDeaths();
                DecimalFormat decim3 = new DecimalFormat("#,###.##");
                String test3 = "Total Deaths: " + decim3.format(total_deaths);
                total_deaths_global.setText(test3);

                int new_recoveries = response.body().getGlobal().getNewRecovered();
                DecimalFormat decim4 = new DecimalFormat("#,###.##");
                String test4 = "New Recoveries: " + decim4.format(new_recoveries);
                new_recoveries_global.setText(test4);

                int total_recoveries = response.body().getGlobal().getTotalRecovered();
                DecimalFormat decim5 = new DecimalFormat("#,###.##");
                String test5 = "Total Recoveries: " + decim5.format(total_recoveries);
                total_recoveries_global.setText(test5);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(GlobalStatsActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}