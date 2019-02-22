package com.codingnomads.impacttracker.presentation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.CommitmentRepository;
import com.codingnomads.impacttracker.data.ReductionRepository;
import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.reduction.ReductionService;
import com.codingnomads.impacttracker.logic.commitment.SaveCommitmentTask;
import com.codingnomads.impacttracker.logic.reduction.GetReductionsSyncTask;
import com.codingnomads.impacttracker.logic.reduction.Reduction;
import com.codingnomads.impacttracker.logic.reduction.ReductionSpinnerData;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AddCommitmentsActivity extends AppCompatActivity {
    CommitmentService commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));
    ReductionService reductionService = new ReductionService(new ReductionRepository(createRestTemplate()));

    Commitment commitment = new Commitment();
    ReductionSpinnerData reductionSpinnerData = new ReductionSpinnerData();

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
            }
        });

        final Calendar myCalendar = Calendar.getInstance();
        final EditText startDate = findViewById(R.id.startDate_input);
        startDate.setKeyListener(null);

        final DatePickerDialog.OnDateSetListener startDatePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(startDate, myCalendar);
            }

        };

        startDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddCommitmentsActivity.this, startDatePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final EditText endDate = findViewById(R.id.endDate_input);

        Switch toggle = (Switch) findViewById(R.id.ongoingToggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    endDate.setText("");
                    endDate.setVisibility(View.INVISIBLE);
                } else {
                    endDate.setVisibility(View.VISIBLE);
                }
            }
        });


        endDate.setKeyListener(null);

        final DatePickerDialog.OnDateSetListener endDatePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(endDate, myCalendar);
            }

        };

        endDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddCommitmentsActivity.this, endDatePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Spinner reductionInput = (Spinner) findViewById(R.id.reductionId_input);

        reductionSpinnerData.setReductionIdMap(new HashMap<String, Integer>());
        reductionSpinnerData.setReductionStrings(new ArrayList<String>());

        final ArrayAdapter<String> reductionAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, reductionSpinnerData.getReductionStrings());
        reductionInput.setAdapter(reductionAdapter);

        new GetReductionsSyncTask(reductionService, reductionSpinnerData, reductionAdapter).execute();
    }

    public void addNewCommitment(View view) {
        Commitment commitment = new Commitment();

        commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));

        SaveCommitmentTask saveCommitmentTask = new SaveCommitmentTask(commitmentService);

        saveCommitmentTask.execute(commitment);

        startActivity(new Intent(AddCommitmentsActivity.this, CommitmentsActivity.class));
    }

    @NonNull
    private Commitment getCommitmentInputs() {
        Commitment commitment = new Commitment();

        Spinner reduction = findViewById(R.id.reductionId_input);
        EditText startDate = findViewById(R.id.startDate_input);
        EditText endDate = findViewById(R.id.endDate_input);

        commitment.setReductionId(reductionSpinnerData.getReductionIdMap().get(reduction.getSelectedItem()));
        commitment.setStartDate(startDate.getText().toString());
        commitment.setEndDate(endDate.getText().toString());

        return commitment;
    }

    @NonNull
    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    private void updateLabel(EditText editText, Calendar myCalendar) {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }
}


























