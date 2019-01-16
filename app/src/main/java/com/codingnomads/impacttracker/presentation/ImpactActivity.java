package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.ImpactAdapter;
import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.data.TokenRepository;
import com.codingnomads.impacttracker.logic.GetImpactTask;
import com.codingnomads.impacttracker.logic.Impact;
import com.codingnomads.impacttracker.logic.ImpactService;
import com.codingnomads.impacttracker.logic.Token;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.codingnomads.impacttracker.data.TokenRepository.setStoredToken;

public class ImpactActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Impact> impacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        mRecyclerView = findViewById(R.id.impactRecycler);
        mAdapter = new ImpactAdapter(impacts);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ImpactService impactService = new ImpactService(new ImpactRepository(createRestTemplate()));

        mRecyclerView.setAdapter(mAdapter);

        GetImpactTask getImpactTask = new GetImpactTask(impacts, impactService, mAdapter);

        getImpactTask.execute();
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                View menuItemView = findViewById(R.id.action_settings); // SAME ID AS MENU ID
                PopupMenu popupMenu = new PopupMenu(this, menuItemView);
                MenuInflater inflater = popupMenu.getMenuInflater();
                popupMenu.setOnMenuItemClickListener(this);
                inflater.inflate(R.menu.impact_actions, popupMenu.getMenu());
                popupMenu.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.commitments:
                Intent commitmentsIntent = new Intent(this, CommitmentsActivity.class);
                startActivity(commitmentsIntent);
                return true;
            case R.id.logout:
                setStoredToken("");
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                return true;
            default:
                return false;
        }
    }
}
