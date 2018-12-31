package com.codingnomads.impacttracker.data;

import android.content.SharedPreferences;

import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.Statistic;
import com.codingnomads.impacttracker.logic.Token;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ImpactRepository {


    private RestTemplate restTemplate;

    private static String storedToken;
    public ImpactRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Statistic getImpact() {
        Statistic impact = restTemplate.getForObject(getUriWithToken("api/impact/total/",storedToken), Statistic.class);
        return impact;
    }

    public Boolean getToken(Credentials credentials) {
        try {
            Token token = restTemplate.postForObject(getUri("/api/authenticate"), credentials, Token.class);
            storedToken = token.getValue();
            if (token.getValue() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private URI getUri(String path) {
        return UriComponentsBuilder.fromUriString("http:///18.220.98.185/")
                .path(path)
                .build()
                .encode()
                .toUri();
    }

    private URI getUriWithToken(String path, String value) {
        return UriComponentsBuilder.fromUriString("http://18.220.98.185/")
                .path(path)
                .queryParam("token",value)
                .build()
                .encode()
                .toUri();
    }
}
