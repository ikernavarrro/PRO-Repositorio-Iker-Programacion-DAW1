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
public class LineaVenta {
    
    private Integer id;
    private Producto producto;
    private Integer cantidad;

    //CONSTRUCTOR
    public LineaVenta(Integer id, Producto producto, Integer cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public LineaVenta() {
    }

    // GETTERS y SETTERS
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        if (producto != null) {
            Double subTotal = this.producto.getPrecio() * this.cantidad;
            return subTotal;
        } else {
            return 0.0;
        }
    }

    public Double getSubTotalConIVA() {
        if (producto != null) {
            Double subTotalConIVA = this.producto.getPrecioConIVA() * this.cantidad;
            return subTotalConIVA;
        } else {
            return 0.0;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    //EQUALS y HASHCODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final LineaVenta other = (LineaVenta) obj;
        return Objects.equals(this.id, other.id);
    }

    //TOSTRING

    @Override
    public String toString() {
        return "LineaVenta{" + "id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + '}';
    }
    
}
