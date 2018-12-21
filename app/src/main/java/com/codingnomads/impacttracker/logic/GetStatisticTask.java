package com.codingnomads.impacttracker.logic;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import static com.codingnomads.impacttracker.logic.ImpactUtils.transformStatisticToImpactList;

public class GetStatisticTask extends AsyncTask<String, Void, List<Impact>> {

    private List<Impact> impacts;
    private StatisticService statisticService;
    private RecyclerView.Adapter mAdapter;

    public GetStatisticTask(List<Impact> impacts, StatisticService statisticService, RecyclerView.Adapter mAdapter) {
        this.impacts = impacts;
        this.statisticService = statisticService;
        this.mAdapter = mAdapter;
    }

    @Override
    protected List<Impact> doInBackground(String... strings) {
        return statisticService.getImpact();
    }

    @Override
    protected void onPostExecute(List<Impact> impactList) {
        super.onPostExecute(impactList);
        impacts.clear();
        impacts.addAll(impactList);
        mAdapter.notifyDataSetChanged();
    }
}
