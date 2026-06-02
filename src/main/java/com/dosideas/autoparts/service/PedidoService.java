package com.dosideas.autoparts.service;

import com.dosideas.autoparts.domain.Cliente;
import com.dosideas.autoparts.domain.DetallePedido;
import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.domain.Producto;
import com.dosideas.autoparts.repository.ClienteRepository;
import com.dosideas.autoparts.repository.DetallePedidoRepository;
import com.dosideas.autoparts.repository.PedidoRepository;
import com.dosideas.autoparts.repository.ProductoRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public Pedido agregarProductoAlCarrito(Long clienteId,
                                           Integer productoId,
                                           Integer cantidad) {

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<Pedido> carritos =
                pedidoRepository.buscarCarritosActivosPorCliente(clienteId);

        Pedido pedido;

        if (carritos.isEmpty()) {

            Cliente cliente = clienteRepository
                    .findById(clienteId.intValue())
                    .orElseThrow(() ->
                            new RuntimeException("Cliente no encontrado"));

            pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setEstado("PENDIENTE");
            pedido.setFechaPedido(java.time.LocalDateTime.now());

        } else {

            pedido = carritos.get(0);
        }

        Optional<DetallePedido> detalleExistente =
                pedido.getDetalles().stream()
                        .filter(det ->
                            Integer.valueOf(det.getProducto().getProdId()).equals(productoId))
                        .findFirst();

        if (detalleExistente.isPresent()) {

            DetallePedido detalle = detalleExistente.get();
            detalle.setCantidad(detalle.getCantidad() + cantidad);

        } else {

            BigDecimal precio =
                    BigDecimal.valueOf(producto.getProdPrecioVenta());

            DetallePedido nuevoDetalle =
                    new DetallePedido(
                            pedido,
                            producto,
                            cantidad,
                            precio
                    );

            pedido.agregarDetalle(nuevoDetalle);
        }

        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obtenerCarritoActivo(Long clienteId) {

        List<Pedido> carritos =
                pedidoRepository.buscarCarritosActivosPorCliente(clienteId);

        if (carritos.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(carritos.get(0));
    }

    @Transactional
    public void eliminarDetalle(Long detalleId) {
        detallePedidoRepository.deleteById(detalleId);
    }

    @Transactional
    public void sumarCantidad(Long detalleId) {

        DetallePedido detalle = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() ->
                        new RuntimeException("Detalle no encontrado"));

        detalle.setCantidad(detalle.getCantidad() + 1);

        detallePedidoRepository.save(detalle);
    }

    @Transactional
    public void restarCantidad(Long detalleId) {

        DetallePedido detalle = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() ->
                        new RuntimeException("Detalle no encontrado"));

        if (detalle.getCantidad() > 1) {

            detalle.setCantidad(detalle.getCantidad() - 1);

            detallePedidoRepository.save(detalle);

        } else {

            detallePedidoRepository.delete(detalle);
        }
    }

    @Transactional
    public void finalizarCompra(Long clienteId, String metodoPago) {

        List<Pedido> carritos =
                pedidoRepository.buscarCarritosActivosPorCliente(clienteId);

        if (carritos.isEmpty()) {
            throw new RuntimeException("Carrito no encontrado");
        }

        Pedido pedido = carritos.get(0);

        for (DetallePedido detalle : pedido.getDetalles()) {

            Producto producto = detalle.getProducto();

            if (producto.getProdStock() < detalle.getCantidad()) {
                throw new RuntimeException(
                        "Stock insuficiente para "
                        + producto.getProdNombre()
                );
            }

            producto.setProdStock(
                    producto.getProdStock() - detalle.getCantidad()
            );

            productoRepository.save(producto);
        }

        pedido.setMetodoPago(metodoPago);
        pedido.setEstado("ENTREGADO"); // o PAGADO
        pedido.setFechaEntrega(java.time.LocalDateTime.now().plusDays(3));

        pedidoRepository.save(pedido);
    }
}