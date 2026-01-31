/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package org.zabalburu.daw1.alquileres.modelo;

/**
 *
 * @author ichueca
 */
public record Cliente(Integer id, String nombre) {

    @Override
    public String toString() {
        return  nombre;
    }
    
}
