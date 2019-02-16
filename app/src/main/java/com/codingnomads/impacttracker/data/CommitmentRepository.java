package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentPresentation;
import com.codingnomads.impacttracker.logic.commitment.CommitmentWithReduction;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.codingnomads.impacttracker.data.TokenRepository.getStoredToken;
import static com.codingnomads.impacttracker.data.UriMaker.getUri;

public class CommitmentRepository {

    RestTemplate restTemplate = new RestTemplate();

    public CommitmentRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CommitmentWithReduction> getCommitments() {
        CommitmentWithReduction[] allCommitments = restTemplate.getForObject(getUri("/api/commitments/", getStoredToken()), CommitmentWithReduction[].class);
        return Arrays.asList(allCommitments);
    }

    public Commitment saveCommitment(Commitment commitment){
        Commitment newCommitment = restTemplate.postForObject(getUri("/api/commitments/addcommitment", getStoredToken()), commitment, Commitment.class);
        return newCommitment;
    }

}
