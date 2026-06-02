/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.domain.Cliente;
import com.dosideas.autoparts.service.PedidoService;
import com.dosideas.autoparts.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class CarritoController {

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/carrito")
    public String verCarrito(
            Authentication authentication,
            Model model) {

        String email = authentication.getName();
        Cliente cliente = clienteService.findByEmail(email);

        Pedido pedido = pedidoService
                .obtenerCarritoActivo(cliente.getCliId().longValue())
                .orElseGet(Pedido::new);

        model.addAttribute("pedido", pedido);

        double total = pedido.getDetalles().stream()
                .mapToDouble(d -> d.getPrecio().doubleValue() * d.getCantidad())
                .sum();

        model.addAttribute("total", total);

        return "carrito";
    }
    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam Integer productoId,
                                   @RequestParam Integer cantidad,
                                   @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {

        String email = user.getUsername();
        Cliente cliente = clienteService.findByEmail(email);

        // VALIDACIÓN: Si el cliente no existe en la BD, redirigir al login o página de error
        if (cliente == null) {
            return "redirect:/login?error=usuarioNoEncontrado";
        }

        Long clienteId = cliente.getCliId().longValue();
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
    public String pagar(
            @RequestParam String metodoPago,
            Authentication authentication,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) { // 1. Agrega RedirectAttributes

        if(authentication == null){
            return "redirect:/login-user";
        }

        String email = authentication.getName();
        Cliente cliente = clienteService.findByEmail(email);

        try {
            pedidoService.finalizarCompra(
                    cliente.getCliId().longValue(),
                    metodoPago);

            redirectAttributes.addFlashAttribute("mensaje", "¡Compra finalizada con éxito!");
            return "redirect:/";

        } catch (RuntimeException e) {
            // 2. Captura el error de stock y envía el mensaje al usuario
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/carrito"; 
        }
    }
}
