/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.dao.PartidaDAO;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Partida;
import org.zabalburu.daw1.aplicaciontorneo.util.Conexion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PartidaDAOImpl implements PartidaDAO {

    private Connection cnn = Conexion.getConnection();

    @Override
    public Partida addPartida(Partida nueva) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        INSERT INTO PARTIDAS VALUES
                                                        (seqPartidas.nextval, ?, ?, ?, ?, ?, ?)
                                                        """);
            pstmt.setInt(1, nueva.getJuego().getId());
            pstmt.setInt(2, nueva.getGana().getId());
            pstmt.setInt(3, nueva.getPierde().getId());
            pstmt.setInt(4, nueva.getPuntos());
            pstmt.setString(5, nueva.getDuracion());
            pstmt.setDate(6, Date.valueOf(nueva.getFecha().toLocalDate()));
            pstmt.executeUpdate();
            ResultSet rst = cnn.createStatement().executeQuery("""
                                                               SELECT seqPartidas.currval as "id"
                                                               FROM dual
                                                               """);
            rst.next();
            nueva.setId(rst.getInt("id"));
            rst.close();
            return nueva;
        } catch (SQLException ex) {
            System.getLogger(JugadorDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public Partida getPartida(Integer id) {
        Partida p = null;
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                            SELECT *
                                                            FROM PARTIDAS
                                                            WHERE id=?
                                                            """);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                p = crearPartida(rst);
            }
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(PartidaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return p;
    }

    @Override
    public List<Partida> getPartidas() {
        List<Partida> partidas = null;
        try {
            ResultSet rst = cnn.createStatement().executeQuery("SELECT * FROM PARTIDAS");
            while (rst.next()) {
                partidas.add(crearPartida(rst));
            }
        } catch (SQLException ex) {
            System.getLogger(PartidaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return partidas;
    }

    @Override
    public void modifyPartida(Partida modificar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removePartida(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Partida crearPartida(ResultSet rst) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
