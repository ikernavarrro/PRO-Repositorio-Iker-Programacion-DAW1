/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_habitacionreserva_1.dao.HabitacionDAO;
import org.zabalburu.daw1.prueba_habitacionreserva_1.dao.ReservaDAO;
import org.zabalburu.daw1.prueba_habitacionreserva_1.dao.impl.HabitacionDAOImpl;
import org.zabalburu.daw1.prueba_habitacionreserva_1.dao.impl.ReservaDAOImpl;
import org.zabalburu.daw1.prueba_habitacionreserva_1.modelo.Habitacion;
import org.zabalburu.daw1.prueba_habitacionreserva_1.modelo.Reserva;
import org.zabalburu.daw1.prueba_habitacionreserva_1.util.EstadoReserva;
import org.zabalburu.daw1.prueba_habitacionreserva_1.util.TipoHabitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class HotelServicio {

    private static HotelServicio instancia;
    private HabitacionDAO habitacionDAO;
    private ReservaDAO reservaDAO;

    private HotelServicio() {
        this.habitacionDAO = new HabitacionDAOImpl();
        this.reservaDAO = new ReservaDAOImpl();
        inicializarDatos();
    }

    public static HotelServicio getInstance() {
        if (instancia == null) {
            instancia = new HotelServicio();
        }
        return instancia;
    }

    private void inicializarDatos() {
        java.util.Random rnd = new java.util.Random();

        // 1. Crear 8 Habitaciones
        TipoHabitacion[] tipos = TipoHabitacion.values();
        for (int i = 1; i <= 8; i++) {
            Habitacion h = new Habitacion();
            h.setNumHabitacion(100 + i);
            // Precio aleatorio entre 50 y 150
            h.setPrecioNoche(50.0 + (100.0 * rnd.nextDouble()));
            h.setTipo(tipos[rnd.nextInt(tipos.length)]);
            h.setReservas(new ArrayList<>());
            habitacionDAO.addHabitacion(h);
        }

        List<Habitacion> todasHabitaciones = habitacionDAO.getHabitaciones();
        EstadoReserva[] estados = EstadoReserva.values();
        String[] nombres = {"Iker", "Ainhoa", "Mikel", "Lucia", "Jon", "Ane", "Pepe", "Elena", "Xabi", "Sara"};

        // 2. Crear 25 Reservas aleatorias
        for (int i = 0; i < 25; i++) {
            Reserva r = new Reserva();
            r.setNombreCliente(nombres[rnd.nextInt(nombres.length)] + " " + (i + 1));

            // Fecha aleatoria entre hace 10 días y dentro de 20 días
            LocalDate fechaIni = LocalDate.now().plusDays(rnd.nextInt(30) - 10);
            r.setFechaEntrada(fechaIni);
            r.setTotalDias(rnd.nextInt(5) + 1); // Entre 1 y 5 días
            r.setEstado(estados[rnd.nextInt(estados.length)]);

            // Asignar una de las 8 habitaciones aleatoriamente
            Habitacion hAsignada = todasHabitaciones.get(rnd.nextInt(todasHabitaciones.size()));
            r.setHabitacion(hAsignada);

            // Calcular campos derivados
            r.calcularFechaSalida();
            r.setImporteTotal(r.calcularImporteTotal());

            // Guardar (el DAO ya se encarga de añadir la reserva a la lista de la habitación)
            reservaDAO.addReserva(r);
        }
    }

    // ================== MÉTODOS HABITACION ==================
    public void addHabitacion(Habitacion nueva) {
        habitacionDAO.addHabitacion(nueva);
    }

    public Optional<Habitacion> getHabitacion(Integer id) {
        return habitacionDAO.getHabitacion(id);
    }

    public List<Habitacion> getHabitaciones() {
        return habitacionDAO.getHabitaciones();
    }

    public void modifyHabitacion(Habitacion modificar) {
        habitacionDAO.modifyHabitacion(modificar);
    }

    public void removeHabitacion(Integer id) {
        habitacionDAO.removeHabitacion(id);
    }

    // ================== MÉTODOS RESERVA ==================
    public void addReserva(Reserva nueva) {
        reservaDAO.addReserva(nueva);
    }

    public Optional<Reserva> getReserva(Integer id) {
        return reservaDAO.getReserva(id);
    }

    public List<Reserva> getReservas() {
        return reservaDAO.getReservas();
    }

    public void modifyReserva(Reserva modificar) {
        reservaDAO.modifyReserva(modificar);
    }

    public void removeReserva(Integer id) {
        reservaDAO.removeReserva(id);
    }

    // ================== MÉTODOS EXTRA ==================
    public List<Reserva> getReservasEstado(EstadoReserva estado) {
        return reservaDAO.getReservas().stream()
                .filter(r -> r.getEstado() == estado)
                .toList();
    }

    public Double getIngresosTotalesHabitacion(Integer idHabitacion) {
        Habitacion h = habitacionDAO.getHabitacion(idHabitacion).orElse(null);
        if (h != null) {
            return h.getReservas().stream()
                    .mapToDouble(r -> r.getImporteTotal())
                    .sum();
        } else {
            return 0.0;
        }
    }

    public List<Habitacion> getHabitacionesDisponiblesFecha(LocalDate fecha) {
        return habitacionDAO.getHabitaciones().stream()
                .filter(h -> h.getReservas().stream()
                .noneMatch(r -> r.getEstado() != EstadoReserva.CANCELADA && !fecha.isBefore(r.getFechaEntrada()) && fecha.isBefore(r.getFechaSalida())))
                .toList();
    }

}
