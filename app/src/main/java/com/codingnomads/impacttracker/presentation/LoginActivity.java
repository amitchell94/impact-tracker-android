package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.graphics.Paint;
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
import com.codingnomads.impacttracker.data.TokenRepository;
import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.LoginService;
import com.codingnomads.impacttracker.logic.LoginTask;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.ref.WeakReference;

import static com.codingnomads.impacttracker.presentation.RestTemplateProvider.createRestTemplate;

public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerText = findViewById(R.id.reg_text);
        registerText.setPaintFlags(registerText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        EditText editText = findViewById(R.id.password);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
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

        loginService = new LoginService(new TokenRepository(createRestTemplate()));

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
    public void register (View view) {
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivity(registerIntent);
    }
}
