/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_gestionbanco_1.modelo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.zabalburu.daw1.prueba_gestionbanco_1.util.TipoCuentaBancaria;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class CuentaBancaria {
    @EqualsAndHashCode.Include
    private Integer id;
    @ToString.Include
    private String nroCuenta;
    private Double saldo;
    private TipoCuentaBancaria tipo;
    private LocalDate fechaApertura;
    private Cliente titular;
    
}
