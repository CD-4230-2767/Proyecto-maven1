/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Long id;

    @Column(name = "ped_fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    // Puede ser nullable porque al crear el carrito aún no se ha entregado
    @Column(name = "ped_fecha_entrega")
    private LocalDateTime fechaEntrega;

    // Ejemplo: "EFECTIVO", "TARJETA", "TRANSFERENCIA" (o null mientras sea carrito)
    @Column(name = "ped_metodo_pago")
    private String metodoPago;

    // ID del cliente que realiza la compra
    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;
    
    @Column(name="ped_estado")
    private String estado;

    // RELACIÓN BIDIRECCIONAL: Un pedido tiene muchos detalles.
    // 'mappedBy = "pedido"' le dice a JPA que la relación la gobierna el campo 'pedido' en DetallePedido.
    // 'cascade = CascadeType.ALL' hace que si guardas/borras un Pedido, se guarden/borren sus detalles automáticamente.
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    // --- CONSTRUCTORE ---

    public Pedido() {
    this.fechaPedido = LocalDateTime.now();
    this.estado = "PENDIENTE";
    }

    // --- MÉTODOS DE AYUDA (Helper Methods) ---
    // Estos métodos ayudan a mantener sincronizados ambos lados de la relación en Java
    public void agregarDetalle(DetallePedido detalle) {
        detalles.add(detalle);
        detalle.setPedido(this); // Vincula el detalle con este pedido
    }

    public void removerDetalle(DetallePedido detalle) {
        detalles.remove(detalle);
        detalle.setPedido(null);
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() {
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public LocalDateTime getFechaPedido() {
        return fechaPedido; 
    }
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido; 
    }
    public LocalDateTime getFechaEntrega() {
        return fechaEntrega; 
    }
    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega; 
    }

    public String getMetodoPago() { 
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) { 
        this.metodoPago = metodoPago; 
    }
    
    public Cliente getCliente() {
    return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() { 
        return detalles;
    }
    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles; 
    }
    public String getEstado() {
    return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}