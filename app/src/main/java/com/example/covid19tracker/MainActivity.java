package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DataAdapter dataAdapter;
    private ArrayList<CountriesItem> countriesItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TextView tv_lastupdatedon_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv_lastupdatedon_country = findViewById(R.id.tv_lastupdatedon_country);
        tv_lastupdatedon_country.setPaintFlags(tv_lastupdatedon_country.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        String date_n = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        tv_lastupdatedon_country.setText("Last Updated On: " + date_n);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parseJSON();
    }

    private void parseJSON() {
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
                    Toast.makeText(MainActivity.this, "ERROR CODE: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                countriesItems = new ArrayList<>(response.body().getCountries());
                dataAdapter = new DataAdapter(countriesItems, MainActivity.this);
                mRecyclerView.setAdapter(dataAdapter);

                dataAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dataAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}