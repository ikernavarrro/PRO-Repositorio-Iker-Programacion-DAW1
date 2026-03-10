/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.modelo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Partida {
    @EqualsAndHashCode.Include
    private Integer id;
    private Juego juego; // FK
    private Jugador gana; // FK
    private Jugador pierde; // FK
    private String duracion;
    private LocalDateTime fecha;
    private Integer puntos;
    
}
