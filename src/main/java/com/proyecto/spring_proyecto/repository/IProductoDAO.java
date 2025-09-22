package com.proyecto.spring_proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.spring_proyecto.core.entity.Producto;

@Repository
public interface IProductoDAO extends JpaRepository<Producto, Long> {
     public List<Producto> findAllByOrderByNombreAsc();
}
