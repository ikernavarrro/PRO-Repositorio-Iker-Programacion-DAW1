/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.pruebaobj_trimestre2.dao.PedidoDAO;
import org.zabalburu.daw1.pruebaobj_trimestre2.modelo.Pedido;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PedidoDAOImpl implements PedidoDAO {

    private static List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void addPedido(Pedido nuevo) {
        Integer idMax = pedidos.stream()
                .mapToInt(c -> c.getId())
                .max()
                .orElse(0);
        nuevo.setId(idMax + 1);
        nuevo.getCliente().getPedidos().add(nuevo); // Añadimos el pedido al cliente para garantizar la Bidireccionalidad!
        pedidos.add(nuevo);
    }
    
    @Override
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    @Override
    public void pagarPedido(Integer id) {
        Pedido pagar = pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (pagar != null) {
            pagar.setPagado(true);
            int pos = pedidos.indexOf(pagar);
            if (pos != -1) {
                pedidos.set(pos, pagar);
            }
        }
    }
}
