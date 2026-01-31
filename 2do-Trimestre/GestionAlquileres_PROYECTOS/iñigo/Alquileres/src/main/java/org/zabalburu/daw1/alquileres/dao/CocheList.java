/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.dao;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.alquileres.modelo.Coche;

/**
 *
 * @author ichueca
 */
public class CocheList implements CocheDao{
    private static  List<Coche> coches = new ArrayList<>();
    
    @Override
    public Coche nuevoCoche(Coche nuevo) {
        Integer maxId = coches.stream()
            .max((c1,c2) -> c1.getId().compareTo(c2.getId()))
            .map(c -> c.getId())
            .orElse(0);
        nuevo.setId(maxId + 1);
        coches.add(nuevo);
        return nuevo;
    }

    @Override
    public void modificarCoche(Coche modificar) {
        int pos = coches.indexOf(modificar);
        if (pos != -1){
            modificar.setAlquileres(coches.get(pos).getAlquileres());
            coches.set(pos, modificar);
        }
    }

    @Override
    public void eliminarCoche(Integer id) {
        Coche c = new Coche();
        c.setId(id);
        coches.remove(c);
    }

    @Override
    public Coche getCoche(Integer id) {
        /*int i;
        for(i=0; i<coches.size() && 
            !id.equals(coches.get(i).getId())
            ;i++);
        if (i < coches.size()){
            return coches.get(i);
        } else {
            return null;
        }*/
        return coches.stream()
              .filter(c -> c.getId().equals(id))
              .findAny()
              .orElse(null);
    }

    @Override
    public List<Coche> getCoches() {
        return coches.stream()
               .sorted((c1,c2) -> c1.getMatricula().compareToIgnoreCase(
               c2.getMatricula()))
               .toList();
    }

    @Override
    public List<Coche> getCochesModelo(String modelo) {
        return coches.stream()
            .filter(c -> c.getModelo().equalsIgnoreCase(modelo))
            .sorted((c1,c2) -> c1.getMatricula().compareToIgnoreCase(
               c2.getMatricula()))
            .toList();
    }
    
}
