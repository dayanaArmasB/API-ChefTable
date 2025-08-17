package com.proyecto.spring_proyecto.application.interfaces;

import java.util.List;
import java.util.Optional;

import com.proyecto.spring_proyecto.core.entity.Producto;

public interface IProductoService {
    public String guardarProducto(Producto producto);
    public List<Producto> cargarProductos();
    public String eliminarProducto(Long id);

    public Optional<Producto> getProductoById(Long id);
}
