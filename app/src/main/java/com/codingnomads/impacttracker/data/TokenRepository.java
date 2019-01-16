package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.Credentials;
import com.codingnomads.impacttracker.logic.Token;

import org.springframework.web.client.RestTemplate;

import static com.codingnomads.impacttracker.data.UriMaker.getUri;

public class TokenRepository {

    private RestTemplate restTemplate;
    private static String storedToken;

    public TokenRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

    public static void setStoredToken(String storedToken) {
        TokenRepository.storedToken = storedToken;
    }

    public static String getStoredToken() {
        return storedToken;
    }
}
