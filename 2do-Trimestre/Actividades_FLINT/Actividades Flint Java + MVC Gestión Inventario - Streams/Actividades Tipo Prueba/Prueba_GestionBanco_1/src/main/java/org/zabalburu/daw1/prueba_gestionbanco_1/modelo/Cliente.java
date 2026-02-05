/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_gestionbanco_1.modelo;

import java.time.LocalDate;
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
    private String nroTelefono;
    private LocalDate fechaNacimiento;
    private LocalDate fechaAlta;
    private List<CuentaBancaria> cuentasBancarias = new ArrayList<>();
    
}
