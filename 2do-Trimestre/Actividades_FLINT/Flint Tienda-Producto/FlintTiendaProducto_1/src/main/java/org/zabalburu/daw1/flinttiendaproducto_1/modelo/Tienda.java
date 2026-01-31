/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flinttiendaproducto_1.modelo;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Tienda {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private List<Producto> productos;

    public Tienda() {
    }

    public Tienda(Integer id, String nombre, String ubicacion, String telefono, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.productos = productos;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Tienda other = (Tienda) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Tienda{" + "id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono + ", productos=" + productos + '}';
    }
}
