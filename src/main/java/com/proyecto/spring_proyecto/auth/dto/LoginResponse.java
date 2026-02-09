package com.proyecto.spring_proyecto.auth.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String address;
    private String token;
    private boolean success;
    private String message;

    public LoginResponse(String address, String token, boolean success, String message) {
        this.address = address;
        this.token = token;
        this.success = success;
        this.message = message;
    }

}
