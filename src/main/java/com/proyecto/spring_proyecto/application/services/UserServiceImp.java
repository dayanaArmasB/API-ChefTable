package com.proyecto.spring_proyecto.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.spring_proyecto.application.interfaces.IJwtService;
import com.proyecto.spring_proyecto.application.interfaces.IUserService;
import com.proyecto.spring_proyecto.auth.dto.LoginResponse;
import com.proyecto.spring_proyecto.core.entity.User;
import com.proyecto.spring_proyecto.repository.IUserDAO;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImp implements IUserService {
    private final IUserDAO userDAO;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(IUserDAO userDAO, IJwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<String> authenticate(String address, String rawPassword) {
        // System.out.println("RAW PASSWORD RECIBIDO: " + rawPassword);

        return userDAO.findByAddress(address)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .map(u -> jwtService.generateToken(u.getAddress()));
    }

    @Override
    public LoginResponse renewToken(String token) {
        var claims = jwtService.parseClaims(token);
        var address = claims.getSubject();
        return userDAO.findByAddress(address)
                .map(u -> new LoginResponse(address, jwtService.generateToken(address)))
                .orElse(null);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }
}
