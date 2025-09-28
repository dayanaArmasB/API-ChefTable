package com.proyecto.spring_proyecto.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.spring_proyecto.application.interfaces.ICategoriaService;
import com.proyecto.spring_proyecto.core.entity.Categoria;
import com.proyecto.spring_proyecto.repository.ICategoriaDAO;

@Service
public class CategoriaServiceImp implements ICategoriaService {
    private final ICategoriaDAO categoriaDAO;

    // Inyección por constructor
    public CategoriaServiceImp(ICategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public String guardarCategoria(Categoria categoria) {
        categoriaDAO.save(categoria);
        return "La categoría se guardo correctamente";
    }

    @Override
    public List<Categoria> cargarCategorias() {
        return categoriaDAO.findAll();
    }

    @Override
    public String eliminarCategoria(Long id) {
        categoriaDAO.deleteById(id);
        return "Categoria eliminada correctamente";
    }

    @Override
    public List<Categoria> cargarOrdenadas() {
        return categoriaDAO.findAllByOrderByNombreAsc();
    }

    @Override
    public List<Categoria> cargarLike(String p) {
        return categoriaDAO.cualquierNombre(p);
    }
}
