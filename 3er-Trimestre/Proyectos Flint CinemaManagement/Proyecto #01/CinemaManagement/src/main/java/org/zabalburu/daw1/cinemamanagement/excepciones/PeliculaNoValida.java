/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.excepciones;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PeliculaNoValida extends Exception {

    public PeliculaNoValida(String motivo) {
        super("Validación FALLIDA - %s".formatted(motivo));
    }

}
