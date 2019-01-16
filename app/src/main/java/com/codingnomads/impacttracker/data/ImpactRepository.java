package com.codingnomads.impacttracker.data;

import android.content.SharedPreferences;

import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.Statistic;
import com.codingnomads.impacttracker.logic.Token;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.codingnomads.impacttracker.data.TokenRepository.getStoredToken;
import static com.codingnomads.impacttracker.data.UriMaker.getUri;

public class ImpactRepository {


    private RestTemplate restTemplate;

    private static String storedToken;
    public ImpactRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Statistic getImpact() {
        Statistic impact = restTemplate.getForObject(getUri("api/impact/total/", getStoredToken()), Statistic.class);
        return impact;
    }

}
