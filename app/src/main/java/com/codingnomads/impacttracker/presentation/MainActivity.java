package com.codingnomads.impacttracker.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.StatisticAdapter;
import com.codingnomads.impacttracker.data.UriStatisticRepository;
import com.codingnomads.impacttracker.logic.GetStatisticTask;
import com.codingnomads.impacttracker.logic.Impact;
import com.codingnomads.impacttracker.logic.Statistic;
import com.codingnomads.impacttracker.logic.StatisticService;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Impact> impacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.impactRecycler);
        mAdapter = new StatisticAdapter(impacts);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        StatisticService statisticService = new StatisticService(new UriStatisticRepository(createRestTemplate()));

        mRecyclerView.setAdapter(mAdapter);

        GetStatisticTask getStatisticTask = new GetStatisticTask(impacts,statisticService,mAdapter);

        getStatisticTask.execute();
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}
