package com.codingnomads.impacttracker.logic;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class GetImpactTask extends AsyncTask<String, Void, List<Impact>> {

    private List<Impact> impacts;
    private ImpactService impactService;
    private RecyclerView.Adapter mAdapter;

    public GetImpactTask(List<Impact> impacts, ImpactService impactService, RecyclerView.Adapter mAdapter) {
        this.impacts = impacts;
        this.impactService = impactService;
        this.mAdapter = mAdapter;
    }

    @Override
    protected List<Impact> doInBackground(String... strings) {
        return impactService.getImpact();
    }

    @Override
    protected void onPostExecute(List<Impact> impactList) {
        super.onPostExecute(impactList);
        impacts.clear();
        impacts.addAll(impactList);
        mAdapter.notifyDataSetChanged();
    }
}
