package com.proyecto.spring_proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.spring_proyecto.model.entidad.Producto;
import com.proyecto.spring_proyecto.model.dao.IProductoDAO;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
@Autowired
private IProductoDAO productoDAO;

@CrossOrigin(origins = "http://localhost:4200")
@GetMapping
public List<Producto> getAllProductos(){
    return productoDAO.findAll();
}

 // Método GET para obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoDAO.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método POST para crear un nuevo producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoDAO.save(producto);
    }

    // Método PUT para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Optional<Producto> productoOptional = productoDAO.findById(id);

        if (!productoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = productoOptional.get();
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());

        Producto updatedProducto = productoDAO.save(producto);
        return ResponseEntity.ok(updatedProducto);
    }

    // Método DELETE para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!productoDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productoDAO.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
