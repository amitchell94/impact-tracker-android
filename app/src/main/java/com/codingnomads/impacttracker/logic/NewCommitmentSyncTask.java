package com.codingnomads.impacttracker.logic;

import android.os.AsyncTask;

public class NewCommitmentSyncTask extends AsyncTask<Commitment, Void, Commitment> {
    private CommitmentService commitmentService;

    public NewCommitmentSyncTask(CommitmentService commitmentService) {
        this.commitmentService = commitmentService;
    }

    @Override
    protected Commitment doInBackground(Commitment[] commitments) {
        return commitmentService.saveCommitment(commitments[0]);
    }

}
