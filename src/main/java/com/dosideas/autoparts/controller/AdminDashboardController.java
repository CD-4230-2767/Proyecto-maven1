/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Pedido;
import com.dosideas.autoparts.domain.Proveedor;
import com.dosideas.autoparts.repository.ClienteRepository;
import com.dosideas.autoparts.repository.ProveedorRepository;
import com.dosideas.autoparts.repository.PedidoRepository; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private PedidoRepository pedidoRepository; 

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        
        // Aquí se asegura que la variable se llame exactamente igual a la declarada arriba
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        model.addAttribute("listaPedidos", listaPedidos);
        
        List<Proveedor> listaProveedores = proveedorRepository.findAll();
        model.addAttribute("listaProveedores", listaProveedores);
        
        return "admin-dashboard";
    }
}