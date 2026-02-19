/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.dao;

import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;

/**
 *
 * @author Iker Navarr Pérez
 */
public interface JugadorDAO {
    public Jugador addJugador(Jugador nuevo);
    public Jugador getJugador(Integer id);
    public List<Jugador> getJugadores();
    public void modifyJugador(Jugador modificar);
    public void removeJugador(Integer id);
}
