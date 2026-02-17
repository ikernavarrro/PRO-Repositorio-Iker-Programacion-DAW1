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
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.gestock.dao.AlmacenDAO;
import org.zabalburu.daw1.gestock.excepciones.AlmacenNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.EliminarAlmacenConProductosException;
import org.zabalburu.daw1.gestock.modelo.Almacen;
import org.zabalburu.daw1.gestock.modelo.Producto;
import org.zabalburu.daw1.gestock.util.Categoria;
import org.zabalburu.daw1.gestock.util.ConexionBBDD;

/**
 *
 * @author Iker Navarro Pérez
 */
public class AlmacenDAOImpl implements AlmacenDAO {

    private Connection cnn;

    public AlmacenDAOImpl() {
        this.cnn = ConexionBBDD.getConnection();
    }

    @Override
    public void addAlmacen(Almacen nuevo) {
        if (nuevo != null) {
            String sql = "INSERT INTO ALMACEN VALUES (?, ?, ?, ?)";
            nuevo.setIdAlmacen(getSiguienteID());
            try (PreparedStatement pstmt = cnn.prepareStatement(sql)) {
                pstmt.setInt(1, nuevo.getIdAlmacen());
                pstmt.setString(2, nuevo.getNombre());
                pstmt.setString(3, nuevo.getDireccion());
                pstmt.setInt(4, nuevo.getCapacidadMaxima());
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Almacen> getAlmacen(Integer idAlmacen) {
        String sqlAlmacen = "SELECT * FROM almacen WHERE id_almacen = ?";
        String sqlProductos = "SELECT * FROM producto WHERE id_almacen = ?";

        Almacen a = null;

        try (PreparedStatement pstmtAlmacen = cnn.prepareStatement(sqlAlmacen)) {
            pstmtAlmacen.setInt(1, idAlmacen);
            ResultSet rsA = pstmtAlmacen.executeQuery();

            if (rsA.next()) {
                a = new Almacen();
                a.setIdAlmacen(rsA.getInt("id_almacen"));
                a.setNombre(rsA.getString("nombre"));
                a.setDireccion(rsA.getString("direccion"));
                a.setCapacidadMaxima(rsA.getInt("capacidad_maxima"));

                try (PreparedStatement pstmtProductos = cnn.prepareStatement(sqlProductos)) {
                    pstmtProductos.setInt(1, idAlmacen);
                    ResultSet rsP = pstmtProductos.executeQuery();

                    while (rsP.next()) {
                        Producto p = new Producto();
                        p.setIdProducto(rsP.getInt("id_producto"));
                        p.setCodigoBarras(rsP.getString("codigo_barras"));
                        p.setDescripcion(rsP.getString("descripcion"));
                        p.setMarca(rsP.getString("marca"));
                        p.setCategoria(Categoria.valueOf(rsP.getString("categoria").toUpperCase()));
                        p.setPrecio(rsP.getDouble("precio"));
                        p.setStock(rsP.getInt("stock"));
                        Date fechaSQL = rsP.getDate("fecha_entrada");
                        if (fechaSQL != null) {
                            p.setFechaEntrada(fechaSQL.toLocalDate());
                        }
                        a.getProductos().add(p);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Optional.ofNullable(a);
    }

    @Override
    public List<Almacen> getAlmacenes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Almacen> getAlmacenesBusqueda(String textoBusqueda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifyAlmacen(Almacen modificar) throws AlmacenNoEncontradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeAlmacen(Integer idAlmacen) throws EliminarAlmacenConProductosException, AlmacenNoEncontradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getSiguienteID() {
        String sql = "SELECT MAX(id_almacen) FROM ALMACEN";
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
