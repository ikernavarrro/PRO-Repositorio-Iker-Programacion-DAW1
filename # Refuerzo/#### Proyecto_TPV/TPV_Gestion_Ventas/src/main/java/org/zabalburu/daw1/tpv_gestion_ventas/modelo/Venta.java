/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.modelo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Venta {

    private Integer id;
    private Date fecha;
    private List<LineaVenta> productos;

    //CONSTRUCTOR
    public Venta(Integer id, Date fecha, List<LineaVenta> productos) {
        this.id = id;
        this.fecha = fecha;
        this.productos = productos;
    }

    public Venta() {
    }

    // GETTERS y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<LineaVenta> getProductos() {
        return productos;
    }

    public void setProductos(List<LineaVenta> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        if (productos != null && !productos.isEmpty()) {
            Double total = 0.0;
            for (LineaVenta producto : productos) {
                total += producto.getSubTotal();
            }
            return total;
        } else {
            return 0.0;
        }
    }

    public Double getTotalConIVA() {
        if (productos != null && !productos.isEmpty()) {
            Double totalConIVA = 0.0;
            for (LineaVenta producto : productos) {
                totalConIVA += producto.getSubTotalConIVA();
            }
            return totalConIVA;
        } else {
            return 0.0;
        }
    }

    //EQUALS y HASHCODE
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Venta other = (Venta) obj;
        return Objects.equals(this.id, other.id);
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", fecha=" + fecha + ", productos=" + productos + '}';
    }
}
