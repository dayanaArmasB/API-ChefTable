package com.proyecto.spring_proyecto.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.spring_proyecto.application.interfaces.IJwtService;
import com.proyecto.spring_proyecto.application.interfaces.IUserService;
import com.proyecto.spring_proyecto.auth.dto.LoginResponse;
import com.proyecto.spring_proyecto.auth.dto.RegisterUserRequest;
import com.proyecto.spring_proyecto.auth.dto.RegisterUserResponse;
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
    public LoginResponse authenticate(String address, String rawPassword) {

        // 1️⃣ Buscar usuario
        return userDAO.findByAddress(address)
                .map(user -> {

                    // 2️⃣ Validar password
                    if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
                        return new LoginResponse(
                                null,
                                null,
                                false,
                                "Credenciales incorrectas"
                        );
                    }

                    // 3️⃣ Login exitoso
                    String token = jwtService.generateToken(user.getAddress());

                    return new LoginResponse(
                            user.getAddress(),
                            token,
                            true,
                            "Login exitoso"
                    );
                })
                .orElseGet(() -> new LoginResponse(
                        null,
                        null,
                        false,
                        "Usuario no encontrado"
                ));
    }


    @Override
    public LoginResponse renewToken(String token) {

        try {
            // 1️⃣ Validar y parsear token
            var claims = jwtService.parseClaims(token);
            var address = claims.getSubject();

            // 2️⃣ Verificar que el usuario exista
            return userDAO.findByAddress(address)
                    .map(user -> new LoginResponse(
                            address,
                            jwtService.generateToken(address),
                            true,
                            "Token renovado correctamente"
                    ))
                    .orElseGet(() -> new LoginResponse(
                            null,
                            null,
                            false,
                            "Usuario no encontrado"
                    ));

        } catch (Exception e) {
            // 3️⃣ Token inválido / expirado / manipulado
            return new LoginResponse(
                    null,
                    null,
                    false,
                    "Token inválido o expirado"
            );
        }
    }


    @Override
    public RegisterUserResponse save(RegisterUserRequest request) {

        RegisterUserResponse response = new RegisterUserResponse();

        // 1️⃣ Validación de negocio
        if (userDAO.findByAddress(request.getAddress()).isPresent()) {
            response.setSuccess(false);
            response.setMessage("El correo ya está registrado");
            return response;
        }

        // 2️⃣ Mapear request → entidad
        User user = new User();
        user.setAddress(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 3️⃣ Persistir
        user = userDAO.save(user);

        // 4️⃣ Respuesta
        response.setSuccess(true);
        response.setMessage("Usuario registrado correctamente");
        response.setUserId(user.getId());

        return response;
    }
}
