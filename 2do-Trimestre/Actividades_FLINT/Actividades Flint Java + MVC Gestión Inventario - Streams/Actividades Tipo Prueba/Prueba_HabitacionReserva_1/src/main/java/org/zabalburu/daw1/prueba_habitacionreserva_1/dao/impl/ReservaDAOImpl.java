/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_habitacionreserva_1.dao.ReservaDAO;
import org.zabalburu.daw1.prueba_habitacionreserva_1.modelo.Reserva;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ReservaDAOImpl implements ReservaDAO {

    private static List<Reserva> reservas = new ArrayList<>();

    @Override
    public void addReserva(Reserva nueva) {
        Integer idMax = reservas.stream()
                .max((r1, r2) -> r1.getId().compareTo(r2.getId()))
                .map(r -> r.getId())
                .orElse(0);
        nueva.setId(idMax + 1);
        nueva.getHabitacion().getReservas().add(nueva);
        reservas.add(nueva);
    }

    @Override
    public Optional<Reserva> getReserva(Integer id) {
        return reservas.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public void modifyReserva(Reserva modificar) {
        int pos = reservas.indexOf(modificar);
        if (pos != -1) {
            reservas.set(pos, modificar);
        }
    }

    @Override
    public void removeReserva(Integer id) {
        Reserva r = getReserva(id).orElse(null);
        if (r != null) {
            r.getHabitacion().getReservas().remove(r);
            reservas.remove(r);
        }
    }
}
