/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dosideas.autoparts.service;

import com.dosideas.autoparts.domain.Producto;
import com.dosideas.autoparts.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // TODOS LOS PRODUCTOS
    public List<Producto> buscarDestacados() {
        return productoRepository.buscarTodos();
    }

    // BUSCAR SOLO POR NOMBRE
    public List<Producto> buscarPorNombre(String consulta) {
        return productoRepository.buscarPorNombre(consulta);
    }

    // BUSCAR SOLO POR CATEGORIA
    public List<Producto> buscarPorCategoria(Integer categoriaId) {
        return productoRepository.buscarPorCategoria(categoriaId);
    }

    // BUSCAR POR NOMBRE + CATEGORIA
    public List<Producto> buscarPorNombreYCategoria(
            String consulta,
            Integer categoriaId) {

        return productoRepository
                .buscarPorNombreYCategoria(consulta, categoriaId);
    }

}
