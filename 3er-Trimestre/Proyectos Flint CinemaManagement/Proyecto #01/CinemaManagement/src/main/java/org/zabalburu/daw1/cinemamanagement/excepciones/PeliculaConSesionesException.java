/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.excepciones;

import org.zabalburu.daw1.cinemamanagement.modelo.Pelicula;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PeliculaConSesionesException extends RuntimeException {

    public PeliculaConSesionesException(Pelicula p) {
        super("La pelicula %s no se puede eliminar, tiene sesiones registradas.".formatted(p.getTitulo()));
    }

}
