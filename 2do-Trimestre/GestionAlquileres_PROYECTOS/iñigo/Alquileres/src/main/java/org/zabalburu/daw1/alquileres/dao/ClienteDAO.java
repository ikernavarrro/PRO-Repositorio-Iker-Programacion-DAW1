/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.dao;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.alquileres.modelo.Cliente;

/**
 *
 * @author ichueca
 */
public class ClienteDAO {
    private static List<Cliente> clientes = new ArrayList();
    
    public Cliente getCliente(Integer id){
        return clientes.stream()
               .filter(c -> c.id().equals(id))
               .findAny()
                .orElse(null);
                
    }
    
    public List<Cliente> getClientes(){
        return clientes;
    }
    
}
