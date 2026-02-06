/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.pruebaobj_trimestre2.modelo.Cliente;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ClienteDAO {

    public void addCliente(Cliente nuevo);

    public Optional<Cliente> getCliente(Integer id);

    public List<Cliente> getClientes();

    public void modifyCliente(Cliente modificar);

    public void removeCliente(Integer id) throws Exception;
}
