/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.guiada_almacenproductos_1.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.guiada_almacenproductos_1.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ProductoDAO {
    
     void addProducto(Producto nuevo);
    Optional<Producto> getProducto(Integer id);
    List<Producto> getProductos();
    void modifyProducto(Producto modificar);
    void removeProducto(Integer id);
    
}
