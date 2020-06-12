package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataAdapterNews extends RecyclerView.Adapter<DataAdapterNews.ViewHolder> {

    private ArrayList<Article> articles = new ArrayList<>();
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DataAdapterNews(ArrayList<Article> articles, Context context) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public DataAdapterNews.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout_news, viewGroup, false);
        return new ViewHolder(view, (OnItemClickListener) mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapterNews.ViewHolder viewHolder, final int i) {
        final String title = articles.get(i).getTitle();
        final String desc = articles.get(i).getDescription();
        final String url = articles.get(i).getUrl();
        final String publishedAt = articles.get(i).getPublishedAt();
        final String imageURL = String.valueOf(articles.get(i).getUrlToImage());

        viewHolder.tv_title.setText(title);
        viewHolder.tv_desc.setText(desc);
        viewHolder.tv_url.setText(url);
        viewHolder.tv_publishedAt.setText(publishedAt);

        Glide.with(context)
                .load(imageURL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .override(800, 800)
                .into(viewHolder.iv_url);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_desc, tv_url, tv_publishedAt;
        private ImageView iv_url;

        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title_news);
            tv_desc = view.findViewById(R.id.tv_desc_news);
            tv_url = view.findViewById(R.id.tv_url_news);
            tv_publishedAt = view.findViewById(R.id.tv_publishedAt);
            iv_url = view.findViewById(R.id.iv_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
