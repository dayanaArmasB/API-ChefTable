package com.proyecto.spring_proyecto.application.interfaces;

import io.jsonwebtoken.Claims;
public interface IJwtService {
    String generateToken(String email);
    Claims parseClaims(String token);
    boolean isExpired(String token);
    long getExpirationSeconds();
}
