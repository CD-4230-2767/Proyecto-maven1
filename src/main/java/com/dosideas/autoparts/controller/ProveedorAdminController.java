/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Proveedor;
import com.dosideas.autoparts.repository.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.time.LocalDate;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/admin/proveedor")
public class ProveedorAdminController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("listaProveedores", proveedorRepository.findAll());
        model.addAttribute("proveedor", new Proveedor());
        return "admin/proveedor";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "admin/proveedor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "redirect:/admin/proveedor/listar";
    }

    @GetMapping("/modificar")
    public String modificar(@RequestParam Integer id, Model model) {
        model.addAttribute("proveedor",
                proveedorRepository.findById(id).orElse(null));
        return "admin/proveedor";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        proveedorRepository.deleteById(id);
        return "redirect:/admin/proveedor/listar";
    }
}
