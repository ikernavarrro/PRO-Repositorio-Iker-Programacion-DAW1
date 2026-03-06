/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.dao;

import java.util.List;
import org.zabalburu.daw1.cinemamanagement.modelo.Pelicula;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface PeliculaDAO {

    public Pelicula addPelicula(Pelicula nueva);

    public Pelicula getPelicula(Integer id);

    public List<Pelicula> getPeliculas();

    public void modifyPelicula(Pelicula modificar);

    public void removePelicula(Integer id);
}
