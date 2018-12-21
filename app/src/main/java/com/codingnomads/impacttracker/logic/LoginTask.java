package com.codingnomads.impacttracker.logic;

import android.os.AsyncTask;

public class LoginTask extends AsyncTask<Credentials,Void,String> {

    private LoginService loginService;

    public LoginTask(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected String doInBackground(Credentials... credentials) {
        return loginService.getToken(credentials[0]);
    }
}
