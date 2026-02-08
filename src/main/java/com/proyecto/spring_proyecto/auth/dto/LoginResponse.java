package com.proyecto.spring_proyecto.auth.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String address;
    private String token;

    public LoginResponse(String address, String token) {
        this.address = address;
        this.token = token;
    }

}
