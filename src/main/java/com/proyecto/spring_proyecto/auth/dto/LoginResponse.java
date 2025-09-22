package com.proyecto.spring_proyecto.auth.dto;


public class LoginResponse {
    
    private String address;
    private String token;

    public LoginResponse(String address, String token) {
        this.address = address;
        this.token = token;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
}
