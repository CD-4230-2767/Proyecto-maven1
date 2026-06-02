/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.dosideas.autoparts.repository;

import com.dosideas.autoparts.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author user
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
        SELECT p 
        FROM Pedido p 
        WHERE p.cliente.cliId = :clienteId 
        AND p.estado = 'PENDIENTE' 
        ORDER BY p.id DESC
        """)
        List<Pedido> buscarCarritosActivosPorCliente(@Param("clienteId") Long clienteId);
}
