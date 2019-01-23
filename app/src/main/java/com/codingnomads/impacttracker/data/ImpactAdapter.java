package com.codingnomads.impacttracker.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.logic.impact.Impact;

import java.util.List;

public class ImpactAdapter extends RecyclerView.Adapter<ImpactAdapter.MyViewHolder> {
    private List<Impact> impacts;

    public ImpactAdapter(List<Impact> statisticFromDb) {
        this.impacts = statisticFromDb;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView impact;
        public LinearLayout layout;
        public MyViewHolder(View view) {
            super(view);
            impact = view.findViewById(R.id.impact);
            layout = view.findViewById(R.id.card_layout);
        }
    }

    @Override
    public ImpactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.impact_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.impact.setText(impacts.get(position).getAmount() + impacts.get(position).getUnits());
        holder.layout.setBackgroundResource(impacts.get(position).getBackgroundId());
    }

    @Override
    public int getItemCount() {
        return impacts.size();
    }

}
