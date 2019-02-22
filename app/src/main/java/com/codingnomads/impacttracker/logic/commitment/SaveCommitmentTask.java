package com.codingnomads.impacttracker.logic.commitment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.codingnomads.impacttracker.presentation.CommitmentsActivity;

import java.lang.ref.WeakReference;

public class SaveCommitmentTask extends AsyncTask<Commitment, Void, Commitment> {

    private CommitmentService commitmentService;
    private WeakReference<AppCompatActivity> weakReference;

    public SaveCommitmentTask(CommitmentService commitmentService, WeakReference<AppCompatActivity> weakReference) {
        this.commitmentService = commitmentService;
        this.weakReference = weakReference;
    }

    @Override
    protected Commitment doInBackground(Commitment[] commitments) {
        return commitmentService.saveCommitment(commitments[0]);
    }

    protected void onPostExecute(Commitment commitment){
        Activity activity = weakReference.get();
        activity.startActivity(new Intent(activity,CommitmentsActivity.class));
    }
}






















