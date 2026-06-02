/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author user
 */

@Controller
public class LoginController {
    
    // Solo dejamos el login general del sistema (ej. Administrador)
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }
}
