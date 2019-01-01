package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.Commitment;
import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.Token;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class CommitmentRepository {

    //create instance of restTemplate
    RestTemplate restTemplate = new RestTemplate();
    private static String token;

    //use constructor for this class
    public CommitmentRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //method: get commitments
    public List<Commitment> getCommitments() {
        Commitment[] allCommitments = restTemplate.getForObject(getUriWithToken("/api/commitments/", token), Commitment[].class);
        return Arrays.asList(allCommitments);
    }

    //method: add commitment
    public Commitment saveCommitment(Commitment commitment){
        Commitment newCommitment = restTemplate.postForObject(getUriWithToken("/api/commitments/addcommitment", token), commitment, Commitment.class);
        return newCommitment;
    }
    //method: update commitment
    //method: delete commitment

    //method to get token
    public Boolean getToken(Credentials credentials) {
        try {
            Token getTokenAttempt = restTemplate.postForObject(getUri("/api/authenticate"), credentials, Token.class);
            token = getTokenAttempt.getValue();
            if (getTokenAttempt.getValue() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //method to get Uri
    private URI getUri(String path) {
        return UriComponentsBuilder.fromUriString("http:///18.220.98.185/")
                .path(path)
                .build()
                .encode()
                .toUri();
    }
    //method to get Uri with token
    private URI getUriWithToken(String path, String value) {
        return UriComponentsBuilder.fromUriString("http://18.220.98.185/")
                .path(path)
                .queryParam("token", value)
                .build()
                .encode()
                .toUri();
    }
}
