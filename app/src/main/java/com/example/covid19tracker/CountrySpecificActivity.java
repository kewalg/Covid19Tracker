package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CountrySpecificActivity extends AppCompatActivity {

    TextView country_name,
            new_cases, total_cases,
            new_deaths, total_deaths,
            new_recovered, total_recovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_specific);

        Intent intent = getIntent();
        CountriesItem countriesItem = intent.getParcelableExtra("ExampleItem");

        String country_name1 = countriesItem.getCountry();
        country_name = findViewById(R.id.country_name_new);
        country_name.setText("Country: " + country_name1);

        String new_cases1 = String.valueOf(countriesItem.getNewConfirmed());
        new_cases = findViewById(R.id.new_cases_new);
        new_cases.setText("New Cases: " + new_cases1);

        String total_cases1 = String.valueOf(countriesItem.getTotalConfirmed());
        total_cases = findViewById(R.id.total_cases_new);
        total_cases.setText("Total Cases: " + total_cases1);

        String new_deaths1 = String.valueOf(countriesItem.getNewDeaths());
        new_deaths = findViewById(R.id.new_deaths_new);
        new_deaths.setText("New Deaths: " + new_deaths1);

        String total_deaths1 = String.valueOf(countriesItem.getTotalDeaths());
        total_deaths = findViewById(R.id.total_deaths_new);
        total_deaths.setText("Total Deaths: " + total_deaths1);

        String new_recovered1 = String.valueOf(countriesItem.getNewRecovered());
        new_recovered = findViewById(R.id.new_recovered_new);
        new_recovered.setText("New Recoveries: " + new_recovered1);

        String total_recovered1 = String.valueOf(countriesItem.getTotalRecovered());
        total_recovered = findViewById(R.id.total_recovered_new);
        total_recovered.setText("Total Recoveries: " + total_recovered1);
    }
}