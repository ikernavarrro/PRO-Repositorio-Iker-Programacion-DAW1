/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.flinttiendaproducto_1.dao;

import java.util.List;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ProductoDAO {
    public Producto addProducto(Producto nuevo);
    public Producto getProducto(Integer id);
    public List<Producto> getProductos();
    public List<Producto> getProductosTienda(Integer idTienda);
    public Producto modifyProducto(Producto modificar);
    public Producto deleteProducto(Integer id);
}
