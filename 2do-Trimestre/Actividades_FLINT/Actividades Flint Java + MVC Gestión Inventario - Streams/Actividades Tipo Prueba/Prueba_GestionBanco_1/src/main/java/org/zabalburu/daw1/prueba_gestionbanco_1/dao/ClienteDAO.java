/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.prueba_gestionbanco_1.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_gestionbanco_1.modelo.Cliente;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ClienteDAO {
    
    void addCliente(Cliente nuevo);
    Optional<Cliente> getCliente(Integer id);
    List<Cliente> getClientes();
    void modifyCliente(Cliente modificar);
    void removeCliente(Integer id) throws Exception;
    
}
