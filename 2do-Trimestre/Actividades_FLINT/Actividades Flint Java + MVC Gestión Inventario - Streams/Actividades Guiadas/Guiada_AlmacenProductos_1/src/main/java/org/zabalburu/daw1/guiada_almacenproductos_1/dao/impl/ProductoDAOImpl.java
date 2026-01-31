/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.guiada_almacenproductos_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.guiada_almacenproductos_1.dao.ProductoDAO;
import org.zabalburu.daw1.guiada_almacenproductos_1.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ProductoDAOImpl implements ProductoDAO {
    
    private static List<Producto> productos = new ArrayList<>();
    
    @Override
    public void addProducto(Producto nuevo) {
        Integer idMax = productos.stream()
            .max((p1,p2) -> p1.getId().compareTo(p2.getId()))
            .map(p -> p.getId())
            .orElse(0);
        nuevo.setId(idMax + 1);
        nuevo.getAlmacen().getProductos().add(nuevo);
        productos.add(nuevo);
    }

    @Override
    public Optional<Producto> getProducto(Integer id) {
        return productos.stream()
              .filter(p -> p.getId().equals(id))
              .findFirst();
    }

    @Override
    public List<Producto> getProductos() {
         return productos;
    }

    @Override
    public void modifyProducto(Producto modificar) {
        int pos = productos.indexOf(modificar);
        if (pos != -1){
            productos.set(pos, modificar);
        }
    }

    @Override
    public void removeProducto(Integer id) {
        Producto p = getProducto(id).orElse(null);
        p.getAlmacen().getProductos().remove(p);
        productos.remove(p);
    }
    
}
