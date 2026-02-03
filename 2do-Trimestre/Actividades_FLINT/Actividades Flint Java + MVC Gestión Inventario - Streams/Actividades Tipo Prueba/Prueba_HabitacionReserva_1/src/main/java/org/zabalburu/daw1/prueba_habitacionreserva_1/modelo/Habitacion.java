/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.modelo;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.zabalburu.daw1.prueba_habitacionreserva_1.util.TipoHabitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Habitacion {
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;
    @ToString.Include
    private Integer numHabitacion;
    @ToString.Include
    private Double precioNoche;
    @ToString.Include
    private TipoHabitacion tipo;
    private List<Reserva> reservas = new ArrayList<>();
}
