package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.CommitmentRepository;
import com.codingnomads.impacttracker.logic.Commitment;
import com.codingnomads.impacttracker.logic.CommitmentService;
import com.codingnomads.impacttracker.logic.SaveCommitmentTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AddCommitmentsActivity extends AppCompatActivity {
    CommitmentService commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));
    Commitment commitment = new Commitment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commitment);

        Button createNewCommitmentButton = findViewById(R.id.add_commitment_button_input);
        createNewCommitmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitment = getCommitmentInputs();
                new SaveCommitmentTask(commitmentService).execute(commitment);
            }});

    }

        public void addNewCommitment (View view){
            Commitment commitment = new Commitment();

            commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));

            SaveCommitmentTask saveCommitmentTask = new SaveCommitmentTask(commitmentService);

            saveCommitmentTask.execute(commitment);

            startActivity(new Intent(AddCommitmentsActivity.this, CommitmentsActivity.class));
        }

        @NonNull
        private Commitment getCommitmentInputs () {
            Commitment commitment = new Commitment();

            EditText reductionId = findViewById(R.id.reductionId_input);
            EditText startDate = findViewById(R.id.startDate_input);
            EditText endDate = findViewById(R.id.endDate_input);

            commitment.setReductionId(Integer.parseInt(reductionId.getText().toString()));
            commitment.setStartDate(startDate.getText().toString());
            commitment.setEndDate(endDate.getText().toString());

            return commitment;

        }

        @NonNull
        private RestTemplate createRestTemplate () {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            return restTemplate;
        }
    }


























