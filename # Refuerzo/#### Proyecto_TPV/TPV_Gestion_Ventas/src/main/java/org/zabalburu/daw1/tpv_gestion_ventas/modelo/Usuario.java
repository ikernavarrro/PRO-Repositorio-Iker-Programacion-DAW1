/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.modelo;

import java.util.Objects;
import org.zabalburu.daw1.tpv_gestion_ventas.util.RangoUsuario;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Usuario {
    
    //private final static int RANGO_ADMIN = 1;
    //private final static int RANGO_EMPLEADO = 0;
    //private int rango;
    
    private Integer id;
    private RangoUsuario rango;
    private String correoElectronico;
    private String hash;

    //CONSTRUCTOR
    public Usuario(Integer id, String correoElectronico, String hash) {
        this.id = id;
        this.correoElectronico = correoElectronico;
        this.hash = hash;
    }

    public Usuario() {
    }

    // GETTERS y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public RangoUsuario getRango() {
        return rango;
    }

    public void setRango(RangoUsuario rango) {
        this.rango = rango;
    }
    
    //EQUALS y HASHCODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }
    
    //TO-STRING
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", correoElectronico=" + correoElectronico + ", hash=" + hash + '}';
    }
    
}
