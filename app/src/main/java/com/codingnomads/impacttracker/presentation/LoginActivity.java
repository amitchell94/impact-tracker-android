package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.LoginService;
import com.codingnomads.impacttracker.logic.LoginTask;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editText = findViewById(R.id.password);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // TODO do something
                    findViewById(R.id.loginbutton).performClick();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void login(View view) {

        TextView errorText = findViewById(R.id.error_text);
        errorText.setText("");

        Credentials credentials = getLoginCredentials();

        loginService = new LoginService(new ImpactRepository(createRestTemplate()));

        LoginTask loginTask = new LoginTask(loginService,new WeakReference<AppCompatActivity>(LoginActivity.this));

        loginTask.execute(credentials);
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

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}
