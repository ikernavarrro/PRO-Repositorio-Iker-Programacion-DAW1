/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.dao;

import java.sql.Connection;
import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.util.Conexion;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface JuegoDAO {
    public Juego addJuego(Juego nuevo);
    public Juego getJuego(Integer id);
    public List<Juego> getJuegos();
    public void modifyJuego(Juego modificar);
    public void removeJuego(Integer id);
}
