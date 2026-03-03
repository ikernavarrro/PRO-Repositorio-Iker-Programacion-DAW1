/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.dao.JugadorDAO;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;
import org.zabalburu.daw1.aplicaciontorneo.util.Conexion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class JugadorDAOImpl implements JugadorDAO {

    public static final Connection cnn = Conexion.getConnection();

    @Override
    public Jugador addJugador(Jugador nuevo) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        INSERT INTO JUGADORES VALUES
                                                        (seqJugadores.nextval, ?, ?, ?, ?)
                                                        """);
            pstmt.setString(1, nuevo.getNombre());
            pstmt.setString(2, nuevo.getApellidos());
            pstmt.setString(3, nuevo.getNick());
            pstmt.setString(4, nuevo.getNormal().toString());
            pstmt.executeUpdate();
            ResultSet rst = cnn.createStatement().executeQuery("""
                                                               SELECT seqJugadores.currval as "id"
                                                               FROM dual
                                                               """);
            rst.next();
            nuevo.setId(rst.getInt("id"));
            rst.close();
            return nuevo;
        } catch (SQLException ex) {
            System.getLogger(JugadorDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public Jugador getJugador(Integer id) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                            SELECT *
                                                            FROM JUGADORES
                                                            WHERE id = ?
                                                            """);
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                return crearJugador(rst);
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(JugadorDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public List<Jugador> getJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            ResultSet rst = cnn.createStatement().executeQuery("SELECT * FROM JUGADORES");
            while (rst.next()) {
                jugadores.add(crearJugador(rst));
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(JugadorDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return jugadores;
    }

    @Override
    public void modifyJugador(Jugador modificar) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        UPDATE JUGADORES 
                                                        SET nombre = ?,
                                                           apellidos = ?,
                                                           nick = ?,
                                                           imagen = ?
                                                        WHERE id=?   
                                                        """);
            pstmt.setString(1, modificar.getNombre());
            pstmt.setString(2, modificar.getApellidos());
            pstmt.setString(3, modificar.getNick());
            pstmt.setString(4, modificar.getNormal().toString());
            pstmt.setInt(5, modificar.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void removeJugador(Integer id) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        DELETE JUGADORES
                                                        WHERE id=?
                                                        """);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private Jugador crearJugador(ResultSet rst) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rst.getInt("id"));
        j.setNombre(rst.getString("nombre"));
        j.setApellidos(rst.getString("apellidos"));
        j.setNick(rst.getString("nick"));
        j.setImagen(rst.getString("imagen"));
        return j;
    }
    public static void main(String[] args) {
        
    }
}
