/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_habitacionreserva_1.dao.HabitacionDAO;
import org.zabalburu.daw1.prueba_habitacionreserva_1.modelo.Habitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class HabitacionDAOImpl implements HabitacionDAO {

    private static List<Habitacion> habitaciones = new ArrayList<>();

    @Override
    public void addHabitacion(Habitacion nueva) {
        Integer idMax = habitaciones.stream()
                .max((h1, h2) -> h1.getId().compareTo(h2.getId()))
                .map(h -> h.getId())
                .orElse(0);
        nueva.setId(idMax + 1);
        habitaciones.add(nueva);
    }

    @Override
    public Optional<Habitacion> getHabitacion(Integer id) {
        return habitaciones.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @Override
    public void modifyHabitacion(Habitacion modificar) {
        int pos = habitaciones.indexOf(modificar);
        if (pos != -1) {
            habitaciones.set(pos, modificar);
        }
    }

    @Override
    public void removeHabitacion(Integer id) {
        Habitacion h = getHabitacion(id).orElse(null);
        if (h != null) {
            habitaciones.remove(h);
        }
    }
}
