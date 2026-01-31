/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flinttiendaproducto_1.servicio;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flinttiendaproducto_1.dao.ProductoDAO;
import org.zabalburu.daw1.flinttiendaproducto_1.dao.TiendaDAO;
import org.zabalburu.daw1.flinttiendaproducto_1.dao.impl.ProductoIMPL;
import org.zabalburu.daw1.flinttiendaproducto_1.dao.impl.TiendaIMPL;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Producto;
import org.zabalburu.daw1.flinttiendaproducto_1.modelo.Tienda;
import org.zabalburu.daw1.flinttiendaproducto_1.util.Categoria;

/**
 *
 * @author Iker Navarro Pérez
 */
public class TiendaServicio {

    private TiendaDAO tiendaDAO;
    private ProductoDAO productoDAO;

    public TiendaServicio() {
        this.tiendaDAO = new TiendaIMPL();
        this.productoDAO = new ProductoIMPL();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // TIENDA 1: Electrónica Plus
        Tienda tienda1 = new Tienda(null, "Electrónica Plus", "Calle Mayor 10, Madrid", "91-555-0001", new ArrayList<>());
        tiendaDAO.addTienda(tienda1);

        // TIENDA 2: Ropa & Moda
        Tienda tienda2 = new Tienda(null, "Ropa & Moda", "Paseo de Gracia 25, Barcelona", "93-555-0002", new ArrayList<>());
        tiendaDAO.addTienda(tienda2);

        // TIENDA 3: Hogar Confort
        Tienda tienda3 = new Tienda(null, "Hogar Confort", "Plaza Mayor 5, Valencia", "96-555-0003", new ArrayList<>());
        tiendaDAO.addTienda(tienda3);

        // PRODUCTOS TIENDA 1 (Electrónica) - 10 productos
        productoDAO.addProducto(new Producto(null, "Laptop Dell", "Laptop 15 pulgadas i7", 899.99, 15, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Mouse Logitech", "Mouse inalámbrico", 29.99, 50, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Teclado Mecánico", "Teclado RGB", 79.99, 25, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Monitor LG 27\"", "Monitor 4K", 349.99, 8, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Auriculares Sony", "Auriculares noise-cancelling", 199.99, 20, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Webcam HD", "Webcam 1080p", 59.99, 30, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Hub USB", "Hub 7 puertos", 39.99, 40, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Cable HDMI", "Cable HDMI 2.1", 14.99, 100, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Adaptador USB-C", "Adaptador múltiple", 24.99, 60, Categoria.ELECTRONICA, tienda1));
        productoDAO.addProducto(new Producto(null, "Funda Laptop", "Funda protectora 15\"", 34.99, 45, Categoria.ELECTRONICA, tienda1));

        // PRODUCTOS TIENDA 2 (Ropa) - 10 productos
        productoDAO.addProducto(new Producto(null, "Camiseta Básica", "Camiseta 100% algodón", 19.99, 80, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Pantalón Vaquero", "Pantalón azul oscuro", 49.99, 40, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Sudadera Hoodie", "Sudadera gris con capucha", 39.99, 35, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Chaqueta Denim", "Chaqueta vaquera clásica", 69.99, 20, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Vestido Negro", "Vestido elegante", 79.99, 15, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Zapatillas Deportivas", "Zapatillas running", 89.99, 25, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Calcetines Pack", "Pack 5 pares", 12.99, 100, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Cinturón Cuero", "Cinturón marrón", 34.99, 30, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Gorro Invierno", "Gorro lana merino", 24.99, 50, Categoria.ROPA, tienda2));
        productoDAO.addProducto(new Producto(null, "Bufanda Larga", "Bufanda cachemira", 44.99, 22, Categoria.ROPA, tienda2));

        // PRODUCTOS TIENDA 3 (Hogar) - 10 productos
        productoDAO.addProducto(new Producto(null, "Almohada Memory", "Almohada viscoelástica", 59.99, 30, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Sábanas 100% Algodón", "Juego sábanas 150x200", 49.99, 25, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Edredón Nórdico", "Edredón 220x240", 89.99, 15, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Lámpara LED", "Lámpara de escritorio", 34.99, 40, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Espejo Pared", "Espejo decorativo", 44.99, 20, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Cortinas Blackout", "Cortinas opacas", 54.99, 18, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Alfombra Persa", "Alfombra 200x300", 199.99, 5, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Cuadro Decorativo", "Cuadro moderno", 39.99, 35, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Maceta Cerámica", "Maceta grande", 24.99, 50, Categoria.HOGAR, tienda3));
        productoDAO.addProducto(new Producto(null, "Espejo Baño", "Espejo con luz LED", 79.99, 12, Categoria.HOGAR, tienda3));
    }

    // Gestión Tienda
    public Tienda addTienda(String nombre, String ubicacion, String telefono, List<Producto> productos) {
        Tienda t = new Tienda(null, nombre, ubicacion, telefono, productos);
        return tiendaDAO.addTienda(t);
    }

    public Tienda getTienda(Integer id) {
        return tiendaDAO.getTienda(id);
    }

    public List<Tienda> getTiendas() {
        return tiendaDAO.getTiendas();
    }

    public Tienda modifyTienda(Tienda modificar) {
        return tiendaDAO.modifyTienda(modificar);
    }

    public Tienda deleteTienda(Integer id) {
        return tiendaDAO.deleteTienda(id);
    }

    // Gestión Producto
    public Producto addProducto(String nombre, String descripcion, Double precio, int stock, Categoria categoria, Tienda tienda) {
        if (tienda != null) {
            Producto p = new Producto(null, nombre, descripcion, precio, stock, categoria, tienda);
            return productoDAO.addProducto(p);
        } else {
            return null;
        }
    }

    public Producto getProducto(Integer id) {
        return productoDAO.getProducto(id);
    }

    public List<Producto> getProductos() {
        return productoDAO.getProductos();
    }

    public List<Producto> getProductosTienda(Integer idTienda) {
        return productoDAO.getProductosTienda(idTienda);
    }

    public Producto modifyProducto(Producto modificar) {
        return productoDAO.modifyProducto(modificar);
    }

    public Producto deleteProducto(Integer id) {
        return productoDAO.deleteProducto(id);
    }
}
