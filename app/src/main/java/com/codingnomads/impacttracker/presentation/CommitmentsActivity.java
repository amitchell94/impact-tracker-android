package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.CommitmentWithReductionAdapter;
import com.codingnomads.impacttracker.data.CommitmentRepository;
import com.codingnomads.impacttracker.logic.commitment.CommitmentPresentation;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.commitment.CommitmentWithReduction;
import com.codingnomads.impacttracker.logic.commitment.GetCommitmentsSyncTask;

import java.util.ArrayList;
import java.util.List;

import static com.codingnomads.impacttracker.presentation.RestTemplateProvider.createRestTemplate;

public class CommitmentsActivity extends AppCompatActivity {

    CommitmentService commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));

    private List<CommitmentWithReduction> commitmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commitments);
        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        RecyclerView commitmentListView = findViewById(R.id.commitment_recyclerview);
        commitmentListView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commitmentListView.setLayoutManager(linearLayoutManager);

        final CommitmentWithReductionAdapter commitmentAdapter = new CommitmentWithReductionAdapter(commitmentList);
        commitmentListView.setAdapter(commitmentAdapter);

        new GetCommitmentsSyncTask(commitmentService, commitmentList, commitmentAdapter).execute();
    }

    public void addCommitment(View view) {
        startActivity(new Intent(CommitmentsActivity.this, AddCommitmentsActivity.class));
    }
}


















