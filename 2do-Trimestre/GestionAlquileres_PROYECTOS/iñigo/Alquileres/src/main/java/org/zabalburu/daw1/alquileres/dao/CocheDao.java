/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.alquileres.dao;

import java.util.List;
import org.zabalburu.daw1.alquileres.modelo.Coche;

/**
 *
 * @author ichueca
 */
public interface CocheDao {
    public Coche nuevoCoche(Coche nuevo);
    public void modificarCoche(Coche modificar);
    public void eliminarCoche(Integer id);
    public Coche getCoche(Integer id);
    public List<Coche> getCoches();
    public List<Coche> getCochesModelo(String modelo);
}
