/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.modelo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author ichueca
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alquiler {
    @EqualsAndHashCode.Include
    private Integer id;
    
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;
    private Coche coche;
    private Integer dias;
    private Double costeDia;
    private Cliente cliente;
    
    public boolean isDevuelto(){
        return fechaDevolucion != null;
    }
    
    public Double getCosteTotal(){
        /*LocalDate fechaDebeDevolverse = fechaAlquiler.plusDays(dias);
        LocalDate hoy = LocalDate.now();
        int numDias = (int) fechaDebeDevolverse.datesUntil(hoy).count();
        if (numDias > 0){
            return costeDia * dias + numDias * (costeDia * 2);
        } else {
            return costeDia * dias;
        }*/
        return costeDia * dias;
    }
    
    public boolean isReclamable(){
        if (this.fechaDevolucion == null){
            LocalDate fechaDebeDevolverse = fechaAlquiler.plusDays(dias);
            LocalDate hoy = LocalDate.now();
            return hoy.isAfter(fechaDebeDevolverse);
        }
        return false;
    }
}
