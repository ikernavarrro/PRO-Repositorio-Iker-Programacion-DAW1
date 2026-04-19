/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.gestion_hoteles.dao.HabitacionDAO;
import org.zabalburu.daw1.gestion_hoteles.model.Habitacion;
import org.zabalburu.daw1.gestion_hoteles.model.Hotel;
import org.zabalburu.daw1.gestion_hoteles.util.EstadoHabitacion;
import org.zabalburu.daw1.gestion_hoteles.util.TipoHabitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class HabitacionDAOImpl implements HabitacionDAO {

    @Override
    public void addHabitacion(EntityManager em, Habitacion nueva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Habitacion getHabitacion(EntityManager em, Integer idHabitacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Habitacion> getHabitaciones(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Habitacion> getHabitacionesFiltro(EntityManager em, Hotel hotel, EstadoHabitacion estado, TipoHabitacion tipo, Integer capacidad, Double precioMax, Double precioMin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifyHabitacion(EntityManager em, Habitacion modificar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeHabitacion(EntityManager em, Integer idHabitacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
