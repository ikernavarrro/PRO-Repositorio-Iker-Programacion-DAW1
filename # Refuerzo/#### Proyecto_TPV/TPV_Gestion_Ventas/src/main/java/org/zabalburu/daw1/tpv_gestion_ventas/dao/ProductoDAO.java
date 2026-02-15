/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.dao;

import java.util.List;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ProductoDAO {
    public Producto addProducto(Producto p);
    public Producto getProducto(Integer id);
    public List<Producto> getProductos();
    public Producto modifyProducto(Producto p);
    public Producto deleteProducto(Integer id);
}
