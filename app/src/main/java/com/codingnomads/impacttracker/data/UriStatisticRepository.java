package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.Statistic;
import com.codingnomads.impacttracker.logic.StatisticRepository;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class UriStatisticRepository implements StatisticRepository {


    private RestTemplate restTemplate;

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4In0.I-dAPedxck0H85H2nSlmPgYL6v1AHlxLCVZBJRv4DEU";

    public UriStatisticRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Statistic getImpact() {
        Statistic impact = restTemplate.getForObject(getUri("api/impact/total/",token), Statistic.class);
        return impact;
    }

    private URI getUri(String path, String value) {
        return UriComponentsBuilder.fromUriString("http://18.220.98.185")
                .path(path)
                .queryParam("token",value)
                .build()
                .encode()
                .toUri();
    }
}
