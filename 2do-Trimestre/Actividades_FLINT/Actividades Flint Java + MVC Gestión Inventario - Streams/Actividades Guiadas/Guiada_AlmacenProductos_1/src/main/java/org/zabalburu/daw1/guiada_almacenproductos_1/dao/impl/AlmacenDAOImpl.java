/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.guiada_almacenproductos_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.guiada_almacenproductos_1.dao.AlmacenDAO;
import org.zabalburu.daw1.guiada_almacenproductos_1.modelo.Almacen;

/**
 *
 * @author Iker Navarro Pérez
 */
public class AlmacenDAOImpl implements AlmacenDAO {

    private static List<Almacen> almacenes = new ArrayList<>();
    
    @Override
    public void addAlmacen(Almacen nuevo) {
        Integer idMax = almacenes.stream()
            .max((a1,a2) -> a1.getId().compareTo(a2.getId()))
            .map(p -> p.getId())
            .orElse(0);
        nuevo.setId(idMax + 1);
        almacenes.add(nuevo);
    }

    @Override
    public Optional<Almacen> getAlmacen(Integer id) {
        return almacenes.stream()
              .filter(a -> a.getId().equals(id))
              .findFirst();
    }

    @Override
    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    @Override
    public void modifyAlmacen(Almacen modificar) {
        int pos = almacenes.indexOf(modificar);
        if (pos != -1){
            almacenes.set(pos, modificar);
        }
    }

    @Override
    public void removeAlmacen(Integer id) {
        Almacen a = getAlmacen(id).orElse(null);
        a.setId(id);
        almacenes.remove(a);
    }
    
}
