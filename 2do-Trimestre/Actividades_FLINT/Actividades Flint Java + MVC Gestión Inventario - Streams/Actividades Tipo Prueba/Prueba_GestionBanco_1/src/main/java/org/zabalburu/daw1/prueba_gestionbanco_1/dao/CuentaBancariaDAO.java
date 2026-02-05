/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.prueba_gestionbanco_1.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_gestionbanco_1.modelo.CuentaBancaria;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface CuentaBancariaDAO {
    
    void addCuentaBancaria(CuentaBancaria nueva);
    Optional<CuentaBancaria> getCuentaBancaria(Integer id);
    List<CuentaBancaria> getCuentasBancarias();
    void modifyCuentaBancaria(CuentaBancaria modificar);
    void removeCuentaBancaria(Integer id);
    
}
