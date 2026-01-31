/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.dao;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.alquileres.modelo.Alquiler;

/**
 *
 * @author ichueca
 */
public class AlquilerList implements AlquilerDao{

    private static List<Alquiler> alquileres = new ArrayList<>();
    
    @Override
    public Alquiler nuevoAlquiler(Alquiler nuevo) {
        Integer maxId = alquileres.stream()
                .map(a -> a.getId())
                .max((i1,i2) -> i1.compareTo(i2))
                .orElse(0);
        nuevo.setId(maxId + 1);
        alquileres.add(nuevo);
        nuevo.getCoche().getAlquileres().add(nuevo);
        return nuevo;
    }

    @Override
    public void modificarAlquiler(Alquiler modificar) {
        int pos = alquileres.indexOf(modificar);
        if (pos != -1){
            alquileres.set(pos, modificar);
        }
    }

    @Override
    public void eliminarAlquiler(Integer id) {
        Alquiler a = getAlquiler(id);
        alquileres.remove(a);
        a.getCoche().getAlquileres().remove(a);
    }

    @Override
    public Alquiler getAlquiler(Integer id) {
        return alquileres.stream()
           .filter(a -> a.getId().equals(id))
           .findAny()
           .orElse(null);
    }

    @Override
    public List<Alquiler> getAlquileres() {
        return alquileres.stream()
            .sorted((a1,a2) -> a2.getFechaAlquiler().compareTo(a1.getFechaAlquiler()))
            .toList();
    }
    
}
