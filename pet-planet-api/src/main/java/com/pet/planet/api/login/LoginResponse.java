package com.pet.planet.api.login;

/**
 * Created by gabrieldyck on 29/04/17.
 */
public class LoginResponse {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
