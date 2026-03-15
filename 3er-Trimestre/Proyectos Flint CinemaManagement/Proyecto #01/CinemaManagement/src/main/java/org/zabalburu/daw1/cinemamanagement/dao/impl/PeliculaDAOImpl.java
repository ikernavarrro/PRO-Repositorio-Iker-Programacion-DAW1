/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.cinemamanagement.dao.PeliculaDAO;
import org.zabalburu.daw1.cinemamanagement.modelo.Pelicula;
import org.zabalburu.daw1.cinemamanagement.modelo.Sesion;
import org.zabalburu.daw1.cinemamanagement.util.Conexion;
import org.zabalburu.daw1.cinemamanagement.util.GeneroPelicula;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PeliculaDAOImpl implements PeliculaDAO {

    public static final Connection CNN = Conexion.getConnection();

    @Override
    public Pelicula addPelicula(Pelicula nueva) {
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                            INSERT INTO PELICULAS VALUES
                                                            (seqPeliculas.nextval, ?, ?, ?, ?, ?)
                                                            """);
            pstmt.setString(1, nueva.getTitulo());
            pstmt.setString(2, nueva.getDirector());
            pstmt.setInt(3, nueva.getAño());
            pstmt.setInt(4, nueva.getDuracion());
            pstmt.setString(5, nueva.getGenero().name());
            pstmt.executeUpdate();
            ResultSet rst = CNN.createStatement().executeQuery("""
                                                               SELECT seqPeliculas.currval as "id"
                                                               from dual
                                                               """);
            rst.next();
            nueva.setIdPelicula(rst.getInt("id"));
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(PeliculaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            nueva = null;
        }
        return nueva;
    }

    @Override
    public Pelicula getPelicula(Integer id) {
        Pelicula p = null;
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                           SELECT *
                                                           FROM PELICULAS
                                                           WHERE idPelicula = ?
                                                           """);
            pstmt.setInt(1, id);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                p = crearPelicula(rst);
            }
            pstmt.close();
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(PeliculaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return p;
    }

    @Override
    public List<Pelicula> getPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            ResultSet rst = CNN.createStatement().executeQuery("""
                                                               SELECT *
                                                               FROM PELICULAS
                                                               """);
            while (rst.next()) {
                peliculas.add(crearPelicula(rst));
            }
            rst.close();
        } catch (SQLException ex) {
            System.getLogger(PeliculaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return peliculas;
    }

    @Override
    public void modifyPelicula(Pelicula modificar) {
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                           UPDATE PELICULAS
                                                           SET 
                                                           titulo = ?,
                                                           director = ?,
                                                           año = ?,
                                                           duracion = ?,
                                                           genero = ?
                                                           WHERE idPelicula = ?
                                                           """);
            pstmt.setString(1, modificar.getTitulo());
            pstmt.setString(2, modificar.getDirector());
            pstmt.setInt(3, modificar.getAño());
            pstmt.setInt(4, modificar.getDuracion());
            pstmt.setString(5, modificar.getGenero().name());
            pstmt.setInt(6, modificar.getIdPelicula());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(PeliculaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void removePelicula(Integer id) {
        try {
            PreparedStatement pstmt = CNN.prepareStatement("""
                                                           DELETE FROM PELICULAS
                                                           WHERE idPelicula = ?
                                                           """);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.getLogger(PeliculaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public Pelicula crearPelicula(ResultSet rst) throws SQLException {
        Pelicula p = new Pelicula();
        p.setIdPelicula(rst.getInt("idPelicula"));
        p.setTitulo(rst.getString("titulo"));
        p.setDirector(rst.getString("director"));
        p.setAño(rst.getInt("año"));
        p.setDuracion(rst.getInt("duracion"));
        p.setGenero(GeneroPelicula.valueOf(rst.getString("genero").toUpperCase()));
        // === AÑADIMOS LAS SESIONES DE LA PELÍCULA (En caso de que tenga) ===
        PreparedStatement pstmt2 = CNN.prepareStatement("""
                                                        SELECT *
                                                        FROM SESIONES
                                                        WHERE idPelicula = ?
                                                        """);
        pstmt2.setInt(1, p.getIdPelicula());
        ResultSet rst2 = pstmt2.executeQuery();
        if (rst2.next()) {
            p.setSesiones(crearSesionesPelicula(rst2, p));
        }
        pstmt2.close();
        rst2.close();
        return p;
    }

    private List<Sesion> crearSesionesPelicula(ResultSet rst, Pelicula p) throws SQLException {
        List<Sesion> sesionesPelicula = new ArrayList<>();
        do {
            Sesion s = new Sesion();
            s.setIdSesion(rst.getInt("idSesion"));
            s.setPelicula(p);
            s.setFecha(rst.getDate("fecha").toLocalDate());
            s.setHora(rst.getString("hora"));
            s.setSala(rst.getInt("sala"));
            s.setAsientosDisponibles(rst.getInt("asientos_disponibles"));
            sesionesPelicula.add(s);
        } while (rst.next());
        return sesionesPelicula;
    }
}
