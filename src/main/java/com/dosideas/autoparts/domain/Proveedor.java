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
import java.time.LocalDate; 
import jakarta.persistence.*;

/**
 *
 * @author user
 */
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prov_id")
    private Integer provId;

    @Column(name = "prov_nombre")
    private String provNombre;

    @Column(name = "prov_empresa")
    private String provEmpresa;

    @Column(name = "prov_telefono")
    private String provTelefono;

    @Column(name = "prov_email")
    private String provEmail;

    @Column(name = "prov_calle")
    private String provCalle;

    @Column(name = "prov_cp")
    private String provCp;

    @Column(name = "prov_colonia")
    private String provColonia;

    @Column(name = "prov_estado")
    private String provEstado;

    @Column(name = "prov_fecha_registro")
    private LocalDate provFechaRegistro;

    // Constructor vacío (Obligatorio para JPA)
    public Proveedor() {
    }

    // Constructor con campos
    public Proveedor(Integer provId, String provNombre, String provEmpresa, String provTelefono, 
                     String provEmail, String provCalle, String provCp, String provColonia, 
                     String provEstado, LocalDate provFechaRegistro) {
        this.provId = provId;
        this.provNombre = provNombre;
        this.provEmpresa = provEmpresa;
        this.provTelefono = provTelefono;
        this.provEmail = provEmail;
        this.provCalle = provCalle;
        this.provCp = provCp;
        this.provColonia = provColonia;
        this.provEstado = provEstado;
        this.provFechaRegistro = provFechaRegistro;
    }

    // Getters y Setters
    public Integer getProvId() { 
        return provId; 
    }
    public void setProvId(Integer provId) { 
        this.provId = provId; 
    }

    public String getProvNombre() { 
        return provNombre; 
    }
    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getProvEmpresa() {
        return provEmpresa; 
    }
    public void setProvEmpresa(String provEmpresa) {
        this.provEmpresa = provEmpresa; 
    }
    public String getProvTelefono() { 
        return provTelefono; 
    }
    public void setProvTelefono(String provTelefono) { 
        this.provTelefono = provTelefono; 
    }
    public String getProvEmail() { 
        return provEmail; 
    }
    public void setProvEmail(String provEmail) { 
        this.provEmail = provEmail; 
    }
    public String getProvCalle() { 
        return provCalle; 
    }
    public void setProvCalle(String provCalle) {
        this.provCalle = provCalle;
    }
    public String getProvCp() {
        return provCp;
    }
    public void setProvCp(String provCp) { 
        this.provCp = provCp; 
    }
    public String getProvColonia() {
        return provColonia; 
    }
    public void setProvColonia(String provColonia) { 
        this.provColonia = provColonia; 
    }
    public String getProvEstado() { 
        return provEstado;
    }
    public void setProvEstado(String provEstado) { 
        this.provEstado = provEstado; 
    }

    public LocalDate getProvFechaRegistro() { 
        return provFechaRegistro; 
    }
    public void setProvFechaRegistro(LocalDate provFechaRegistro) {
        this.provFechaRegistro = provFechaRegistro;
    }
}
