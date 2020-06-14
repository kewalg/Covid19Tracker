package com.example.covid19tracker;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ArrayList<CountriesItem> countriesItems;
    private ArrayList<CountriesItem> countriesItemsFull;
    private Context context;
    private OnItemClickListener mListener;
    private CardView cardView;


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
                new_recovered, total_recovered, tv_today_country, tv_total_country;
        LinearLayout expandableLayout;

        public ViewHolder(@NonNull View view, final OnItemClickListener listener) {
            super(view);
            country_name = view.findViewById(R.id.country_name);
            new_cases = view.findViewById(R.id.new_cases);
            total_cases = view.findViewById(R.id.total_cases);
            new_deaths = view.findViewById(R.id.new_deaths);
            total_deaths = view.findViewById(R.id.total_deaths);
            new_recovered = view.findViewById(R.id.new_recovered);
            total_recovered = view.findViewById(R.id.total_recovered);
            tv_today_country = view.findViewById(R.id.tv_today_country);
            tv_today_country.setPaintFlags(tv_today_country.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            tv_total_country = view.findViewById(R.id.tv_total_country);
            tv_total_country.setPaintFlags(tv_total_country.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            expandableLayout = view.findViewById(R.id.expandableLayout);
            country_name.setPaintFlags(country_name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            cardView = view.findViewById(R.id.cardview);
            cardView.setBackgroundResource(R.drawable.shape1);

            country_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CountriesItem countriesItem = countriesItems.get(getAbsoluteAdapterPosition());
                    countriesItem.setExpanded(!countriesItem.isExpanded());
                    notifyItemChanged(getAbsoluteAdapterPosition());

                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(country_name.getWindowToken(), 0);
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
        viewHolder.country_name.setText(country_name);

        DecimalFormat decim = new DecimalFormat("#,###.##");
        String new_cases = decim.format(countriesItems.get(i).getNewConfirmed());
        String new_cases_formatted = "New Cases: " + new_cases;
        viewHolder.new_cases.setText(new_cases_formatted);

        DecimalFormat decim1 = new DecimalFormat("#,###.##");
        String total_cases = decim1.format(countriesItems.get(i).getTotalConfirmed());
        String total_cases_formatted = "Total Cases: " + total_cases;
        viewHolder.total_cases.setText(total_cases_formatted);

        DecimalFormat decim2 = new DecimalFormat("#,###.##");
        String new_deaths = decim2.format(countriesItems.get(i).getNewDeaths());
        String new_deaths_formatted = "New Deaths: " + new_deaths;
        viewHolder.new_deaths.setText(new_deaths_formatted);

        DecimalFormat decim3 = new DecimalFormat("#,###.##");
        String total_deaths = decim3.format(countriesItems.get(i).getTotalDeaths());
        String total_deaths_formatted = "Total Deaths: " + total_deaths;
        viewHolder.total_deaths.setText(total_deaths_formatted);

        DecimalFormat decim4 = new DecimalFormat("#,###.##");
        String new_recovered = decim4.format(countriesItems.get(i).getNewRecovered());
        String new_recovered_formatted = "New Recoveries: " + new_recovered;
        viewHolder.new_recovered.setText(new_recovered_formatted);

        DecimalFormat decim5 = new DecimalFormat("#,###.##");
        String total_recovered = decim5.format(countriesItems.get(i).getTotalRecovered());
        String total_recovered_formatted = "Total Recoveries: " + total_recovered;
        viewHolder.total_recovered.setText(total_recovered_formatted);

        boolean isExpanded = countriesItems.get(i).isExpanded();
        viewHolder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
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
