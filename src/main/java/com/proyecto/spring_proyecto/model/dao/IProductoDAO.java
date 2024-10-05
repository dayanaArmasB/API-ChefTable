package com.proyecto.spring_proyecto.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.spring_proyecto.model.entidad.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Long> {
    
}
