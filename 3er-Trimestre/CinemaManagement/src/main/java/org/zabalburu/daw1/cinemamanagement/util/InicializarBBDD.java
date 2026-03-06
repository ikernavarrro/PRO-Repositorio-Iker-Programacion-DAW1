/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Iker Navarro Pérez
 */
public class InicializarBBDD {

    public static void main(String[] args) {
        Connection cnn = null;
        try {
            cnn = Conexion.getConnection();
            Statement stmt = cnn.createStatement();

            // ====== BORRAMOS TABLAS ======
            try {
                stmt.executeUpdate("DROP TABLE SESIONES");
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("DROP TABLE PELICULAS");
            } catch (SQLException ex) {
            }

            // ====== QUITAMOS EL AUTOCOMMIT ====== (En caso de que surgiese cualquier error inesperado no guardamos nada) ======
            cnn.setAutoCommit(false);

            // ====== CREACIÓN DE TABLAS ======
            stmt.executeUpdate("""
                               CREATE TABLE Peliculas (
                                   idpelicula NUMBER(3) CONSTRAINT PK_PELICULAS PRIMARY KEY,
                                   titulo VARCHAR2(100) CONSTRAINT NN_TITULO_PELICULAS NOT NULL,
                                   director VARCHAR2(100) CONSTRAINT NN_DIRECTOR_PELICULAS NOT NULL,
                                   año NUMBER(4) CONSTRAINT NN_AÑO_PELICULAS NOT NULL,
                                   duracion NUMBER(3) CONSTRAINT NN_DURACION_PELICULAS NOT NULL,
                                   genero VARCHAR2(50) CONSTRAINT NN_GENERO_PELICULAS NOT NULL
                               )
                               """);

            stmt.executeUpdate("""
                               CREATE TABLE Sesiones (
                                   idsesion NUMBER(3) CONSTRAINT PK_SESIONES PRIMARY KEY,
                                   idpelicula NUMBER(3) CONSTRAINT NN_IDPELICULA_SESIONES NOT NULL,
                                   fecha DATE CONSTRAINT NN_FECHA_SESIONES NOT NULL,
                                   hora VARCHAR2(5) CONSTRAINT NN_HORA_SESIONES NOT NULL,
                                   sala NUMBER(2) CONSTRAINT NN_SALA_SESIONES NOT NULL,
                                   asientos_disponibles NUMBER(3) CONSTRAINT NN_ASIENTOS_SESIONES NOT NULL,
                                   CONSTRAINT FK_SESIONES_PELICULAS FOREIGN KEY (idpelicula) REFERENCES Peliculas(idpelicula) ON DELETE CASCADE
                               )
                               """);

            // ====== REINICIO DE SECUENCIAS ====== (ID ÚNICOS)
            String[] secuencias = {"seqPeliculas", "seqSesiones"};
            for (String secuencia : secuencias) {
                try {
                    stmt.executeUpdate("DROP SEQUENCE %s".formatted(secuencia));
                } catch (SQLException ex) {
                }
                stmt.executeUpdate("CREATE SEQUENCE %s START WITH 1 INCREMENT BY 1".formatted(secuencia));
            }
            
            // ====== DATOS DE PRUEBA ======
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Inception', 'Christopher Nolan', 2010, 148, 'Ciencia Ficción')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'The Dark Knight', 'Christopher Nolan', 2008, 152, 'Acción')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Interstellar', 'Christopher Nolan', 2014, 169, 'Ciencia Ficción')");
            
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2024-01-15', 'YYYY-MM-DD'), '18:00', 1, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2024-01-15', 'YYYY-MM-DD'), '20:30', 2, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2024-01-16', 'YYYY-MM-DD'), '19:00', 3, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2024-01-17', 'YYYY-MM-DD'), '21:00', 1, 40)");
            
            // ====== CERRAMOS EL STATEMENT ======
            stmt.close();
            
            // ====== CONFIRMAMOS (COMMIT) ======
            cnn.commit();
            System.out.println("Base de datos REINICIADA con ÉXITO");

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                if (cnn != null) {
                    cnn.rollback();
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

    }
}
