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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient; 


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

    @ManyToOne
    @JoinColumn(name = "prov_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Categoria categoria;
 
    

    // Constructor completo
    public Producto(int prodId, String prodNombre,String prodDescripcion,int prodStock,double prodPrecioCompra,
                    double prodPrecioVenta,String prodImagenUrl,Proveedor proveedor,Categoria categoria){
    this.prodId = prodId;
    this.prodNombre = prodNombre;
    this.prodDescripcion = prodDescripcion;
    this.prodStock = prodStock;
    this.prodPrecioCompra = prodPrecioCompra;
    this.prodPrecioVenta = prodPrecioVenta;
    this.prodImagenUrl = prodImagenUrl;
    this.proveedor = proveedor;
    this.categoria = categoria;
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

    // getters y setters para usar el objeto Proveedor
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
     // getters y setters para usar el objeto Categorias
    public Categoria getCategoria() {
        return categoria; 
    }
    
    public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
    }
    
  }
