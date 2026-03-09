/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.exception;

import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;

/**
 *
 * @author DAW1
 */
public class JugadorConPartidasException extends Exception {

    public JugadorConPartidasException(Jugador j) {
        super("El jugador %s no se puede eliminar, tiene partidas registradas.".formatted(j.getNick()));
    }
}
