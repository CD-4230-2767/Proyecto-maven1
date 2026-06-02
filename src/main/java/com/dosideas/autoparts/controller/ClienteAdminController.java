/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Cliente;
import com.dosideas.autoparts.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin/cliente")
public class ClienteAdminController {

    @Autowired
    private ClienteRepository clienteRepository;
    @GetMapping("/listar")

    public String listar(Model model) {
        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("cliente", new Cliente());
        return "admin/cliente";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/cliente";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/admin/cliente/listar";
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer id, Model model) {
        model.addAttribute("cliente",
                clienteRepository.findById(id).orElse(null));
        return "admin/cliente";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/admin/cliente/listar";
    }
}

