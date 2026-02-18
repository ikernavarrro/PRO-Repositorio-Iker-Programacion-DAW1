/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.servicio;

import java.util.List;
import org.zabalburu.daw1.tpv_gestion_ventas.dao.ProductoDAO;
import org.zabalburu.daw1.tpv_gestion_ventas.dao.VentaDAO;
import org.zabalburu.daw1.tpv_gestion_ventas.dao.impl.ProductoDAOImpl;
import org.zabalburu.daw1.tpv_gestion_ventas.dao.impl.VentaDAOImpl;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Producto;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Venta;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.LineaVenta;
import java.util.ArrayList;
import java.util.Date;
import org.zabalburu.daw1.tpv_gestion_ventas.excepciones.PagoInsuficienteException;
import org.zabalburu.daw1.tpv_gestion_ventas.excepciones.StockInsuficienteException;

/**
 *
 * @author Iker Navarro Pérez
 */
public class TPV_Servicio {

    private ProductoDAO productoDAO;
    private VentaDAO ventaDAO;

    public TPV_Servicio() {
        this.productoDAO = new ProductoDAOImpl();
        this.ventaDAO = new VentaDAOImpl();
        //DATOS EN MEMORIA AQUÍ MÉTODO inicializarDatos();
        inicializarDatos();
    }

    //==================DATOS-MEMORIA======================
    private void inicializarDatos() {
        // BEBIDAS CALIENTES
        crearProducto("Café Solo", "Café espresso", 1.50, 100, "001", "Bebidas Calientes");
        crearProducto("Café con Leche", "Café con leche", 2.00, 100, "002", "Bebidas Calientes");
        crearProducto("Cappuccino", "Cappuccino italiano", 2.50, 80, "003", "Bebidas Calientes");
        crearProducto("Té", "Té variado", 1.50, 90, "004", "Bebidas Calientes");
        crearProducto("Chocolate Caliente", "Chocolate caliente", 2.00, 70, "005", "Bebidas Calientes");

        // BEBIDAS FRÍAS
        crearProducto("Agua Mineral", "Agua mineral 50cl", 1.00, 150, "006", "Bebidas Frías");
        crearProducto("Refresco Cola", "Refresco cola 33cl", 1.50, 120, "007", "Bebidas Frías");
        crearProducto("Refresco Naranja", "Refresco naranja 33cl", 1.50, 120, "008", "Bebidas Frías");
        crearProducto("Zumo Natural", "Zumo natural 25cl", 2.00, 80, "009", "Bebidas Frías");
        crearProducto("Cerveza", "Cerveza 33cl", 2.50, 100, "010", "Bebidas Frías");
        crearProducto("Vino Tinto", "Vino tinto copa", 3.00, 60, "011", "Bebidas Frías");
        crearProducto("Horchata", "Horchata 25cl", 2.00, 70, "012", "Bebidas Frías");

        // BOCADILLOS
        crearProducto("Bocadillo Jamón", "Bocadillo jamón serrano", 4.50, 50, "013", "Bocadillos");
        crearProducto("Bocadillo Queso", "Bocadillo queso fresco", 3.50, 50, "014", "Bocadillos");
        crearProducto("Bocadillo Tortilla", "Bocadillo tortilla española", 3.50, 40, "015", "Bocadillos");
        crearProducto("Bocadillo Atún", "Bocadillo atún", 4.00, 45, "016", "Bocadillos");
        crearProducto("Bocadillo Camarones", "Bocadillo camarones", 5.00, 30, "017", "Bocadillos");
        crearProducto("Sándwich Mixto", "Sándwich jamón y queso", 4.00, 50, "018", "Bocadillos");

        // SNACKS Y APERITIVOS
        crearProducto("Patatas Chips", "Bolsa patatas chips", 1.50, 80, "019", "Snacks");
        crearProducto("Aceitunas", "Aceitunas rellenas de anchoa", 2.00, 60, "020", "Snacks");
        crearProducto("Anchoas", "Anchoas con aceite ajo y perejil", 3.50, 40, "021", "Snacks");
        crearProducto("Jamón Ibérico", "Jamón ibérico 100g", 6.00, 25, "022", "Snacks");
        crearProducto("Queso Manchego", "Queso manchego 100g", 4.50, 35, "023", "Snacks");
        crearProducto("Frutos Secos", "Mezcla frutos secos", 3.00, 50, "024", "Snacks");

        // POSTRES Y DULCES
        crearProducto("Tarta de Queso", "Porción tarta queso", 3.50, 30, "025", "Postres");
        crearProducto("Brownie", "Brownie chocolate", 2.50, 40, "026", "Postres");
        crearProducto("Churros", "Porción churros", 2.00, 50, "027", "Postres");
        crearProducto("Donut", "Donut glaseado", 1.50, 60, "028", "Postres");
        crearProducto("Helado", "Bola helado", 2.00, 70, "029", "Postres");
        crearProducto("Galletas", "Paquete galletas", 1.50, 100, "030", "Postres");
    }

    //=================AUTENTICACIÓN=======================
    public boolean autenticar(String usuario, String contraseña) {
        if (usuario.equalsIgnoreCase("empleado123") && "tpv123".equals(contraseña)) {
            return true;
        } else {
            return false;
        }
    }

    //=================GESTIÓN-PRODUCTOS===================
    public Producto crearProducto(String nombre, String descripcion, Double precio, Integer stockDisponible, String codigoBarras, String categoria) {
        Producto producto = new Producto(null, nombre, descripcion, precio, stockDisponible, codigoBarras, categoria);
        return productoDAO.addProducto(producto);
    }

    public Producto obtenerProducto(Integer id) {
        return productoDAO.getProducto(id);
    }

    public List<Producto> listarProductos() {
        return productoDAO.getProductos();
    }

    public Producto modificarProducto(Producto p) {
        return productoDAO.modifyProducto(p);
    }

    public Producto eliminarProducto(Integer id) {
        return productoDAO.deleteProducto(id);
    }

    //=================GESTIÓN-VENTAS======================
    public Venta crearVenta() {
        Venta venta = new Venta(null, new Date(), new ArrayList<>());
        return venta;
    }

    public void addLineaVenta(Venta venta, Producto producto, Integer cantidad) throws StockInsuficienteException {
        // Si el producto NO es null, y la cantidad es MAYOR o IGUAL al stock disponible y el stock es MAYOR a 0. 
        if (producto != null && (cantidad >= producto.getStockDisponible() && producto.getStockDisponible() > 0)) {
            LineaVenta lineaVenta = new LineaVenta(null, producto, cantidad);
            venta.getProductos().add(lineaVenta);
        } else { // Si no, lanzamos escepción. Que la capturaremos más adelante.
            throw new StockInsuficienteException("Stock insuficiente para " + producto.getNombre());
        }
    }

    public void removeLineaVenta(Venta venta, Integer idLinea) {
        if (venta != null && venta.getProductos() != null) {
            //EXPRESIÓN LAMBDA!! :) - Forma corta para escribir código en lugar de crear un método.
            // Para cada elemento llamado  \/ / Si el ID de la línea es igual al idLinea buscado, elimínalo.
            venta.getProductos().removeIf(linea -> linea.getId().equals(idLinea));
        }

        /*
        Sin Lambda sería algo así:
        for (int i = 0; i < venta.getProductos().size(); i++) {
            if (venta.getProductos().get(i).getId().equals(idLinea)) {
                venta.getProductos().remove(i);
            }
        }
         */
    }

    public Venta guardarVenta(Venta venta) {
        return ventaDAO.addVenta(venta);
    }

    public void vaciarCesta(Venta venta) {
        // Si venta NO es null y la lista de productos de ventas TAMPOCO es null.
        if (venta != null && venta.getProductos() != null) {
            venta.getProductos().clear();
        }
    }

    public List<Venta> getVentas() {
        return ventaDAO.getVentas();
    }

    public Double calcularVueltas(Double pagado, Double total) throws PagoInsuficienteException {
        //Si lo pagado es MAYOR o IGUAL al total.
        if (pagado >= total) {
            Double vueltas = pagado - total;
            return vueltas;
        } else {
            throw new PagoInsuficienteException("Pago insuficiente. Faltan: " + (total - pagado));
        }
    }
}
