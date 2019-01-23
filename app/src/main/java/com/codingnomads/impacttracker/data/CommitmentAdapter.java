package com.codingnomads.impacttracker.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.logic.commitment.Commitment;

import java.util.List;

public class CommitmentAdapter extends RecyclerView.Adapter<CommitmentAdapter.MyViewHolder> {
    private List<Commitment> commitmentsList;

    public CommitmentAdapter() {
    }

    public CommitmentAdapter(List<Commitment> commitmentsList) {
        this.commitmentsList = commitmentsList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textviewName;
        public TextView textViewStartDate, textviewEndDate;

        public MyViewHolder(View v) {
            super(v);
            textviewName = v.findViewById(R.id.commitment_name_textview);
            textViewStartDate = v.findViewById(R.id.commitment_startDate_textview);
            textviewEndDate = v.findViewById(R.id.commitment_endDate_textview);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.commitment_view, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Commitment commitment = commitmentsList.get(position);

        myViewHolder.textviewName.setText(commitment.getId().toString());

        myViewHolder.textViewStartDate.setText(commitment.getStartDate());

        myViewHolder.textviewEndDate.setText(commitment.getEndDate());

    }

    @Override
    public int getItemCount() {
        return commitmentsList.size();
    }


}





































