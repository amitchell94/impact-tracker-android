package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentWithReduction;
import com.codingnomads.impacttracker.logic.reduction.Reduction;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.codingnomads.impacttracker.data.TokenRepository.getStoredToken;
import static com.codingnomads.impacttracker.data.UriMaker.getUri;

public class ReductionRepository {

    RestTemplate restTemplate = new RestTemplate();

    public ReductionRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Reduction> getReductions() {
        Reduction[] allReductions = restTemplate.getForObject(getUri("/api/reductions/", getStoredToken()), Reduction[].class);
        return Arrays.asList(allReductions);
    }

}
