package com.proyecto.spring_proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.spring_proyecto.core.entity.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Long> {
     public List<Producto> findAllByOrderByNombreAsc();
}
