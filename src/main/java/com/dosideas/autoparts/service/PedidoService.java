/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.service;

import com.dosideas.autoparts.domain.DetallePedido;
import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.domain.Producto;
import com.dosideas.autoparts.repository.DetallePedidoRepository;
import com.dosideas.autoparts.repository.PedidoRepository;
import com.dosideas.autoparts.repository.ProductoRepository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public Pedido agregarProductoAlCarrito(Long clienteId, Integer productoId, Integer cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Pedido pedido = pedidoRepository.buscarCarritoActivoPorCliente(clienteId)
                .orElseGet(() -> new Pedido(clienteId));

        Optional<DetallePedido> detalleExistente = pedido.getDetalles().stream()
                .filter(det -> det.getProducto().getProdId() == productoId)
                .findFirst();

        if (detalleExistente.isPresent()) {
            DetallePedido detalle = detalleExistente.get();
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        } else {
            BigDecimal precio = BigDecimal.valueOf(producto.getProdPrecioVenta());
            DetallePedido nuevoDetalle = new DetallePedido(pedido, producto, cantidad, precio);
            pedido.agregarDetalle(nuevoDetalle);
        }

        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obtenerCarritoActivo(Long clienteId) {
        return pedidoRepository.buscarCarritoActivoPorCliente(clienteId);
    }

    @Transactional
    public void eliminarDetalle(Long detalleId){
        detallePedidoRepository.deleteById(detalleId);
    }

    @Transactional
    public void sumarCantidad(Long detalleId){
        DetallePedido detalle = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        detalle.setCantidad(detalle.getCantidad() + 1);
        detallePedidoRepository.save(detalle);
    }

    @Transactional
    public void restarCantidad(Long detalleId){
        DetallePedido detalle = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        if(detalle.getCantidad() > 1){
            detalle.setCantidad(detalle.getCantidad() - 1);
            detallePedidoRepository.save(detalle);
        } else {
            detallePedidoRepository.delete(detalle);
        }
    }

    @Transactional
    public void finalizarCompra(Long clienteId){
        Pedido pedido = pedidoRepository.buscarCarritoActivoPorCliente(clienteId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        for(DetallePedido detalle : pedido.getDetalles()){
            Producto producto = detalle.getProducto();
            producto.setProdStock(producto.getProdStock() - detalle.getCantidad());
            productoRepository.save(producto);
        }

        pedido.setMetodoPago("EFECTIVO");
        pedidoRepository.save(pedido);
    }
}