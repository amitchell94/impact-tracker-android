package com.codingnomads.impacttracker.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codingnomads.impacttracker.R;

public class CommitmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commitments);
        setSupportActionBar((Toolbar) findViewById(R.id.home_toolbar));
    }

}
