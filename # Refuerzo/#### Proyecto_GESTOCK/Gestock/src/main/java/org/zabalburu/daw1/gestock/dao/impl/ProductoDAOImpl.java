/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.gestock.dao.ProductoDAO;
import org.zabalburu.daw1.gestock.excepciones.ProductoNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.StockInsuficienteException;
import org.zabalburu.daw1.gestock.modelo.Almacen;
import org.zabalburu.daw1.gestock.modelo.Producto;
import org.zabalburu.daw1.gestock.util.Categoria;
import org.zabalburu.daw1.gestock.util.ConexionBBDD;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ProductoDAOImpl implements ProductoDAO {

    private Connection cnn;

    public ProductoDAOImpl() {
        this.cnn = ConexionBBDD.getConnection();
    }

    @Override
    public void addProducto(Producto nuevo) {
        if (nuevo != null) {
            String sql = "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            nuevo.setIdProducto(getSiguienteID());
            try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
                pstmt.setInt(1, nuevo.getIdProducto());
                pstmt.setString(2, nuevo.getCodigoBarras());
                pstmt.setString(3, nuevo.getDescripcion());
                pstmt.setString(4, nuevo.getMarca());
                pstmt.setString(5, nuevo.getCategoria().getValueDB());
                pstmt.setDouble(6, nuevo.getPrecio());
                pstmt.setInt(7, nuevo.getStock());
                pstmt.setDate(8, nuevo.getFechaEntrada() != null ? Date.valueOf(nuevo.getFechaEntrada()) : null);
                pstmt.setInt(9, nuevo.getAlmacen().getIdAlmacen());
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Producto> getProducto(Integer idProducto) {
        String sql = """
                     SELECT p.*, a.id_almacen AS id_almacen_obj, a.nombre, a.direccion, a.capacidad_maxima
                     FROM producto p JOIN almacen a ON p.id_almacen = a.id_almacen
                     WHERE p.id_producto = ?
                     """;

        Producto p = null;
        Almacen a = null;

        try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                p = new Producto();
                a = new Almacen();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setCodigoBarras(rs.getString("codigo_barras"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setMarca(rs.getString("marca"));
                p.setCategoria(Categoria.valueOf(rs.getString("categoria").toUpperCase()));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                Date fechaSQL = rs.getDate("fecha_entrada");
                if (fechaSQL != null) {
                    p.setFechaEntrada(fechaSQL.toLocalDate());
                }

                a.setIdAlmacen(rs.getInt("id_almacen_obj"));
                a.setNombre(rs.getString("nombre"));
                a.setDireccion(rs.getString("direccion"));
                a.setCapacidadMaxima(rs.getInt("capacidad_maxima"));

                p.setAlmacen(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.ofNullable(p);
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = """
                    SELECT p.*, a.id_almacen AS id_almacen_obj, a.nombre, a.direccion, a.capacidad_maxima
                    FROM producto p JOIN almacen a ON p.id_almacen = a.id_almacen
                    """;
        try (PreparedStatement pstmt = cnn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                Almacen a = new Almacen();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setCodigoBarras(rs.getString("codigo_barras"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setMarca(rs.getString("marca"));
                p.setCategoria(Categoria.valueOf(rs.getString("categoria").toUpperCase()));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                Date fechaSQL = rs.getDate("fecha_entrada");
                if (fechaSQL != null) {
                    p.setFechaEntrada(fechaSQL.toLocalDate());
                }

                a.setIdAlmacen(rs.getInt("id_almacen_obj"));
                a.setNombre(rs.getString("nombre"));
                a.setDireccion(rs.getString("direccion"));
                a.setCapacidadMaxima(rs.getInt("capacidad_maxima"));

                p.setAlmacen(a);
                productos.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    @Override
    public List<Producto> getProductosBusqueda(String textoBusqueda) {
        List<Producto> productos = new ArrayList<>();
        String sql = """
                     SELECT p.*, a.id_almacen AS id_almacen_obj, a.nombre, a.direccion, a.capacidad_maxima
                     FROM producto p JOIN almacen a ON p.id_almacen = a.id_almacen
                     WHERE LOWER(p.descripcion) LIKE ? OR LOWER(p.marca) LIKE ?
                     """;

        try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
            String filtro = "%" + textoBusqueda.toLowerCase() + "%";
            pstmt.setString(1, filtro);
            pstmt.setString(2, filtro);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    Almacen a = new Almacen();

                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setCodigoBarras(rs.getString("codigo_barras"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setMarca(rs.getString("marca"));
                    p.setCategoria(Categoria.valueOf(rs.getString("categoria").toUpperCase()));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));

                    Date fechaSQL = rs.getDate("fecha_entrada");
                    if (fechaSQL != null) {
                        p.setFechaEntrada(fechaSQL.toLocalDate());
                    }

                    a.setIdAlmacen(rs.getInt("id_almacen_obj"));
                    a.setNombre(rs.getString("nombre"));
                    a.setDireccion(rs.getString("direccion"));
                    a.setCapacidadMaxima(rs.getInt("capacidad_maxima"));

                    p.setAlmacen(a);
                    productos.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    @Override
    public void modifyProducto(Producto modificar) throws StockInsuficienteException, ProductoNoEncontradoException {
        if (modificar.getStock() < 0) {
            throw new StockInsuficienteException("El stock no puede ser un valor negativo.");
        }

        String sql = """
                     UPDATE producto 
                     SET codigo_barras = ?, descripcion = ?, marca = ?, categoria = ?, 
                         precio = ?, stock = ?, fecha_entrada = ?, id_almacen = ? 
                     WHERE id_producto = ?
                     """;

        try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
            pstmt.setString(1, modificar.getCodigoBarras());
            pstmt.setString(2, modificar.getDescripcion());
            pstmt.setString(3, modificar.getMarca());
            pstmt.setString(4, modificar.getCategoria().getValueDB());
            pstmt.setDouble(5, modificar.getPrecio());
            pstmt.setInt(6, modificar.getStock());
            pstmt.setDate(7, modificar.getFechaEntrada() != null ? Date.valueOf(modificar.getFechaEntrada()) : null);
            pstmt.setInt(8, modificar.getAlmacen().getIdAlmacen());
            pstmt.setInt(9, modificar.getIdProducto());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException("No se encontró el producto con ID: " + modificar.getIdProducto());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeProducto(Integer idProducto) throws ProductoNoEncontradoException {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException("No se encontró el producto con ID: " + idProducto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getSiguienteID() {
        String sql = "SELECT MAX(id_producto) FROM PRODUCTO";
        int siguienteId = 1;
        try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int maxId = rs.getInt(1);
                siguienteId = maxId + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return siguienteId;
    }

}
