package com.proyecto.spring_proyecto.auth.dto;

public class LoginLegacyResponse {
    private String token;
    private long expiresInSeconds;

    public LoginLegacyResponse(String token, long expiresInSeconds) {
        this.token = token;
        this.expiresInSeconds = expiresInSeconds;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public long getExpiresInSeconds() {
        return expiresInSeconds;
    }
    public void setExpiresInSeconds(long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }
}
