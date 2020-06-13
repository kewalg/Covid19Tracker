package com.example.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class AdapterForMain extends PagerAdapter {

    private List<ModelForMain> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterForMain(List<ModelForMain> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_layout_main, container, false);
        ImageView imageView;
        TextView tv1, tv2;

        imageView = view.findViewById(R.id.image_main);
        tv1 = view.findViewById(R.id.title_main);
        tv2 = view.findViewById(R.id.desc_main);

        imageView.setImageResource(models.get(position).getImage());
        tv1.setText(models.get(position).getTitle());
        tv2.setText(models.get(position).getDesc());
        container.addView(view, 0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent myIntent = new Intent(context, GlobalStatsActivity.class);
                    context.startActivity(myIntent);
                }
                if (position == 1) {
                    Intent myIntent = new Intent(context, MainActivity.class);
                    context.startActivity(myIntent);
                }
                if (position == 2) {
                    Intent myIntent = new Intent(context, NewsActivity.class);
                    context.startActivity(myIntent);
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
