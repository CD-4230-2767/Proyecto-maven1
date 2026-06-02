/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.domain.Proveedor;
import com.dosideas.autoparts.domain.Cliente;
import com.dosideas.autoparts.repository.ClienteRepository;
import com.dosideas.autoparts.repository.ProveedorRepository;
import com.dosideas.autoparts.repository.PedidoRepository; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired ClienteRepository clienteRepository;
    @Autowired ProveedorRepository proveedorRepository;
    @Autowired PedidoRepository pedidoRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("listaClientes", clienteRepository.findAll());
        model.addAttribute("listaProveedores", proveedorRepository.findAll());
        model.addAttribute("listaPedidos", pedidoRepository.findAll());

        return "admin/dashboard";
    }
}