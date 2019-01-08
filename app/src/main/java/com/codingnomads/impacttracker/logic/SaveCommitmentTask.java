package com.codingnomads.impacttracker.logic;

import android.os.AsyncTask;

public class SaveCommitmentTask extends AsyncTask<Commitment, Void, Commitment> {

    private CommitmentService commitmentService;

    public SaveCommitmentTask(CommitmentService commitmentService){
        this.commitmentService = commitmentService;
    }

    @Override
    protected Commitment doInBackground(Commitment[] commitments) {
        return commitmentService.saveCommitment(commitments[0]);
    }
}






















