/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.modelo;

import java.util.ArrayList;
import java.util.List;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Cliente {
    @EqualsAndHashCode.Include
    private Integer id;
    @ToString.Include
    private String nombre;
    private String email;
    private String telefono;
    private List<Pedido> pedidos = new ArrayList();
    
    public double getImporteTotal(){
        return this.pedidos.stream()
                .mapToDouble(p -> p.getImporte())
                .sum();
    }
}
