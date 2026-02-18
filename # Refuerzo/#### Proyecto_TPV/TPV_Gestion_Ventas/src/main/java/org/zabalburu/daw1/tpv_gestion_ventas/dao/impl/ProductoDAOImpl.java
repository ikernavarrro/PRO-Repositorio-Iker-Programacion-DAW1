/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.tpv_gestion_ventas.dao.ProductoDAO;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ProductoDAOImpl implements ProductoDAO {

    private List<Producto> productos;

    public ProductoDAOImpl() {
        this.productos = new ArrayList<>();
    }

    @Override
    public Producto addProducto(Producto p) {
        if (p != null) {
            Integer id = 1;
            if (!productos.isEmpty()) {
                id = productos.get(productos.size() - 1).getId() + 1;
            }
            p.setId(id);
            productos.add(p);
            return p;
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
    public Producto modifyProducto(Producto p) {
        int pos = productos.indexOf(p);
        if (pos != -1) {
            productos.set(pos, p);
            return p;
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
            return p; // DEVUELVE EL PRODUCTO VACIO, SOLO CON ID PARA MOSTRAR MENSAJE INFORMATIVO
        } else {
            return null;
        }
    }
}
