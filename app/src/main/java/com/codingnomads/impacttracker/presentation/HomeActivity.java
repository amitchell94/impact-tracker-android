package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.codingnomads.impacttracker.R;

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar((Toolbar) findViewById(R.id.home_toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                View menuItemView = findViewById(R.id.action_settings); // SAME ID AS MENU ID
                PopupMenu popupMenu = new PopupMenu(this,menuItemView);
                MenuInflater inflater = popupMenu.getMenuInflater();
                popupMenu.setOnMenuItemClickListener(this);
                inflater.inflate(R.menu.home_actions, popupMenu.getMenu());
                popupMenu.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent loginintent = new Intent(this,LoginActivity.class);
                startActivity(loginintent);
                return true;
            case R.id.register:
                Intent registerIntent = new Intent(this,RegisterActivity.class);
                startActivity(registerIntent);
                return true;
            default:
                return false;
        }
    }
    public void signUp (View view) {
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivity(registerIntent);
    }
}
