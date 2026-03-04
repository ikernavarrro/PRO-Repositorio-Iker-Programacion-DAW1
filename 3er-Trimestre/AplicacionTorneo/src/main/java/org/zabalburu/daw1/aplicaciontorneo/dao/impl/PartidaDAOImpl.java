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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.dao.PartidaDAO;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;
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
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                p = crearPartida(rst);
            }
            rst.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(PartidaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return p;
    }

    @Override
    public List<Partida> getPartidas() {
        List<Partida> partidas = new ArrayList<>();
        try {
            ResultSet rst = cnn.createStatement().executeQuery("SELECT * FROM PARTIDAS");
            while (rst.next()) {
                partidas.add(crearPartida(rst));
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(PartidaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return partidas;
    }

    @Override
    public void modifyPartida(Partida modificar) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                        UPDATE PARTIDAS 
                                                        SET id = ?,
                                                           idjuego = ?,
                                                           idgana = ?,
                                                           idpierde = ?,
                                                           puntos = ?,
                                                           duracion = ?,
                                                           fecha = ?
                                                        WHERE id=?   
                                                        """);
            pstmt.setInt(1, modificar.getId());
            pstmt.setInt(2, modificar.getJuego().getId());
            pstmt.setInt(3, modificar.getGana().getId());
            pstmt.setInt(4, modificar.getPierde().getId());
            pstmt.setInt(5, modificar.getPuntos());
            pstmt.setString(6, modificar.getDuracion());
            pstmt.setTimestamp(7, Timestamp.valueOf(modificar.getFecha()));
            pstmt.setInt(8, modificar.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(JuegoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void removePartida(Integer id) {
        try {
            PreparedStatement pstmt = cnn.prepareStatement("""
                                                            DELETE partidas
                                                            Where id = ?
                                                            """);
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(PartidaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private Partida crearPartida(ResultSet rst) throws SQLException {
        Partida p = new Partida();
        p.setId(rst.getInt("id"));
        p.setFecha(rst.getTimestamp("fecha").toLocalDateTime());
        p.setDuracion(rst.getString("duracion"));
        p.setPuntos(rst.getInt("puntos"));
        Juego j = new Juego();
        j.setId(rst.getInt("idJuego"));
        p.setJuego(j);
        Jugador gana = new Jugador();
        gana.setId(rst.getInt("idGana"));
        p.setGana(gana);
        Jugador pierde = new Jugador();
        pierde.setId(rst.getInt("idPierde"));
        p.setPierde(pierde);
        return p;
    }
}
