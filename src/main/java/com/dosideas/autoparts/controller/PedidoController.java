/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("listaPedidos", pedidoRepository.findAll());
        model.addAttribute("pedido", new Pedido());
        return "admin/pedido";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pedido pedido) {
        pedidoRepository.save(pedido);
        return "redirect:/admin/pedido/listar";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/admin/pedido/listar";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {
        model.addAttribute("pedido",
                pedidoRepository.findById(id).orElse(null));
        model.addAttribute("listaPedidos", pedidoRepository.findAll());
        return "admin/pedido";
    }

    @GetMapping("/detalles")
    public String detalles(@RequestParam Long id, Model model) {
        model.addAttribute("pedido",
                pedidoRepository.findById(id).orElse(null));
        return "admin/pedido-detalles";
    }
}
