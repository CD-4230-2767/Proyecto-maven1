/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;
import com.dosideas.autoparts.domain.Producto;
import com.dosideas.autoparts.domain.Categoria;
import com.dosideas.autoparts.domain.Proveedor;
import com.dosideas.autoparts.repository.ProductoRepository;
import com.dosideas.autoparts.repository.CategoriaRepository;
import com.dosideas.autoparts.repository.ProveedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */

@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "admin/productos-listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("listaProveedores", proveedorRepository.findAll());
        model.addAttribute("listaCategorias", categoriaRepository.findAll());
        return "admin/productos";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto) {
        productoRepository.save(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("producto",
                productoRepository.findById(id).orElse(null));

        model.addAttribute("listaProveedores", proveedorRepository.findAll());
        model.addAttribute("listaCategorias", categoriaRepository.findAll());

        return "admin/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        productoRepository.deleteById(id);
        return "redirect:/admin/productos";
    }
}
