package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.ImpactRepository;

public class LoginService {
    private ImpactRepository impactRepository;

    public LoginService(ImpactRepository impactRepository) {
        this.impactRepository = impactRepository;
    }

    public Boolean getToken(Credentials credentials) {
        if (credentials.getPassword() == null || credentials.getUsername() == null ||
                "".equals(credentials.getPassword()) || "".equals(credentials.getUsername())) {
            return false;
        }
        return impactRepository.getToken(credentials);
    }
}
