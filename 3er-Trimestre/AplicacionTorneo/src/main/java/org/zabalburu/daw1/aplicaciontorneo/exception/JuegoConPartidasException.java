/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.exception;

import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;

/**
 *
 * @author Iker Navarro Pérez
 */
public class JuegoConPartidasException extends Exception {
    public JuegoConPartidasException(Juego j) {
        super("El juego %s no se puede eliminar, tiene partidas registradas.".formatted(j.getTitulo()));
    }
}
