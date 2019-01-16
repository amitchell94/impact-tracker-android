package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.data.TokenRepository;

public class LoginService {
    private TokenRepository tokenRepository;

    public LoginService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Boolean getToken(Credentials credentials) {
        if (credentials.getPassword() == null || credentials.getUsername() == null ||
                "".equals(credentials.getPassword()) || "".equals(credentials.getUsername())) {
            return false;
        }
        return tokenRepository.getToken(credentials);
    }
}
