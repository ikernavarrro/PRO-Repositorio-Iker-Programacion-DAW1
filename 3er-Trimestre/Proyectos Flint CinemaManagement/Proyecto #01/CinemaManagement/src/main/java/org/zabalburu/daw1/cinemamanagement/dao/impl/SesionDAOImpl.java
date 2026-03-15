/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.cinemamanagement.dao.SesionDAO;
import org.zabalburu.daw1.cinemamanagement.modelo.Pelicula;
import org.zabalburu.daw1.cinemamanagement.modelo.Sesion;
import org.zabalburu.daw1.cinemamanagement.util.Conexion;
import org.zabalburu.daw1.cinemamanagement.util.GeneroPelicula;

/**
 *
 * @author Iker Navarro Pérez
 */
public class SesionDAOImpl implements SesionDAO {

    private static final Connection CNN = Conexion.getConnection();

    @Override
    public Sesion addSesion(Sesion nueva) {
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                                       INSERT INTO SESIONES VALUES
                                                                       (seqSesiones.nextval, ?, ?, ?, ?, ?)
                                                                       """);
            pstmt.setInt(1, nueva.getPelicula().getIdPelicula());
            pstmt.setDate(2, Date.valueOf(nueva.getFecha()));
            pstmt.setString(3, nueva.getHora());
            pstmt.setInt(4, nueva.getSala());
            pstmt.setInt(5, nueva.getAsientosDisponibles());
            pstmt.executeUpdate();
            ResultSet rst = CNN.createStatement().executeQuery("""
                                                                           SELECT seqSesiones.currval as "id"
                                                                           FROM dual
                                                                           """);
            rst.next();
            nueva.setIdSesion(rst.getInt("id"));
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(SesionDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            nueva = null;
        }
        return nueva;
    }

    @Override
    public Sesion getSesion(Integer id) {
        Sesion s = null;
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                            SELECT *
                                                            FROM SESIONES
                                                            WHERE idSesion = ?
                                                            """);
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                s = crearSesion(rst);
            }
            rst.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(SesionDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return s;
    }

    @Override
    public List<Sesion> getSesiones() {
        List<Sesion> sesiones = new ArrayList<>();
        try {
            ResultSet rst = CNN.createStatement().executeQuery("SELECT * FROM SESIONES");
            while (rst.next()) {
                sesiones.add(crearSesion(rst));
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(SesionDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return sesiones;
    }

    @Override
    public void modifySesion(Sesion modificar) {
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                            UPDATE SESIONES 
                                                            SET
                                                            idPelicula = ?,
                                                            fecha = ?,
                                                            hora = ?,
                                                            sala = ?,
                                                            asientos_disponibles = ?
                                                            WHERE idSesion = ? 
                                                            """);
            pstmt.setInt(1, modificar.getPelicula().getIdPelicula());
            pstmt.setDate(2, Date.valueOf(modificar.getFecha()));
            pstmt.setString(3, modificar.getHora());
            pstmt.setInt(4, modificar.getSala());
            pstmt.setInt(5, modificar.getAsientosDisponibles());
            pstmt.setInt(6, modificar.getIdSesion());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(SesionDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void removeSesion(Integer id) {
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                            DELETE FROM SESIONES
                                                            WHERE idSesion = ?
                                                            """);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(SesionDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private Sesion crearSesion(ResultSet rst) throws SQLException {
        Sesion s = new Sesion();
        s.setIdSesion(rst.getInt("idSesion"));
        s.setFecha(rst.getDate("fecha").toLocalDate());
        s.setHora(rst.getString("hora"));
        s.setSala(rst.getInt("sala"));
        s.setAsientosDisponibles(rst.getInt("asientos_disponibles"));
        // === AÑADIMOS LA PELÍCULA ===
        PreparedStatement pstmt2 = CNN.prepareStatement("""
                                                        SELECT *
                                                        FROM PELICULAS
                                                        WHERE idPelicula IN (
                                                            SELECT idPelicula
                                                            FROM SESIONES
                                                            WHERE idSesion = ?)
                                                        """);
        pstmt2.setInt(1, s.getIdSesion());
        ResultSet rst2 = pstmt2.executeQuery();
        if (rst2.next()) {
            s.setPelicula(crearPeliculaSesion(rst2, s));
        }
        pstmt2.close();
        rst2.close();
        return s;
    }

    private Pelicula crearPeliculaSesion(ResultSet rst2, Sesion s) throws SQLException {
        Pelicula p = new Pelicula();
        p.setIdPelicula(rst2.getInt("idPelicula"));
        p.setTitulo(rst2.getString("titulo"));
        p.setDirector(rst2.getString("director"));
        p.setAño(rst2.getInt("año"));
        p.setDuracion(rst2.getInt("duracion"));
        p.setGenero(GeneroPelicula.valueOf(rst2.getString("genero").toUpperCase()));
        // SIN p.setSesiones() PARA NO SOBRECARGAR
        return p;
    }
}
