/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.pruebaobj_trimestre2.dao.ClienteDAO;
import org.zabalburu.daw1.pruebaobj_trimestre2.modelo.Cliente;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ClienteDAOImpl implements ClienteDAO {

    private static List<Cliente> clientes = new ArrayList<>();

    @Override
    public void addCliente(Cliente nuevo) {
        Integer idMax = clientes.stream()
                .mapToInt(c -> c.getId())
                .max()
                .orElse(0);
        nuevo.setId(idMax + 1);
        clientes.add(nuevo);
    }

    @Override
    public Optional<Cliente> getCliente(Integer id) {
        return clientes.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public void modifyCliente(Cliente modificar) {
        int pos = clientes.indexOf(modificar);
        if (pos != -1) {
            clientes.set(pos, modificar);
        }
    }

    @Override
    /*
     *Solo elimina el cliente si no tiene pedidos o si tiene todos los pedidos pagados. 
     */
    public void removeCliente(Integer id) throws Exception {
        Cliente c = getCliente(id).orElse(null);
        boolean pedidosPagados = false;
        if (c != null) {
            if (!c.getPedidos().isEmpty()) { // El cliente tiene pedidos en su lista
                pedidosPagados = c.getPedidos().stream()
                        .allMatch(p -> p.isPagado()); //Comprobamos que estén todos pagados, si no es así no eliminamos e cliente y lanzamos excepción.
                if (pedidosPagados) {
                    clientes.remove(c);
                } else {
                    throw new Exception("El cliente tiene pagos pendientes!");
                }
            } else { // Aquí asumimos que el cliente no tiene pedidos.
                clientes.remove(c);
            }
            ;

        }
    }

}
