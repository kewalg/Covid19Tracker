package com.example.covid19tracker;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ArrayList<CountriesItem> countriesItems;
    private ArrayList<CountriesItem> countriesItemsFull;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DataAdapter(ArrayList<CountriesItem> countriesItems, Context context) {
        this.context = context;
        this.countriesItems = countriesItems;
        countriesItemsFull = new ArrayList<>(countriesItems);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView country_name,
                new_cases, total_cases,
                new_deaths, total_deaths,
                new_recovered, total_recovered;

        public ViewHolder(@NonNull View view, final OnItemClickListener listener) {
            super(view);
            country_name = view.findViewById(R.id.country_name);
            new_cases = view.findViewById(R.id.new_cases);
            total_cases = view.findViewById(R.id.total_cases);
            new_deaths = view.findViewById(R.id.new_deaths);
            total_deaths = view.findViewById(R.id.total_deaths);
            new_recovered = view.findViewById(R.id.new_recovered);
            total_recovered = view.findViewById(R.id.total_recovered);
            country_name.setPaintFlags(country_name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            view.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,
                viewGroup, false);
        return new ViewHolder(view, (OnItemClickListener) mListener);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int i) {

        final String country_name = countriesItems.get(i).getCountry();
        final String new_cases = String.valueOf(countriesItems.get(i).getNewConfirmed());
        final String total_cases = String.valueOf(countriesItems.get(i).getTotalConfirmed());
        final String new_deaths = String.valueOf(countriesItems.get(i).getNewDeaths());
        final String total_deaths = String.valueOf(countriesItems.get(i).getTotalDeaths());
        final String new_recovered = String.valueOf(countriesItems.get(i).getNewRecovered());
        final String total_recovered = String.valueOf(countriesItems.get(i).getTotalRecovered());


        viewHolder.country_name.setText(country_name);
        viewHolder.new_cases.setText("New Cases: " + new_cases);
        viewHolder.total_cases.setText("Total Cases: " + total_cases);
        viewHolder.new_deaths.setText(new_deaths);
        viewHolder.total_deaths.setText(total_deaths);
        viewHolder.new_recovered.setText(new_recovered);
        viewHolder.total_recovered.setText(total_recovered);
    }

    @Override
    public int getItemCount() {
        return countriesItems.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CountriesItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(countriesItemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CountriesItem item : countriesItemsFull) {
                    if (item.getCountry().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countriesItems.clear();
            countriesItems.addAll((Collection<? extends CountriesItem>) results.values);
            notifyDataSetChanged();
        }
    };


}
