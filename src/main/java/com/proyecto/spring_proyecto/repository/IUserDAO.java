package com.proyecto.spring_proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.spring_proyecto.core.entity.User;

public interface IUserDAO extends JpaRepository<User, Long> {
    Optional<User> findByAddress(String address);
    
}
