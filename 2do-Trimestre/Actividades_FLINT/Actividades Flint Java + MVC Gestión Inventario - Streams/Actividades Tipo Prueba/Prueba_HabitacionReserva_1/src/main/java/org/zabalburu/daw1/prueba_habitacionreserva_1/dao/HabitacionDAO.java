/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_habitacionreserva_1.modelo.Habitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface HabitacionDAO {
    public void addHabitacion(Habitacion nueva);
    public Optional<Habitacion> getHabitacion(Integer id);
    public List<Habitacion> getHabitaciones();
    public void modifyHabitacion(Habitacion modificar);
    public void removeHabitacion(Integer id);
}
