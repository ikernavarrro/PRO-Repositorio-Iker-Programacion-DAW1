/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.modelo;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.zabalburu.daw1.cinemamanagement.util.GeneroPelicula;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula {

    @EqualsAndHashCode.Include
    private Integer idPelicula;
    private String titulo;
    private String director;
    private Integer año;
    private Integer duracion;
    private GeneroPelicula genero;
    @ToString.Exclude
    private List<Sesion> sesiones = new ArrayList();
}
