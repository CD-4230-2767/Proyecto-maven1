/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author user
 */
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_id")
    private Long id;

    // Muchos detalles pertenecen a un solo pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ped_id", nullable = false)
    private Pedido pedido;

    // Muchos detalles pueden referenciar al mismo producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id", nullable = false)
    private Producto producto;

    @Column(name = "det_cantidad", nullable = false)
    private Integer cantidad;

    // Usamos BigDecimal para dinero, evita problemas de redondeo que tiene 'double'
    @Column(name = "det_precio", nullable = false)
    private BigDecimal precio;

    // --- CONSTRUCTORES ---
    public DetallePedido() {
    }

    public DetallePedido(Pedido pedido, Producto producto, Integer cantidad, BigDecimal precio) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public Pedido getPedido() { 
        return pedido; 
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto; 
    }
    public void setProducto(Producto producto) {
        this.producto = producto; 
    }

    public Integer getCantidad() { 
        return cantidad; 
    }
    public void setCantidad(Integer cantidad) { 
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() { 
        return precio; 
    }
    public void setPrecio(BigDecimal precio) { 
        this.precio = precio; 
    }
}
