package com.proyecto.spring_proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.spring_proyecto.core.entity.Categoria;

public interface ICategoriaDAO extends JpaRepository<Categoria, Long> {

    public List<Categoria> findAllByOrderByNombreAsc();

    @Query(value = "SELECT * FROM categorias WHERE cat_nombre like ?1", nativeQuery = true)
    public List<Categoria> cualquierNombre(String param1);
}
