package com.codingnomads.impacttracker.logic.commitment;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class GetCommitmentsSyncTask extends AsyncTask<String, Void, List<CommitmentWithReduction>> {
    private CommitmentService commitmentService;
    private List<CommitmentWithReduction> commitments;
    private RecyclerView.Adapter mAdapter;

    public GetCommitmentsSyncTask(CommitmentService commitmentService, List<CommitmentWithReduction> commitments, RecyclerView.Adapter mAdapter) {
        this.commitmentService = commitmentService;
        this.commitments = commitments;
        this.mAdapter = mAdapter;
    }

    @Override
    protected List<CommitmentWithReduction> doInBackground(String... strings) {
        return commitmentService.getCommitments();
    }

    @Override
    protected void onPostExecute(List<CommitmentWithReduction> commitmentsList) {
        super.onPostExecute(commitmentsList);
        commitments.clear();
        commitments.addAll(commitmentsList);
        mAdapter.notifyDataSetChanged();
    }
}


























