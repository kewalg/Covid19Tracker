package com.example.covid19tracker;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {
    ViewPager viewPager;
    AdapterForMain adapterForMain;
    List<ModelForMain> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        models = new ArrayList<>();
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "Global Stats", "COVID-19 stats around the world!")));
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "Country Specific Stats", "COVID-19 stats specific to each country!")));
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "COVID-19 in News", "Coverage of latest news related to COVID-19")));
        adapterForMain = new AdapterForMain(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapterForMain);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {getColor(R.color.color1),
                getColor(R.color.color2),
                getColor(R.color.color3)};

        colors = colors_temp;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapterForMain.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset
                            , colors[position], colors[position + 1]));
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
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