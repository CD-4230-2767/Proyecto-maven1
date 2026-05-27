package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Producto;
import com.dosideas.autoparts.domain.Categoria;
import com.dosideas.autoparts.repository.CategoriaRepository;
import com.dosideas.autoparts.repository.ProductoRepository;
import com.dosideas.autoparts.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 *
 * @author user
 */
@Controller
public class ListadoController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private final ProductoService productoService;

    public ListadoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @RequestMapping("/")
    public String listarProductos(

            @RequestParam(required = false) String consulta,
            @RequestParam(required = false) Integer categoriaId,
            Model model) {

        List<Producto> productos;

        // BUSCAR POR NOMBRE + CATEGORIA
        if(consulta != null && !consulta.isEmpty()
                && categoriaId != null) {

            productos = productoRepository
                    .buscarPorNombreYCategoria(consulta, categoriaId);

        }
        // SOLO NOMBRE
        else if(consulta != null && !consulta.isEmpty()) {

            productos = productoRepository
                    .buscarPorNombre(consulta);

        }
        // SOLO CATEGORIA
        else if(categoriaId != null) {

            productos = productoRepository
                    .buscarPorCategoria(categoriaId);

        }
        // TODOS
        else {

            productos = productoRepository.buscarTodos();
        }

        model.addAttribute("productos", productos);

        // CATEGORIAS
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("categorias", listaCategorias);

        return "index";
    }
    
    @GetMapping("/producto/{id}")
    public String verProducto(@PathVariable Integer id,Model model){
    Producto producto = productoRepository.findById(id).orElse(null);
    model.addAttribute("producto", producto);
    return "detalle-producto";
}
}