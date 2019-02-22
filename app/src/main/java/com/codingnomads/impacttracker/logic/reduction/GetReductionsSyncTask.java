package com.codingnomads.impacttracker.logic.reduction;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetReductionsSyncTask extends AsyncTask<String, Void, ReductionSpinnerData> {
    private ReductionService reductionService;
    private ReductionSpinnerData reductionSpinnerData;
    private ArrayAdapter mAdapter;

    public GetReductionsSyncTask(ReductionService reductionService, ReductionSpinnerData reductionSpinnerData, ArrayAdapter mAdapter) {
        this.reductionService = reductionService;
        this.reductionSpinnerData = reductionSpinnerData;
        this.mAdapter = mAdapter;
    }

    @Override
    protected ReductionSpinnerData doInBackground(String... strings) {
        ReductionSpinnerData reductionSpinnerData = new ReductionSpinnerData();

        List<Reduction> reductionList = reductionService.getReductions();

        reductionSpinnerData.setReductionStrings(new ArrayList<String>());
        reductionSpinnerData.setReductionMap(new HashMap<String, Reduction>());

        for (Reduction reduction: reductionList) {
            reductionSpinnerData.getReductionStrings().add(reduction.getReduction());
            reductionSpinnerData.getReductionMap().put(reduction.getReduction(),reduction);
        }
        return reductionSpinnerData;
    }

    @Override
    protected void onPostExecute(ReductionSpinnerData reductionData) {
        super.onPostExecute(reductionSpinnerData);
        reductionSpinnerData.getReductionMap().clear();
        reductionSpinnerData.getReductionMap().putAll(reductionData.getReductionMap());
        reductionSpinnerData.getReductionStrings().clear();
        reductionSpinnerData.getReductionStrings().addAll(reductionData.getReductionStrings());
        mAdapter.notifyDataSetChanged();
    }
}


























