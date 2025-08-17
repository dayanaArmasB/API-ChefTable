package com.proyecto.spring_proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.spring_proyecto.application.dto.LoginRequest;
import com.proyecto.spring_proyecto.application.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permitir acceso desde el frontend
public class LoginController {
     @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Simulación de usuario válido (reemplazar con base de datos)
        if ("admin".equals(username) && "1234".equals(password)) {
            return ResponseEntity.ok(new LoginResponse("Login exitoso", true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Credenciales incorrectas", false));
        }
    }

        @PostMapping("/recover")
    public ResponseEntity<?> recover(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Simulación de usuario válido (reemplazar con base de datos)
        if ("admin".equals(username) && "1234".equals(password)) {
            return ResponseEntity.ok(new LoginResponse("Login exitoso", true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Credenciales incorrectas", false));
        }
    }
}
