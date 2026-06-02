/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Cliente;
import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.service.ClienteService;
import com.dosideas.autoparts.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class PagoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/pagar")
    public String mostrarPago(Authentication auth,
                              Model model){

        Cliente cliente =
                clienteService.findByEmail(auth.getName());

        Pedido pedido =
                pedidoService.obtenerCarritoActivo(
                        cliente.getCliId().longValue())
                        .orElseThrow();

        double total = pedido.getDetalles()
                .stream()
                .mapToDouble(d ->
                    d.getPrecio().doubleValue()
                    * d.getCantidad())
                .sum();

        model.addAttribute("pedido", pedido);
        model.addAttribute("total", total);

        return "pagar";
    }

    @PostMapping("/pagar/confirmar")
    public String confirmarPago(
            @RequestParam String metodoPago,
            Authentication auth){

        Cliente cliente =
                clienteService.findByEmail(auth.getName());

        pedidoService.finalizarCompra(
                cliente.getCliId().longValue(),
                metodoPago);

        return "redirect:/";
    }
}