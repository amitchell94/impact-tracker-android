package com.codingnomads.impacttracker.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.data.RegisterRepository;
import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.LoginService;
import com.codingnomads.impacttracker.logic.LoginTask;
import com.codingnomads.impacttracker.logic.RegisterService;
import com.codingnomads.impacttracker.logic.RegisterTask;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.ref.WeakReference;

import static com.codingnomads.impacttracker.presentation.RestTemplateProvider.createRestTemplate;

public class RegisterActivity extends AppCompatActivity {
    private RegisterService registerService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginText = findViewById(R.id.reg_login);
        loginText.setPaintFlags(loginText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        EditText editText = findViewById(R.id.reg_password);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    findViewById(R.id.reg_signup_button).performClick();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void registerUser(View view) {

        TextView errorText = findViewById(R.id.reg_error_text);
        TextView successText = findViewById(R.id.reg_sucess_text);

        errorText.setText("");
        successText.setText("");
        successText.setPaintFlags(successText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        Credentials credentials = getRegisterCredentials();

        registerService = new RegisterService(new RegisterRepository(createRestTemplate()));

        RegisterTask registerTask = new RegisterTask(registerService,new WeakReference<AppCompatActivity>(RegisterActivity.this));

        registerTask.execute(credentials);
    }

    public void login (View view) {
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
    }

    @NonNull
    private Credentials getRegisterCredentials() {
        Credentials credentials = new Credentials();

        EditText username = findViewById(R.id.reg_username);
        EditText password = findViewById(R.id.reg_password);

        credentials.setUsername(username.getText().toString());
        credentials.setPassword(password.getText().toString());
        return credentials;
    }
}
