package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.register.Credentials;
import com.codingnomads.impacttracker.logic.register.User;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.codingnomads.impacttracker.data.UriMaker.getUri;

public class RegisterRepository {

    private RestTemplate restTemplate;

    public RegisterRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean registerUser(Credentials credentials) throws RestClientException {
        User user = restTemplate.postForObject(getUri("/api/register"), credentials, User.class);
        if (user.getUsername() != null && user.getPassword() != null) {
            return true;
        } else {
            return false;
        }
    }
}
