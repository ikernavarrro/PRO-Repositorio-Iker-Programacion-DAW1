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
        // 1. Crear 4 Almacenes adicionales
        Almacen almacen4 = new Almacen(null, "Logística Levante", "Valencia", 800, new ArrayList<>());
        Almacen almacen5 = new Almacen(null, "Sur Distribución", "Sevilla", 600, new ArrayList<>());
        Almacen almacen6 = new Almacen(null, "Ebro Almacenaje", "Zaragoza", 450, new ArrayList<>());
        Almacen almacen7 = new Almacen(null, "Costa del Sol", "Málaga", 300, new ArrayList<>());

        almacenDAO.addAlmacen(almacen4);
        almacenDAO.addAlmacen(almacen5);
        almacenDAO.addAlmacen(almacen6);
        almacenDAO.addAlmacen(almacen7);

        // 2. Repartir 25 productos entre ellos
        // --- PRODUCTOS ALMACÉN VALENCIA (7 productos) ---
        productoDAO.addProducto(new Producto(null, "Tarjeta Gráfica RTX 4070", "GPU4070", 650.00, 12, almacen4));
        productoDAO.addProducto(new Producto(null, "Procesador i7-13700K", "CPU_I7", 420.00, 25, almacen4));
        productoDAO.addProducto(new Producto(null, "Memoria RAM 32GB DDR5", "RAM032", 115.00, 40, almacen4));
        productoDAO.addProducto(new Producto(null, "Placa Base Z790", "MB_Z790", 210.00, 15, almacen4));
        productoDAO.addProducto(new Producto(null, "SSD NVMe 2TB", "SSD002", 145.00, 30, almacen4));
        productoDAO.addProducto(new Producto(null, "Fuente Alimentación 850W", "PSU850", 125.00, 20, almacen4));
        productoDAO.addProducto(new Producto(null, "Caja ATX Flow", "CASE01", 89.90, 10, almacen4));

        // --- PRODUCTOS ALMACÉN SEVILLA (6 productos) ---
        productoDAO.addProducto(new Producto(null, "Impresora Láser", "IMP001", 199.00, 8, almacen5));
        productoDAO.addProducto(new Producto(null, "Tóner Negro XL", "TON001", 45.00, 50, almacen5));
        productoDAO.addProducto(new Producto(null, "Escáner Documentos", "SCN001", 120.00, 5, almacen5));
        productoDAO.addProducto(new Producto(null, "Papel A4 (500h)", "PAP001", 5.50, 200, almacen5));
        productoDAO.addProducto(new Producto(null, "Router WiFi 6", "NET001", 85.00, 15, almacen5));
        productoDAO.addProducto(new Producto(null, "Cable Ethernet 10m", "CAB010", 12.50, 60, almacen5));

        // --- PRODUCTOS ALMACÉN ZARAGOZA (6 productos) ---
        productoDAO.addProducto(new Producto(null, "Smartphone Android", "PHN001", 450.00, 20, almacen6));
        productoDAO.addProducto(new Producto(null, "Funda Silicona", "PHN_ACC1", 12.00, 100, almacen6));
        productoDAO.addProducto(new Producto(null, "Protector Pantalla", "PHN_ACC2", 8.50, 150, almacen6));
        productoDAO.addProducto(new Producto(null, "Cargador Carga Rápida", "CHG001", 25.00, 45, almacen6));
        productoDAO.addProducto(new Producto(null, "Powerbank 20000mAh", "PBK001", 35.00, 30, almacen6));
        productoDAO.addProducto(new Producto(null, "Adaptador USB-C a HDMI", "ADP001", 19.99, 25, almacen6));

        // --- PRODUCTOS ALMACÉN MÁLAGA (6 productos) ---
        productoDAO.addProducto(new Producto(null, "Altavoces Bluetooth", "SND001", 55.00, 18, almacen7));
        productoDAO.addProducto(new Producto(null, "Micrófono USB", "SND002", 79.00, 12, almacen7));
        productoDAO.addProducto(new Producto(null, "Brazo Articulado Mic", "SND_ACC1", 30.00, 10, almacen7));
        productoDAO.addProducto(new Producto(null, "Filtro Antipop", "SND_ACC2", 15.00, 20, almacen7));
        productoDAO.addProducto(new Producto(null, "Auriculares In-Ear", "AUD002", 29.99, 55, almacen7));
        productoDAO.addProducto(new Producto(null, "Tarjeta de Sonido Ext", "SND003", 99.00, 7, almacen7));
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
