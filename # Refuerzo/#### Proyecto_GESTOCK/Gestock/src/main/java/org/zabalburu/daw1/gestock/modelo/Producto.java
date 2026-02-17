/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.zabalburu.daw1.gestock.util.Categoria;

/**
 *
 * @author Iker Navarro Pérez
 */
/*@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)*/
public class Producto implements Serializable {
    // @ToString.Include
    // @EqualsAndHashCode.Include
    private Integer idProducto;
    private String codigoBarras;
    // @ToString.Include
    private String descripcion;
    private String marca;
    private Categoria categoria;
    private Double precio;
    private Integer stock;
    private LocalDate fechaEntrada;
    private Almacen almacen;

    public Producto() {
    }

    public Producto(Integer idProducto, String codigoBarras, String descripcion, String marca, Categoria categoria, Double precio, Integer stock, LocalDate fechaEntrada, Almacen almacen) {
        this.idProducto = idProducto;
        this.codigoBarras = codigoBarras;
        this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.fechaEntrada = fechaEntrada;
        this.almacen = almacen;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idProducto);
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
        return Objects.equals(this.idProducto, other.idProducto);
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", codigoBarras=" + codigoBarras + ", descripcion=" + descripcion + ", marca=" + marca + ", categoria=" + categoria + ", precio=" + precio + ", stock=" + stock + ", fechaEntrada=" + fechaEntrada + ", almacen=" + almacen + '}';
    }
}
