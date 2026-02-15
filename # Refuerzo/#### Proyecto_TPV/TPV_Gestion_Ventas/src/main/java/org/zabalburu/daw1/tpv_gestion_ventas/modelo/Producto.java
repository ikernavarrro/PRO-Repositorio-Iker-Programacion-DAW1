/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.modelo;

import java.util.Objects;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Producto {
    private static final Double IMPUESTO_IVA = 0.21;
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stockDisponible;
    private String codigoBarras;
    private String categoria;

    //CONSTRUCTOR
    public Producto(Integer id, String nombre, String descripcion, Double precio, Integer stockDisponible, String codigoBarras, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.codigoBarras = codigoBarras;
        this.categoria = categoria;
    }

    public Producto() {
    }
    
    //GETTERS y SETTERS
    
    public Double getPrecioConIVA() {
        Double importeIva = this.getPrecio() * IMPUESTO_IVA;
        Double precioConIva = this.getPrecio() + importeIva;
        return precioConIva;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    //EQUALS y HASHCODE En base al ID

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.id, other.id);
    }
    
    //ToString
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", stockDisponible=" + stockDisponible + ", codigoBarras=" + codigoBarras + ", categoria=" + categoria + '}';
    }
}
