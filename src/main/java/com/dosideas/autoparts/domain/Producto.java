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
@Table(name = "producto")
public class Producto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private int prodId;

    @Column(name = "prod_nombre")
    private String prodNombre;

    @Column(name = "prod_descripcion")
    private String prodDescripcion;

    @Column(name = "prod_stock")
    private int prodStock;

    @Column(name = "prod_precio_compra")
    private double prodPrecioCompra;

    @Column(name = "prod_precio_venta")
    private double prodPrecioVenta;

    @Column(name = "`prod_imagenUrl`")
    private String prodImagenUrl;

    @Column(name = "prov_id")
    private int provId;

    @Column(name = "cat_id")
    private int catId;

    // Constructor completo
    public Producto(int prodId, String prodNombre, String prodDescripcion, int prodStock, double prodPrecioCompra, double prodPrecioVenta, String prodImagenUrl, int provId, int catId) {
        this.prodId = prodId;
        this.prodNombre = prodNombre;
        this.prodDescripcion = prodDescripcion;
        this.prodStock = prodStock;
        this.prodPrecioCompra = prodPrecioCompra;
        this.prodPrecioVenta = prodPrecioVenta;
        this.prodImagenUrl = prodImagenUrl;
        this.provId = provId;
        this.catId = catId;
    }

    // Constructor vacío
    public Producto() {
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdDescripcion() {
        return prodDescripcion;
    }

    public void setProdDescripcion(String prodDescripcion) {
        this.prodDescripcion = prodDescripcion;
    }

    public int getProdStock() {
        return prodStock;
    }

    public void setProdStock(int prodStock) {
        this.prodStock = prodStock;
    }

    public double getProdPrecioCompra() {
        return prodPrecioCompra;
    }

    public void setProdPrecioCompra(double prodPrecioCompra) {
        this.prodPrecioCompra = prodPrecioCompra;
    }

    public double getProdPrecioVenta() {
        return prodPrecioVenta;
    }

    public void setProdPrecioVenta(double prodPrecioVenta) {
        this.prodPrecioVenta = prodPrecioVenta;
    }

    public String getProdImagenUrl() {
        return prodImagenUrl;
    }

    public void setProdImagenUrl(String prodImagenUrl) {
        this.prodImagenUrl = prodImagenUrl;
    }

    public int getProvId() {
        return provId;
    }

    public void setProvId(int provId) {
        this.provId = provId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

}
