/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.flinttiendaproducto_1.dao;

import java.util.List;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Tienda;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface TiendaDAO {
    public Tienda addTienda(Tienda nueva);
    public Tienda getTienda(Integer id);
    public List<Tienda> getTiendas();
    public Tienda modifyTienda(Tienda modificar);
    public Tienda deleteTienda(Integer id);
}
