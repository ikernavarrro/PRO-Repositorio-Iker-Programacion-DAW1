/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.gestock.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.gestock.excepciones.AlmacenNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.EliminarAlmacenConProductosException;
import org.zabalburu.daw1.gestock.excepciones.ProductoNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.StockInsuficienteException;
import org.zabalburu.daw1.gestock.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface ProductoDAO {
    public void addProducto(Producto nuevo);

    public Optional<Producto> getProducto(Integer idProducto);

    public List<Producto> getProductos();

    public List<Producto> getProductosBusqueda(String textoBusqueda);

    public void modifyProducto(Producto modificar)throws StockInsuficienteException,ProductoNoEncontradoException;

    public void removeProducto(Integer idProducto) throws ProductoNoEncontradoException;
}
