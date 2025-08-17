package com.proyecto.spring_proyecto.application.interfaces;

import java.util.List;

import com.proyecto.spring_proyecto.core.entity.Categoria;

public interface ICategoriaService {
    public String guardarCategoria(Categoria categoria);
    public List<Categoria> cargarCategorias();
    public String eliminarCategoria(Long id);

    public List<Categoria> cargarOrdenadas();
    public List<Categoria> cargarLike(String p);
}
