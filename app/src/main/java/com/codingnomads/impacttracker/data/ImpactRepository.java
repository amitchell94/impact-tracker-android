package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.impact.Statistic;

import org.springframework.web.client.RestTemplate;

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
