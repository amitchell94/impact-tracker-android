package com.codingnomads.impacttracker.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.logic.Statistic;

import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.MyViewHolder> {
    private List<Statistic> statisticFromDb;

    public StatisticAdapter(List<Statistic> statisticFromDb) {
        this.statisticFromDb = statisticFromDb;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView gallons, co2;
        public MyViewHolder(View view) {
            super(view);
            gallons = view.findViewById(R.id.gallons);
            co2 = view.findViewById(R.id.co2);
        }
    }

    @Override
    public StatisticAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.impact_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        if (statisticFromDb.size() > 0) {
            holder.gallons.setText(Long.toString(statisticFromDb.get(0).getGallonsOfWater()) + " gallons of water");
            holder.co2.setText(Double.toString(statisticFromDb.get(0).getTonsOfCo2()) + " tons of co2");
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
