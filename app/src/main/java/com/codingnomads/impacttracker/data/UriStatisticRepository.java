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

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMCJ9.J9abfsB_AU9sdP8tuyJuk6WEAXtH4Kf2hTWtxsE9ags";

    public UriStatisticRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Statistic> getImpact() {
        Statistic impact = restTemplate.getForObject(getUri("api/impact/total/",token), Statistic.class);
        return Arrays.asList(impact);
    }

    private URI getUri(String path, String value) {
        return UriComponentsBuilder.fromUriString("http://192.168.1.71")
                .port("8080")
                .path(path)
                .queryParam("token",value)
                .build()
                .encode()
                .toUri();
    }
}
