package com.codingnomads.impacttracker.logic;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codingnomads.impacttracker.R;
import com.codingnomads.impacttracker.presentation.ImpactActivity;

import java.lang.ref.WeakReference;

public class LoginTask extends AsyncTask<Credentials,Void,Boolean> {

    private LoginService loginService;
    private WeakReference<AppCompatActivity> weakReference;

    public LoginTask(LoginService loginService, WeakReference<AppCompatActivity> weakReference) {
        this.loginService = loginService;
        this.weakReference = weakReference;
    }

    @Override
    protected Boolean doInBackground(Credentials... credentials) {
        return loginService.getToken(credentials[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Activity activity = weakReference.get();
        if(!aBoolean) {

            if (activity != null) {
                TextView errorText = activity.findViewById(R.id.error_text);
                errorText.setText("Invalid login credentials");
            }
        } else {
            activity.startActivity(new Intent(activity,ImpactActivity.class));
        }
    }
}
