/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Iker Navarro Pérez
 */
public class InicializarDatos {

    public static void main(String[] args) {
        Connection cnn = null;
        try {
            cnn = Conexion.getConnection();
            Statement stmt = cnn.createStatement();
            try {
                stmt.executeUpdate("""
                               DROP TABLE PARTIDAS
                               """);
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("""
                               DROP TABLE JUGADORES
                               """);
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("""
                               DROP TABLE JUEGOS
                               """);
            } catch (SQLException ex) {
            }
            cnn.setAutoCommit(false);
            stmt.executeUpdate("""
                               CREATE TABLE JUEGOS(
                                id NUMBER(2) CONSTRAINT PK_JUEGOS PRIMARY KEY,
                                titulo VARCHAR2(100),
                                descripcion VARCHAR2(200),
                                tipo VARCHAR2(50),
                                imagen VARCHAR2(100)
                               )
                               """);
            stmt.executeUpdate("""
                               CREATE TABLE JUGADORES(
                                id NUMBER(2) CONSTRAINT PK_JUGADOR PRIMARY KEY,
                                nombre VARCHAR2(100),
                                apellidos VARCHAR2(100),
                                nick VARCHAR2(50),
                                imagen VARCHAR2(100)
                               )
                               """);
            stmt.executeUpdate("""
                               CREATE TABLE PARTIDAS(
                                id NUMBER(2) CONSTRAINT PK_PARTIDAS PRIMARY KEY,
                                idJuego NUMBER(2) CONSTRAINT FK_PARTIDAS_JUEGOS REFERENCES JUEGOS,
                                idGana NUMBER(2) CONSTRAINT FK_PARTIDAS_JUGADORES_GANA REFERENCES JUGADORES,
                                idPierde NUMBER(2) CONSTRAINT FK_PARTIDAS_JUGADORES_PIERDE REFERENCES JUGADORES,
                                puntos NUMBER(3),
                                duracion VARCHAR2(50),
                                fecha DATE
                               )
                               """);

            String[] secuencias = {"seqJuegos", "seqJugadores", "seqPartidas"};
            for (String secuencia : secuencias) {
                try {
                    stmt.executeUpdate("""
                               DROP SEQUENCE %s;
                               """.formatted(secuencia));
                } catch (SQLException ex) {
                }
                stmt.executeUpdate("""
                               CREATE SEQUENCE %s;
                               """.formatted(secuencia));
            }

        } catch (SQLException ex) {
            System.getLogger(InicializarDatos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            try {
                cnn.rollback();
            } catch (SQLException exc) {
            }
        }
    }
}
