package com.proyecto.spring_proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.spring_proyecto.application.interfaces.IJwtService;
import com.proyecto.spring_proyecto.application.interfaces.IUserService;
import com.proyecto.spring_proyecto.auth.dto.LoginRequest;
import com.proyecto.spring_proyecto.auth.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permitir acceso desde el frontend

public class LoginController {
    
    private IUserService userService;
    private IJwtService jwt;

     @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return userService.authenticate(req.address(), req.password())
                .map(token -> ResponseEntity.ok(new LoginResponse(token, jwt.getExpirationSeconds())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
