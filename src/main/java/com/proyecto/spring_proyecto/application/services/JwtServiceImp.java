package com.proyecto.spring_proyecto.application.services;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.proyecto.spring_proyecto.application.interfaces.IJwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
@ConfigurationProperties(prefix = "security.jwt")
public class JwtServiceImp implements IJwtService {
  /** Debe estar en Base64 (64+ bytes si usas HS512; con HS256 basta 32+ bytes) */
    private String secret;
    private Key key;
    @Value("${ttl-seconds:1800}") private long expirationSeconds;

    @PostConstruct
    void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expirationSeconds)))
                .signWith(key, SignatureAlgorithm.HS256) // Usa HS512 si tu clave es >=64 bytes reales
                .compact();
    }

    @Override
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTtlSeconds(long ttlSeconds) {
        this.expirationSeconds = ttlSeconds;
    }

    @Override
    public long getExpirationSeconds() {
        return expirationSeconds;
    }
}
