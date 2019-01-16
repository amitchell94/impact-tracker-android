package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.ImpactRepository;
import com.codingnomads.impacttracker.data.RegisterRepository;

import org.springframework.web.client.RestClientException;

public class RegisterService {
    private RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Boolean registerUser(Credentials credentials) throws RestClientException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        if (username == null || "".equals(username)) {
            return false;
        }

        if (password == null || "".equals(password) ||
                password.length() < 7) {
            return false;
        }
        return registerRepository.registerUser(credentials);
    }
}
