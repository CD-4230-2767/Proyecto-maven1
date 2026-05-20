/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Producto;
import com.dosideas.autoparts.domain.Categoria; // <-- Importamos tu clase Categoria
import com.dosideas.autoparts.repository.CategoriaRepository; // <-- Importamos tu repositorio de categorías
import com.dosideas.autoparts.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; // <-- Importamos el Autowired
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class ListadoController {
    
    @Autowired // <-- Con esto Spring Boot llena la variable automáticamente sin dejarla null
    private CategoriaRepository categoriaRepository;
    
    private final ProductoService productoService;

    // Mantenemos el constructor para el servicio de productos
    public ListadoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @RequestMapping("/")
    public String listarProductos(Model model) {

        List<Producto> destacados = productoService.buscarDestacados();
        model.addAttribute("productos", destacados);

        // SOLUCIÓN AL ERROR EN INDEX.HTML:
        // Buscamos todas las categorías de MySQL y las mandamos a la plantilla Thymeleaf
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("categorias", listaCategorias);
        
        // Si la línea 47 del index.html usa un objeto categoría vacío para registrar datos (th:object="${categoria}"):
        model.addAttribute("categoria", new Categoria());

        return "index";
    }

}
