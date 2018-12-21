package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.LoginService;
import com.codingnomads.impacttracker.logic.LoginTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Credentials credentials = getLoginCredentials();

        loginService = new LoginService(new ImpactRepository(createRestTemplate()));

        LoginTask saveDiveTask = new LoginTask(loginService);

        saveDiveTask.execute(credentials);

        startActivity(new Intent(LoginActivity.this,ImpactActivity.class));
    }

    @NonNull
    private Credentials getLoginCredentials() {
        Credentials credentials = new Credentials();

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        credentials.setUsername(username.getText().toString());
        credentials.setPassword(password.getText().toString());
        return credentials;
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}
