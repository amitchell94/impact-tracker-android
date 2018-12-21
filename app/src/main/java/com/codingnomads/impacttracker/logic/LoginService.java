package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.ImpactRepository;

public class LoginService {
    private ImpactRepository impactRepository;

    public LoginService(ImpactRepository impactRepository) {
        this.impactRepository = impactRepository;
    }

    public String getToken(Credentials credentials) {
        return impactRepository.getToken(credentials);
    }
}
