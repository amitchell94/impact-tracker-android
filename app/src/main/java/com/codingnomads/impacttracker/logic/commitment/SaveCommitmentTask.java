package com.codingnomads.impacttracker.logic.commitment;

import android.os.AsyncTask;

import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;

public class SaveCommitmentTask extends AsyncTask<Commitment, Void, Commitment> {

    private CommitmentService commitmentService;


    public SaveCommitmentTask(CommitmentService commitmentService){
        this.commitmentService = commitmentService;
    }

    @Override
    protected Commitment doInBackground(Commitment[] commitments) {
        return commitmentService.saveCommitment(commitments[0]);
    }

    protected void onProgressUpdate(Integer... progress){}

    protected void onPostExecute(Commitment commitment){
    }
}






















