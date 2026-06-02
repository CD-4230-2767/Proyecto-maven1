/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.service;
import org.springframework.stereotype.Service;
import com.dosideas.autoparts.domain.Cliente;
import com.dosideas.autoparts.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author user
 */
@Service
public class ClienteService {
    
 @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findByEmail(String email) {
        return clienteRepository.findByCliEmail(email);
    }
}
