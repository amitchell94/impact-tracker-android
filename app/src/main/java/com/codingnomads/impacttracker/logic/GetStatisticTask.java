package com.codingnomads.impacttracker.logic;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class GetStatisticTask extends AsyncTask<String, Void, List<Statistic>> {

    private List<Statistic> statistic;
    private StatisticService statisticService;
    private RecyclerView.Adapter mAdapter;

    public GetStatisticTask(List<Statistic> statistic, StatisticService statisticService, RecyclerView.Adapter mAdapter) {
        this.statistic = statistic;
        this.statisticService = statisticService;
        this.mAdapter = mAdapter;
    }

    @Override
    protected List<Statistic> doInBackground(String... strings) {
        return statisticService.getImpact();
    }

    @Override
    protected void onPostExecute(List<Statistic> statistics) {
        super.onPostExecute(statistic);
        statistic.clear();
        statistic.addAll(statistics);
        mAdapter.notifyDataSetChanged();
    }
}
