/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.modelo;

import java.util.ArrayList;
import java.util.List;
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
public class Coche {
    @EqualsAndHashCode.Include
    private Integer id;
    
    private String marca;
    private String modelo;
    private String matricula;
    private String color;
    
    private Double costeDia;
    
    private List<Alquiler> alquileres = new ArrayList<>();

    @Override
    public String toString() {
        return "Coche{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + ", color=" + color + ", costeDia=" + costeDia + '}';
    }
    
}
