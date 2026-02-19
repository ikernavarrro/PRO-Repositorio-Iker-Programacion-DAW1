/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.dao;

import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Partida;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface PartidaDAO {
    public Partida addPartida(Partida nueva);
    public Partida getPartida(Integer id);
    public List<Partida> getPartidas();
    public void modifyPartida(Partida modificar);
    public void removePartida(Integer id);
}
