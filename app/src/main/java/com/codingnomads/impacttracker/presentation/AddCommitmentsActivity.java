package com.codingnomads.impacttracker.presentation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

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

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class AddCommitmentsActivity extends AppCompatActivity {
    CommitmentService commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));
    ReductionService reductionService = new ReductionService(new ReductionRepository(createRestTemplate()));

    Commitment commitment = new Commitment();
    ReductionSpinnerData reductionSpinnerData = new ReductionSpinnerData();
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commitment);

        Button createNewCommitmentButton = findViewById(R.id.add_commitment_button_input);
        createNewCommitmentButton.setOnClickListener(createButtonOnClickListener());

        final EditText startDate = findViewById(R.id.startDate_input);
        startDate.setKeyListener(null);

        final DatePickerDialog.OnDateSetListener startDatePicker = createDateListener(startDate);
        startDate.setOnClickListener(createDateOnClickListener(startDatePicker));

        final EditText endDate = findViewById(R.id.endDate_input);
        endDate.setKeyListener(null);

        final DatePickerDialog.OnDateSetListener endDatePicker = createDateListener(endDate);
        endDate.setOnClickListener(createDateOnClickListener(endDatePicker));

        LinearLayout endDateLayout = findViewById(R.id.endDateLayout);
        endDateLayout.setVisibility(View.GONE);
        Switch toggle = findViewById(R.id.ongoingToggle);
        toggle.setOnCheckedChangeListener(createSwitchListener(endDate, endDateLayout));

        Spinner reductionInput = findViewById(R.id.reductionId_input);
        final LinearLayout amountSelection = findViewById(R.id.amountSelection);
        final TextView amountUnits = findViewById(R.id.amountUnits);
        final AdapterView.OnItemSelectedListener itemSelectedListener = createItemSelectedListener(amountSelection, amountUnits);
        reductionInput.setOnItemSelectedListener(itemSelectedListener);

        reductionSpinnerData.setReductionMap(new HashMap<String, Reduction>());
        reductionSpinnerData.setReductionStrings(new ArrayList<String>());

        final ArrayAdapter<String> reductionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, reductionSpinnerData.getReductionStrings());
        reductionInput.setAdapter(reductionAdapter);

        new GetReductionsSyncTask(reductionService, reductionSpinnerData, reductionAdapter).execute();
    }

    public void addNewCommitment(View view) {
        Commitment commitment = new Commitment();

        commitmentService = new CommitmentService(new CommitmentRepository(createRestTemplate()));

        SaveCommitmentTask saveCommitmentTask = new SaveCommitmentTask(commitmentService, new WeakReference<AppCompatActivity>(AddCommitmentsActivity.this));

        saveCommitmentTask.execute(commitment);
    }

    @NonNull
    private Commitment getCommitmentInputs() {
        Commitment commitment = new Commitment();

        Spinner reduction = findViewById(R.id.reductionId_input);
        EditText startDate = findViewById(R.id.startDate_input);
        EditText endDate = findViewById(R.id.endDate_input);
        LinearLayout amountSelection = findViewById(R.id.amountSelection);
        EditText amountInput = findViewById(R.id.amountInput);

        commitment.setReductionId(reductionSpinnerData.getReductionMap().get(reduction.getSelectedItem()).getId());
        commitment.setStartDate(startDate.getText().toString());
        commitment.setEndDate(endDate.getText().toString());
        if (amountSelection.getVisibility() != View.GONE) {
            commitment.setAmountToReduceBy(Integer.parseInt(amountInput.getText().toString()));
        }

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

    private DatePickerDialog.OnDateSetListener createDateListener(final EditText editText) {
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(editText, myCalendar);
            }

        };
        return dateSetListener;
    }

    private View.OnClickListener createButtonOnClickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitment = getCommitmentInputs();
                new SaveCommitmentTask(commitmentService,
                        new WeakReference<AppCompatActivity>(AddCommitmentsActivity.this)).execute(commitment);
            }
        };
        return onClickListener;
    }

    private View.OnClickListener createDateOnClickListener(final DatePickerDialog.OnDateSetListener onDateSetListener) {
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddCommitmentsActivity.this, onDateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        };
        return onClickListener;
    }

    private CompoundButton.OnCheckedChangeListener createSwitchListener(final EditText endDate, final LinearLayout endDateLayout) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener =
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            endDate.setText("");
                            endDateLayout.setVisibility(View.GONE);
                        } else {
                            endDateLayout.setVisibility(View.VISIBLE);
                        }
                    }
                };
        return onCheckedChangeListener;
    }

    private AdapterView.OnItemSelectedListener createItemSelectedListener(final LinearLayout amountSelection, final TextView amountUnits) {
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(25);
                ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                String selectedReductionString = (String) parent.getItemAtPosition(position);
                Reduction selectedReduction = reductionSpinnerData.getReductionMap().get(selectedReductionString);
                if (selectedReduction.getAveragePerDay() != 1) {
                    amountSelection.setVisibility(View.VISIBLE);
                    amountUnits.setText(selectedReduction.getUnit() + " per day");
                } else {
                    amountSelection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        return itemSelectedListener;
    }
}


























