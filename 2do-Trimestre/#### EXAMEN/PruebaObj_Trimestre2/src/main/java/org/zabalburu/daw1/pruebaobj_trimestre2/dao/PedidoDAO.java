/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.pruebaobj_trimestre2.modelo.Pedido;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface PedidoDAO {

    public void addPedido(Pedido nuevo);
    
    public List<Pedido> getPedidos();
    
    public void pagarPedido(Integer id);
}
