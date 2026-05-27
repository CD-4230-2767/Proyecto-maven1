/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dosideas.autoparts.repository;

import com.dosideas.autoparts.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author user
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p ORDER BY p.prodNombre")
    List<Producto> buscarTodos();

    // BUSCAR SOLO POR NOMBRE
    @Query("""
           SELECT p
           FROM Producto p
           WHERE LOWER(p.prodNombre)
           LIKE LOWER(CONCAT('%', ?1, '%'))
           """)
    List<Producto> buscarPorNombre(String nombre);

    // BUSCAR SOLO POR CATEGORIA (Corregido para usar el objeto relacion)
    @Query("""
           SELECT p
           FROM Producto p
           WHERE p.categoria.catId = ?1
           """)
    List<Producto> buscarPorCategoria(Integer categoriaId);

    // BUSCAR POR NOMBRE Y CATEGORIA (Corregido para usar el objeto relacion)
    @Query("""
           SELECT p
           FROM Producto p
           WHERE LOWER(p.prodNombre)
           LIKE LOWER(CONCAT('%', ?1, '%'))
           AND p.categoria.catId = ?2
           """)
    List<Producto> buscarPorNombreYCategoria(String nombre, Integer categoriaId);

}