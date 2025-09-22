package com.proyecto.spring_proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.spring_proyecto.core.entity.Categoria;

@Repository
public interface ICategoriaDAO extends JpaRepository<Categoria, Long> {

    public List<Categoria> findAllByOrderByNombreAsc();

    @Query(value = "SELECT * FROM categorias WHERE cat_nombre like ?1", nativeQuery = true)
    public List<Categoria> cualquierNombre(String param1);
}
