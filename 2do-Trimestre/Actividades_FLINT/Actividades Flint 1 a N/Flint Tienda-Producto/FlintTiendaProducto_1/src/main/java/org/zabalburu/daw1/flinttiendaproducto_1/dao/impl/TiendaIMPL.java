/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flinttiendaproducto_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flinttiendaproducto_1.dao.TiendaDAO;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Producto;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Tienda;

/**
 *
 * @author Iker Navarro Pérez
 */
public class TiendaIMPL implements TiendaDAO {
    
    private static List<Tienda> tiendas;

    public TiendaIMPL() {
        this.tiendas = new ArrayList<>();
    }
    
    @Override
    public Tienda addTienda(Tienda nueva) {
        if (nueva != null) {
            Integer id = 1;
            if (!tiendas.isEmpty()) {
                id = tiendas.get(tiendas.size() - 1).getId() + 1;
            }
            nueva.setId(id);
            tiendas.add(nueva);
            return nueva;
        } else {
            return null;
        }
    }

    @Override
    public Tienda getTienda(Integer id) {
        int i;
        for (i = 0; i < tiendas.size() && tiendas.get(i).getId() != id; i++);
        if (i < tiendas.size()) {
            return tiendas.get(i);
        } else {
            return null;
        }
    }

    @Override
    public List<Tienda> getTiendas() {
        return tiendas;
    }

    @Override
    public Tienda modifyTienda(Tienda modificar) {
        int pos = tiendas.indexOf(modificar);
        if (pos != -1) {
            tiendas.set(pos, modificar);
            return modificar;
        } else {
            return null;
        }
    }

    @Override
    public Tienda deleteTienda(Integer id) {
        Tienda t = new Tienda();
        t.setId(id);
        int pos = tiendas.indexOf(t);
        if (pos != -1) {
            if (tiendas.get(pos).getProductos() != null) {
                for (Producto producto : tiendas.get(pos).getProductos()) {
                    producto.setTienda(null);
                }
            }
            tiendas.remove(pos);
            return t;
        } else {
            return null;
        }
    }
    
}
