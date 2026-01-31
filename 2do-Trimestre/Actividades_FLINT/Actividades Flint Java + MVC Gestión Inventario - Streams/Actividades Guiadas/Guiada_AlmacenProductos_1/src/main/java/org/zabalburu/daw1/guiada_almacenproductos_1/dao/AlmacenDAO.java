/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.guiada_almacenproductos_1.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.guiada_almacenproductos_1.modelo.Almacen;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface AlmacenDAO {

    void addAlmacen(Almacen nuevo);
    Optional<Almacen> getAlmacen(Integer id);
    List<Almacen> getAlmacenes();
    void modifyAlmacen(Almacen modificar);
    void removeAlmacen(Integer id);
    
}
