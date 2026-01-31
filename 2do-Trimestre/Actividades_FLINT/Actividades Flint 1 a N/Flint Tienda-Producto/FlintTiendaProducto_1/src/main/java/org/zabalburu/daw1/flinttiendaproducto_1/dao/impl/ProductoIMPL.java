/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flinttiendaproducto_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flinttiendaproducto_1.dao.ProductoDAO;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ProductoIMPL implements ProductoDAO {
    
    private static List<Producto> productos;

    public ProductoIMPL() {
        this.productos = new ArrayList<>();
    }
    
    @Override
    public Producto addProducto(Producto nuevo) {
        if (nuevo != null) {
            Integer id = 1;
            if (!productos.isEmpty()) {
                id = productos.get(productos.size() - 1).getId() + 1;
            }
            nuevo.setId(id);
            productos.add(nuevo);
            return nuevo;
        } else {
            return null;
        }
    }

    @Override
    public Producto getProducto(Integer id) {
        int i;
        for (i = 0; i < productos.size() && productos.get(i).getId() != id; i++);
        if (i < productos.size()) {
            return productos.get(i);
        } else {
            return null;
        }
    }

    @Override
    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public List<Producto> getProductosTienda(Integer idTienda) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getTienda() != null && producto.getTienda().getId().equals(idTienda)) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    @Override
    public Producto modifyProducto(Producto modificar) {
        int pos = productos.indexOf(modificar);
        if (pos != -1) {
            productos.set(pos, modificar);
            return modificar;
        } else {
            return null;
        }
    }

    @Override
    public Producto deleteProducto(Integer id) {
        Producto p = new Producto();
        p.setId(id);
        int pos = productos.indexOf(p);
        if (pos != -1) {
            productos.remove(pos);
            return p;
        } else {
            return null;
        }
    }
}
