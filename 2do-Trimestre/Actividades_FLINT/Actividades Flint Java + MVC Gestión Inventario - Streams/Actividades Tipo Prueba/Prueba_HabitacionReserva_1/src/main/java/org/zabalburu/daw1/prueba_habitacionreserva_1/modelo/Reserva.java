/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_habitacionreserva_1.modelo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.zabalburu.daw1.prueba_habitacionreserva_1.util.EstadoReserva;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Reserva {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;
    private String nombreCliente;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Integer totalDias;
    @ToString.Include
    private EstadoReserva estado;
    private Double importeTotal;
    @ToString.Include
    private Habitacion habitacion;

    public Double calcularImporteTotal() {
        Double total = 0.0;
        if (habitacion != null) {
            total = this.habitacion.getPrecioNoche() * this.totalDias;
        }
        return total;
    }

    public void calcularFechaSalida() {
        if (fechaEntrada != null && fechaSalida == null) {
            this.fechaSalida = this.fechaEntrada.plusDays(totalDias);
        }
    }
}
