/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.modelo;

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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Pedido {
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;
    @ToString.Include
    private String descripcion;
    private long cantidad;
    private Double precioUnitario;
    private LocalDate fechaPedido;
    private Cliente cliente;
    private boolean pagado;
    
    public double getImporte(){
        return this.cantidad * this.precioUnitario;
    }
    
}
