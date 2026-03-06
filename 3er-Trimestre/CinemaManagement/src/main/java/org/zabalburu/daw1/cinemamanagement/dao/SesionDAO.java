/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.dao;

import java.util.List;
import org.zabalburu.daw1.cinemamanagement.modelo.Sesion;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface SesionDAO {

    public Sesion addSesion(Sesion nueva);

    public Sesion getSesion(Integer id);

    public List<Sesion> getSesiones();

    public void modifySesion(Sesion modificar);

    public void removeSesion(Integer id);
}
