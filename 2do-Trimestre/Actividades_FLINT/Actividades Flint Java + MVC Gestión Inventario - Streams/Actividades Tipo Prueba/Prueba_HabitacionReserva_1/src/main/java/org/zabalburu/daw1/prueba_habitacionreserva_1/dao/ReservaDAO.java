/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_habitacionreserva_1.modelo.Reserva;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ReservaDAO {
    public void addReserva(Reserva nueva);
    public Optional<Reserva> getReserva(Integer id);
    public List<Reserva> getReservas();
    public void modifyReserva(Reserva modificar);
    public void removeReserva(Integer id);
}
