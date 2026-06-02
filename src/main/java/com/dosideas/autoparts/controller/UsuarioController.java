/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import com.dosideas.autoparts.domain.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author user
 */
@Controller
public class UsuarioController {

    @GetMapping("/login-user")
    public String loginUser(Model model) {
            model.addAttribute("cliente", new Cliente());
            return "login-user";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro";
    }

    @PostMapping("/registro")
    public String registroPost(@ModelAttribute Cliente cliente) {
        return "redirect:/login-user";
    }
}
