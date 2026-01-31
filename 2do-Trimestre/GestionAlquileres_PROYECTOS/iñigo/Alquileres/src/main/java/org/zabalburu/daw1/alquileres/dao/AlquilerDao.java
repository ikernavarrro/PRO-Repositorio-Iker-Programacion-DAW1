/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.dao;

import java.util.List;
import org.zabalburu.daw1.alquileres.modelo.Alquiler;

/**
 *
 * @author ichueca
 */
public interface AlquilerDao {
    public Alquiler nuevoAlquiler(Alquiler nuevo);
    public void modificarAlquiler(Alquiler modificar);
    public void eliminarAlquiler(Integer id);
    public Alquiler getAlquiler(Integer id);
    public List<Alquiler> getAlquileres();
}
