/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.servicio;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.gestock.dao.AlmacenDAO;
import org.zabalburu.daw1.gestock.dao.ProductoDAO;
import org.zabalburu.daw1.gestock.dao.impl.AlmacenDAOImpl;
import org.zabalburu.daw1.gestock.dao.impl.ProductoDAOImpl;
import org.zabalburu.daw1.gestock.excepciones.AlmacenNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.EliminarAlmacenConProductosException;
import org.zabalburu.daw1.gestock.excepciones.ProductoNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.StockInsuficienteException;
import org.zabalburu.daw1.gestock.modelo.Almacen;
import org.zabalburu.daw1.gestock.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public class GestockServicio {

    private static GestockServicio instancia;
    private AlmacenDAO almacenDAO;
    private ProductoDAO productoDAO;

    private GestockServicio() {
        this.almacenDAO = new AlmacenDAOImpl();
        this.productoDAO = new ProductoDAOImpl();
    }

    public static GestockServicio getInstance() {
        if (instancia == null) {
            instancia = new GestockServicio();
        }
        return instancia;
    }

    // =================== ALMACÉN ===================
    public void addAlmacen(Almacen nuevo) {
        almacenDAO.addAlmacen(nuevo);
    }

    public Optional<Almacen> getAlmacen(Integer idAlmacen) {
        return almacenDAO.getAlmacen(idAlmacen);
    }

    public List<Almacen> getAlmacenes() {
        return almacenDAO.getAlmacenes();
    }

    public List<Almacen> getAlmacenesBusqueda(String textoBusqueda) {
        return almacenDAO.getAlmacenesBusqueda(textoBusqueda);
    }

    public void modifyAlmacen(Almacen modificar) throws AlmacenNoEncontradoException {
        almacenDAO.modifyAlmacen(modificar);
    }

    public void removeAlmacen(Integer idAlmacen) throws EliminarAlmacenConProductosException, AlmacenNoEncontradoException {
        almacenDAO.removeAlmacen(idAlmacen);
    }

    // =================== PRODUCTO ==================
    public void addProducto(Producto nuevo) {
        productoDAO.addProducto(nuevo);
    }

    public Optional<Producto> getProducto(Integer idProducto) {
        return productoDAO.getProducto(idProducto);
    }

    public List<Producto> getProductos() {
        return productoDAO.getProductos();
    }

    public List<Producto> getProductosBusqueda(String textoBusqueda) {
        return productoDAO.getProductosBusqueda(textoBusqueda);
    }

    public void modifyProducto(Producto modificar) throws StockInsuficienteException, ProductoNoEncontradoException {
        productoDAO.modifyProducto(modificar);
    }

    public void removeProducto(Integer idProducto) throws ProductoNoEncontradoException {
        productoDAO.removeProducto(idProducto);
    }
}
