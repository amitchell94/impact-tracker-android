package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.CommitmentAdapter;
import com.codingnomads.impacttracker.data.CommitmentRepository;
import com.codingnomads.impacttracker.logic.Commitment;
import com.codingnomads.impacttracker.logic.CommitmentService;
import com.codingnomads.impacttracker.logic.GetCommitmentsSyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class CommitmentsActivity extends AppCompatActivity {

    CommitmentService commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));

    private List<Commitment> commitmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commitments);
        setSupportActionBar((Toolbar) findViewById(R.id.home_toolbar));

        RecyclerView commitmentListView = findViewById(R.id.commitment_recyclerview);
        commitmentListView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        commitmentListView.setLayoutManager(linearLayoutManager);

        final CommitmentAdapter commitmentAdapter = new CommitmentAdapter(commitmentList);
        commitmentListView.setAdapter(commitmentAdapter);

        new GetCommitmentsSyncTask(commitmentService, commitmentList, commitmentAdapter).execute();
    }

    @NonNull
    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    public void addCommitment(View view) {
        startActivity(new Intent(CommitmentsActivity.this, AddCommitmentsActivity.class));
    }
}


















