package com.example.demo.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Handles authenticating api keys against environment variable
 */
public class ApiKeyAuthManager implements AuthenticationManager {
    private static final Logger LOG = LoggerFactory.getLogger(ApiKeyAuthManager.class);

    private final String keys;

    public ApiKeyAuthManager() {
        this.keys = "misma";
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        if (!keys.equals(principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        } else {
            LOG.info("Loading api key, auth completed");
            authentication.setAuthenticated(true);
            return authentication;
        }
    }

}
