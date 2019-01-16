package com.codingnomads.impacttracker.logic.register;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.lang.ref.WeakReference;

public class RegisterTask extends AsyncTask<Credentials,Void,Boolean> {
    private RegisterService registerService;
    private WeakReference<AppCompatActivity> weakReference;

    public RegisterTask(RegisterService registerService, WeakReference<AppCompatActivity> weakReference) {
        this.registerService = registerService;
        this.weakReference = weakReference;
    }

    @Override
    protected Boolean doInBackground(Credentials... credentials) {
        String username = credentials[0].getUsername();
        String password = credentials[0].getPassword();
        final Activity activity = weakReference.get();
        TextView errorText = activity.findViewById(R.id.reg_error_text);
        TextView successText = activity.findViewById(R.id.reg_sucess_text);

        if (username == null || "".equals(username)) {
            errorText.setText("Invalid username");
            return false;
        }

        if (password == null || "".equals(password) ||
                password.length() < 7) {
            errorText.setText("Password must be at least 7 characters");
            return false;
        }
        try {
            if (registerService.registerUser(credentials[0])) {
                successText.setText("Account created! Click here to sign in");
            }
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            errorText.setText("Unable to connect. Try again later");
        } catch (RestClientException e) {
            e.printStackTrace();
            errorText.setText("Username already exists");
        }
        return false;
    }
}
