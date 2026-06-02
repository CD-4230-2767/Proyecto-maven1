/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.dosideas.autoparts.repository;

import com.dosideas.autoparts.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCliEmail(String email);
}