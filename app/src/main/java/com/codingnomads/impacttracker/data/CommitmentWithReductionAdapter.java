package com.codingnomads.impacttracker.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.logic.commitment.CommitmentPresentation;
import com.codingnomads.impacttracker.logic.commitment.CommitmentWithReduction;

import java.util.List;

public class CommitmentWithReductionAdapter extends RecyclerView.Adapter<CommitmentWithReductionAdapter.MyViewHolder> {
    private List<CommitmentWithReduction> commitmentsList;

    public CommitmentWithReductionAdapter() {
    }

    public CommitmentWithReductionAdapter(List<CommitmentWithReduction> commitmentsList) {
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
        CommitmentWithReduction commitment = commitmentsList.get(position);

        String commitmentString = capitaliseFirstLetter(commitment.getReduction());

        if (!"days".equals(commitment.getReductionUnit())) {
            commitmentString += " by " + commitment.getAmountToReduceBy() +
                    " " + commitment.getReductionUnit() + " per day";
        }

        myViewHolder.textviewName.setText(commitmentString);

        myViewHolder.textViewStartDate.setText(commitment.getStartDate());

        if ((commitment.getEndDate() == null)) {
            myViewHolder.textviewEndDate.setText("Ongoing");
        } else {
            myViewHolder.textviewEndDate.setText(commitment.getEndDate());
        }

    }

    @Override
    public int getItemCount() {
        return commitmentsList.size();
    }

    private static String capitaliseFirstLetter(String input) {
        if (input == null || input.length() < 1) {
            return input;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}





































