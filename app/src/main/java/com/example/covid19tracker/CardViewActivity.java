package com.example.covid19tracker;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {
    ViewPager viewPager;
    AdapterForMain adapterForMain;
    List<ModelForMain> models;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView tv_aboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        tv_aboutme = findViewById(R.id.tv_aboutme);
        tv_aboutme.setPaintFlags(tv_aboutme.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        models = new ArrayList<>();
        models.add((new ModelForMain(R.drawable.test, "Global Stats", "COVID-19 Stats Around The World.")));
        models.add((new ModelForMain(R.drawable.test1, "Country Specific Stats", "COVID-19 Stats Specific To Each Country.")));
        models.add((new ModelForMain(R.drawable.test2, "COVID-19 in News", "Latest COVID-19 News.")));

        adapterForMain = new AdapterForMain(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapterForMain);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}