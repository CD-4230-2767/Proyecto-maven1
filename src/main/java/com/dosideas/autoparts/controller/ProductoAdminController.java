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

    @Autowired // <--- ¡Asegúrate de que esto esté presente!
    private ProductoRepository productoRepository;
    
    @Autowired // <--- ¡Asegúrate de que esto esté presente!
    private ProveedorRepository proveedorRepository;

    @Autowired // <--- ¡Asegúrate de que esto esté presente!
    private CategoriaRepository categoriaRepository;

    // 1. LISTAR: Este método se ejecutará cuando le des al botón "Productos" del menú lateral
    @GetMapping
    public String listarProductosAdmin(Model model) {
        List<Producto> listaProductos = productoRepository.findAll();
        model.addAttribute("productos", listaProductos);
        return "productos-listado"; // Nombre del nuevo HTML con la tabla
    }
    // 1. GET: Carga el formulario vacío (para el botón "+ Nuevo Producto")
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Producto p = new Producto();
        p.setCategoria(new Categoria());
        p.setProveedor(new Proveedor());
        model.addAttribute("producto", p);
        
        // Cargamos las listas para los SELECTs
        model.addAttribute("listaProveedores", proveedorRepository.findAll());
        model.addAttribute("listaCategorias", categoriaRepository.findAll());
        
        return "productos"; 
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto,
                                  RedirectAttributes redirectAttributes) {

        productoRepository.save(producto);

        redirectAttributes.addFlashAttribute("mensaje",
                "Producto guardado correctamente");

        return "redirect:/admin/productos";
    }

    // 3. EDITAR: Carga los datos para modificar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "producto";
        }
        return "redirect:/admin/productos";
    }
}
