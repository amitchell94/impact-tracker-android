package com.codingnomads.impacttracker.logic.commitment;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class GetCommitmentsSyncTask extends AsyncTask<String, Void, List<Commitment>> {
    private CommitmentService commitmentService;
    private List<Commitment> commitments;
    private RecyclerView.Adapter mAdapter;

    public GetCommitmentsSyncTask(CommitmentService commitmentService, List<Commitment> commitments, RecyclerView.Adapter mAdapter) {
        this.commitmentService = commitmentService;
        this.commitments = commitments;
        this.mAdapter = mAdapter;
    }

    @Override
    protected List<Commitment> doInBackground(String... strings) {
        return commitmentService.getCommitments();
    }

    @Override
    protected void onPostExecute(List<Commitment> commitmentsList) {
        super.onPostExecute(commitmentsList);
        commitments.clear();
        commitments.addAll(commitmentsList);
        mAdapter.notifyDataSetChanged();
    }
}


























