/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.gestion_hoteles.model.Habitacion;
import org.zabalburu.daw1.gestion_hoteles.model.Hotel;
import org.zabalburu.daw1.gestion_hoteles.util.EstadoHabitacion;
import org.zabalburu.daw1.gestion_hoteles.util.TipoHabitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface HabitacionDAO {
    
    public void addHabitacion(EntityManager em, Habitacion nueva);

    public Habitacion getHabitacion(EntityManager em, Integer idHabitacion);

    public List<Habitacion> getHabitaciones(EntityManager em);
    
    public List<Habitacion> getHabitacionesFiltro(EntityManager em, Hotel hotel, EstadoHabitacion estado, TipoHabitacion tipo, Integer capacidad, Double precioMax, Double precioMin);

    public void modifyHabitacion(EntityManager em, Habitacion modificar);

    public void removeHabitacion(EntityManager em, Integer idHabitacion);
    
}
