package com.codingnomads.impacttracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.ImpactAdapter;
import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.logic.GetImpactTask;
import com.codingnomads.impacttracker.logic.Impact;
import com.codingnomads.impacttracker.logic.ImpactService;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ImpactActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Impact> impacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.impactRecycler);
        mAdapter = new ImpactAdapter(impacts);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ImpactService impactService = new ImpactService(new ImpactRepository(createRestTemplate()));

        mRecyclerView.setAdapter(mAdapter);

        GetImpactTask getStatisticTask = new GetImpactTask(impacts, impactService,mAdapter);

        getStatisticTask.execute();
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}
