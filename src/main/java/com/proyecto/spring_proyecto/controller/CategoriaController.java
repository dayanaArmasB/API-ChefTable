package com.proyecto.spring_proyecto.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.spring_proyecto.application.interfaces.ICategoriaService;
import com.proyecto.spring_proyecto.core.entity.Categoria;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*") // Permitir acceso desde el frontend
public class CategoriaController {
    
    private final ICategoriaService categoriaService;

    // 🔹 Obtener todas las categorías
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.cargarCategorias();
    }

    // 🔹 Obtener categorías ordenadas por nombre
    @GetMapping("/ordenadas")
    public List<Categoria> getCategoriasOrdenadas() {
        return categoriaService.cargarOrdenadas();
    }

    // 🔹 Buscar categorías por nombre (LIKE)
    @GetMapping("/buscar")
    public List<Categoria> buscarCategorias(@RequestParam String q) {
        return categoriaService.cargarLike(q);
    }

    // 🔹 Crear categoría
    @PostMapping
    public String createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    // 🔹 Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
