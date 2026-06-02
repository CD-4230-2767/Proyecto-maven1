/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dosideas.autoparts.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 *
 * @author user
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Integer cliId;

    @Column(name = "cli_nombre")
    private String cliNombre;

    @Column(name = "cli_telefono")
    private String cliTelefono;

    @Column(name = "cli_email")
    private String cliEmail;

    @Column(name = "cli_calle")
    private String cliCalle;

    @Column(name = "cli_cp")
    private String cliCp;

    @Column(name = "cli_colonia")
    private String cliColonia;

    @Column(name = "cli_estado")
    private String cliEstado;
    
    @Column(name = "cli_password")
    private String cliPassword;

    // Constructor vacío (Obligatorio para JPA)
    public Cliente() {
    }

    // Constructor con campos
    public Cliente(Integer cliId, String cliNombre, String cliTelefono, String cliEmail, 
                   String cliCalle, String cliCp, String cliColonia, String cliEstado, String cliPassword) {
        this.cliId = cliId;
        this.cliNombre = cliNombre;
        this.cliTelefono = cliTelefono;
        this.cliEmail = cliEmail;
        this.cliCalle = cliCalle;
        this.cliCp = cliCp;
        this.cliColonia = cliColonia;
        this.cliEstado = cliEstado;
        this.cliPassword = cliPassword;
    }

    // Getters y Setters
    public Integer getCliId() {
        return cliId; 
    }
    public void setCliId(Integer cliId) { 
        this.cliId = cliId; 
    }

    public String getCliNombre() { 
        return cliNombre;
    }
    public void setCliNombre(String cliNombre) { 
        this.cliNombre = cliNombre; 
    }

    public String getCliTelefono() {
        return cliTelefono; 
    }
    public void setCliTelefono(String cliTelefono) { 
        this.cliTelefono = cliTelefono; 
    }

    public String getCliEmail() { 
        return cliEmail; 
    }
    public void setCliEmail(String cliEmail) { 
        this.cliEmail = cliEmail; 
    }

    public String getCliCalle() { 
        return cliCalle; 
    }
    public void setCliCalle(String cliCalle) { 
        this.cliCalle = cliCalle; 
    }

    public String getCliCp() { 
        return cliCp;
    }
    public void setCliCp(String cliCp) { 
        this.cliCp = cliCp; 
    }

    public String getCliColonia() { 
        return cliColonia; 
    }
    public void setCliColonia(String cliColonia) {
        this.cliColonia = cliColonia; 
    }

    public String getCliEstado() { 
        return cliEstado;
    }
    public void setCliEstado(String cliEstado) {
        this.cliEstado = cliEstado;
    }
    
    public String getCliPassword() {
    return cliPassword;
    }

    public void setCliPassword(String cliPassword) {
        this.cliPassword = cliPassword;
    }
}
