package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.View;

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
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "Brochure", "TEST")));
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "Brochure", "TEST")));
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "Brochure", "TEST")));
        models.add((new ModelForMain(R.drawable.ic_launcher_background, "Brochure", "TEST")));
        adapterForMain = new AdapterForMain(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapterForMain);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {getColor(R.color.color1),
                getColor(R.color.color2),
                getColor(R.color.color3),
                getColor(R.color.color4)};

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