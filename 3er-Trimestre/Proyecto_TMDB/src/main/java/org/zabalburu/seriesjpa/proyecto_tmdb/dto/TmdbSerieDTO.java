/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.dto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author DAW1
 */
public record TmdbSerieDTO(
        Integer tmdbId, 
        String titulo,
        String sinopsis,
        LocalDate fechaPrimerAire,
        String posterUrl,
        List<String> generos){
}
