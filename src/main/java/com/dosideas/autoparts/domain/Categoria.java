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
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "cat_nombre")
    private String catNombre;

    @Column(name = "cat_descripcion")
    private String catDescripcion;

    @Column(name = "cat_estado")
    private String catEstado;

    // Constructor vacío (Obligatorio para JPA)
    public Categoria() {
    }

    // Constructor completo corregido
    public Categoria(Integer catId, String catNombre, String catDescripcion, String catEstado) {
        this.catId = catId;
        this.catNombre = catNombre;
        this.catDescripcion = catDescripcion;
        this.catEstado = catEstado;
    }

    // Getters y Setters completos
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatNombre() {
        return catNombre;
    }

    public void setCatNombre(String catNombre) {
        this.catNombre = catNombre;
    }

    public String getCatDescripcion() {
        return catDescripcion;
    }

    public void setCatDescripcion(String catDescripcion) {
        this.catDescripcion = catDescripcion;
    }

    public String getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(String catEstado) {
        this.catEstado = catEstado;
    }
    
}
