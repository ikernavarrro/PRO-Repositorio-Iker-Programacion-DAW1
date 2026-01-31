/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.guiada_almacenproductos_1.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.guiada_almacenproductos_1.dao.AlmacenDAO;
import org.zabalburu.daw1.guiada_almacenproductos_1.dao.ProductoDAO;
import org.zabalburu.daw1.guiada_almacenproductos_1.dao.impl.AlmacenDAOImpl;
import org.zabalburu.daw1.guiada_almacenproductos_1.dao.impl.ProductoDAOImpl;
import org.zabalburu.daw1.guiada_almacenproductos_1.modelo.Almacen;
import org.zabalburu.daw1.guiada_almacenproductos_1.modelo.Producto;

/**
 *
 * @author Iker Navarro Pérez
 */
public class InventarioServicio {

    private static InventarioServicio instancia;
    private AlmacenDAO almacenDAO;
    private ProductoDAO productoDAO;
    
    private InventarioServicio() {
        this.almacenDAO = new AlmacenDAOImpl();
        this.productoDAO = new ProductoDAOImpl();
        inicializarDatos();
    }
    
    public static InventarioServicio getInstance() {
        if (instancia == null) {
            instancia = new InventarioServicio();
        }
        return instancia;
    }
    
    private void inicializarDatos() {
        // Crear almacenes
        Almacen almacen1 = new Almacen(null, "Almacén Central", "Madrid", 1000, new ArrayList<>());
        Almacen almacen2 = new Almacen(null, "Almacén Norte", "Barcelona", 500, new ArrayList<>());

        almacenDAO.addAlmacen(almacen1);
        almacenDAO.addAlmacen(almacen2);

        // Crear productos
        Producto p1 = new Producto(null, "Laptop", "LAP001", 899.99, 15, almacen1);
        Producto p2 = new Producto(null, "Mouse", "MOU001", 25.50, 100, almacen1);
        Producto p3 = new Producto(null, "Teclado", "TEC001", 75.00, 50, almacen2);

        productoDAO.addProducto(p1);
        productoDAO.addProducto(p2);
        productoDAO.addProducto(p3);
    }

     // ================== MÉTODOS ALMACEN ==================
    public void addAlmacen(Almacen almacen) {
        almacenDAO.addAlmacen(almacen);
    }

    public Optional<Almacen> getAlmacen(Integer id) {
        return almacenDAO.getAlmacen(id);
    }

    public List<Almacen> getAlmacenes() {
        return almacenDAO.getAlmacenes();
    }

    public void modifyAlmacen(Almacen almacen) {
        almacenDAO.modifyAlmacen(almacen);
    }

    public void removeAlmacen(Integer id) {
        almacenDAO.removeAlmacen(id);
    }

    // ================== MÉTODOS PRODUCTO ==================
    public void addProducto(Producto producto) {
        productoDAO.addProducto(producto);
    }

    public Optional<Producto> getProducto(Integer id) {
        return productoDAO.getProducto(id);
    }

    public List<Producto> getProductos() {
        return productoDAO.getProductos();
    }

    public void modifyProducto(Producto producto) {
        productoDAO.modifyProducto(producto);
    }

    public void removeProducto(Integer id) {
        productoDAO.removeProducto(id);
    }

    // ================== MÉTODOS EXTRA ==================
    public double getValorInventario() {
        return productoDAO.getProductos().stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();
    }

    public List<Producto> getProductosAlmacen(Integer idAlmacen) {
        return productoDAO.getProductos().stream()
                .filter(p -> p.getAlmacen().getId().equals(idAlmacen))
                .toList();
    }

    public List<Producto> getProductosPrecioMax(double precioMaximo) {
        return productoDAO.getProductos().stream()
                .filter(p -> p.getPrecio() <= precioMaximo)
                .toList();
    }
    
    public List<Producto> getProductosPrecioMin(double precioMinimo) {
        return productoDAO.getProductos().stream()
                .filter(p -> p.getPrecio() >= precioMinimo)
                .toList();
    }

    public long countProductosAlmacen(Integer idAlmacen) {
        return productoDAO.getProductos().stream()
                .filter(p -> p.getAlmacen().getId().equals(idAlmacen))
                .count();
    }
}
