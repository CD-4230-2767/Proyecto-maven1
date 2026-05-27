/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarritoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        Long clienteId = 1L;
        Pedido pedido = pedidoService.obtenerCarritoActivo(clienteId)
                .orElseGet(() -> new Pedido(clienteId));

        model.addAttribute("pedido", pedido);

        double total = pedido.getDetalles().stream()
                .mapToDouble(d -> d.getPrecio().doubleValue() * d.getCantidad())
                .sum();

        model.addAttribute("total", total);
        return "carrito"; 
    }

    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("productoId") Integer productoId,
                                   @RequestParam("cantidad") Integer cantidad) {
        Long clienteId = 1L; 
        pedidoService.agregarProductoAlCarrito(clienteId, productoId, cantidad);
        return "redirect:/carrito"; 
    }

    @GetMapping("/carrito/eliminar/{detalleId}")
    public String eliminarProducto(@PathVariable Long detalleId){
        pedidoService.eliminarDetalle(detalleId);
        return "redirect:/carrito";
    }
    
    @GetMapping("/carrito/sumar/{detalleId}")
    public String sumarCantidad(@PathVariable Long detalleId){
        pedidoService.sumarCantidad(detalleId);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito/restar/{detalleId}")
    public String restarCantidad(@PathVariable Long detalleId){
        pedidoService.restarCantidad(detalleId);
        return "redirect:/carrito";
    }
    
    @PostMapping("/carrito/pagar")
    public String pagar(){
        Long clienteId = 1L;
        pedidoService.finalizarCompra(clienteId);
        return "redirect:/";
    }
}
