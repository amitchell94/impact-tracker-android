package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.TokenRepository;
import com.codingnomads.impacttracker.logic.register.Credentials;
import com.codingnomads.impacttracker.logic.login.LoginService;
import com.codingnomads.impacttracker.logic.login.LoginTask;

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

        Button loginButton = findViewById(R.id.loginbutton);
        loginButton.setEnabled(false);
        loginButton.setText("Logging in...");

        Credentials credentials = getLoginCredentials();

        loginService = new LoginService(new TokenRepository(createRestTemplate()));

        LoginTask loginTask = new LoginTask(loginService,new WeakReference<AppCompatActivity>(LoginActivity.this));

        loginTask.execute(credentials);

        //TODO: Need to prevent login button being pressed twice.
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
