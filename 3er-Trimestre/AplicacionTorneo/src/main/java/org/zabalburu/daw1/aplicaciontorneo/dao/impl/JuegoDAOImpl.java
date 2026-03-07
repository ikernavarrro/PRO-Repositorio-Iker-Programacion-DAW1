/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.dao.JuegoDAO;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.util.Conexion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class JuegoDAOImpl implements JuegoDAO {

    public static final Connection cnn = Conexion.getConnection();

    @Override
    public Juego addJuego(Juego nuevo) {
        try {
            /*ResultSet rst = cnn.createStatement().executeQuery("""
                                                                SELECT MAX(id) AS "maximo"
                                                                FROM JUEGOS
                                                                """);
            Integer id = 1;
            if (rst.next()){
                id= rst.getInt("maximo") + 1;
            }*/
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                           INSERT INTO JUEGOS VALUES
                                                           (seqjuegos.nextval, ?,?,?,?)
                                                           """);
            pstmt.setString(1, nuevo.getTitulo());
            pstmt.setString(2, nuevo.getDescripcion());
            pstmt.setString(3, nuevo.getTipo());
            pstmt.setString(4, nuevo.getImagen());
            pstmt.executeUpdate();
            ResultSet rst = cnn.createStatement().executeQuery("""
                                                               SELECT seqJuegos.currval as "id"
                                                               from dual
                                                               """);
            rst.next();
            nuevo.setId(rst.getInt("id"));
            rst.close();
            return nuevo;
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public Juego getJuego(Integer id) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                            SELECT *
                                                            FROM JUEGOS
                                                            WHERE ID = ?
                                                            """);
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                return crearJuego(rst);
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public List<Juego> getJuegos() {
        List<Juego> juegos = new ArrayList<>();
        try {
            ResultSet rst = cnn.createStatement().executeQuery("""
                                                               SELECT *
                                                               FROM JUEGOS
                                                               """);
            while (rst.next()) {
                juegos.add(crearJuego(rst));
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return juegos;
    }

    @Override
    public void modifyJuego(Juego modificar) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        UPDATE JUEGOS 
                                                        SET titulo = ?,
                                                           descripcion = ?,
                                                           tipo = ?,
                                                           imagen =?
                                                        WHERE id=?   
                                                        """);
            pstmt.setString(1, modificar.getTitulo());
            pstmt.setString(2,modificar.getDescripcion());
            pstmt.setString(3, modificar.getTipo());
            pstmt.setString(4, modificar.getImagen());
            pstmt.setInt(5, modificar.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void removeJuego(Integer id) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        DELETE FROM JUEGOS
                                                        WHERE id=?
                                                        """);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private Juego crearJuego(ResultSet rst) throws SQLException {
        Juego j = new Juego();
        j.setId(rst.getInt("id"));
        j.setTitulo(rst.getString("titulo"));
        j.setImagen(rst.getString("imagen"));
        j.setTipo(rst.getString("tipo"));
        j.setDescripcion(rst.getString("descripcion"));
        return j;
    }

    public static void main(String[] args) {
        JuegoDAO dao = new JuegoDAOImpl();
        Juego nuevo = new Juego();
        System.out.println(dao.getJuegos());
        
    }
}
