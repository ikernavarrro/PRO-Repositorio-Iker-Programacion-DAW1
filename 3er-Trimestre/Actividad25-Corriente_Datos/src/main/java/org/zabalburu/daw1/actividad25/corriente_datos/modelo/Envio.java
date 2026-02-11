/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad25.corriente_datos.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Envio implements Serializable {
    @EqualsAndHashCode.Include
    private Integer idEnvio;
    private String direccionEntrega;
    private String poblacion;
    private Empleado empleado;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;
    private Double coste;
    
}
