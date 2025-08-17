package com.proyecto.spring_proyecto.application.services;

import java.util.List;
import java.util.Optional;



import com.proyecto.spring_proyecto.application.interfaces.IProductoService;
import com.proyecto.spring_proyecto.core.entity.Producto;
import com.proyecto.spring_proyecto.dao.IProductoDAO;

public class ProductoServiceImp implements IProductoService {


    private final IProductoDAO productoDAO;

    // Inyección por constructor
    public ProductoServiceImp(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public String guardarProducto(Producto producto) {
        productoDAO.save(producto);
        return "Se registró correctamente el producto";
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoDAO.findById(id);
    }

    @Override
    public List<Producto> cargarProductos() {
        return productoDAO.findAllByOrderByNombreAsc();
    }

    @Override
    public String eliminarProducto(Long id) {
        productoDAO.deleteById(id);
        return "Se eliminó el producto";
    }
}
