/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.zabalburu.daw1.gestock.util;

/**
 *
 * @author Iker Navarro Pérez
 */
public enum Categoria {
    ELECTRONICA,
    HOGAR,
    ROPA,
    JUGUETES,
    DEPORTES,
    OFICINA,
    JARDIN,
    MASCOTAS,
    SALUD,
    BELLEZA,
    COMIDA,
    BEBIDA,
    OTROS;

    public String getValueDB() {
        return this.name().toLowerCase();
    }
}
