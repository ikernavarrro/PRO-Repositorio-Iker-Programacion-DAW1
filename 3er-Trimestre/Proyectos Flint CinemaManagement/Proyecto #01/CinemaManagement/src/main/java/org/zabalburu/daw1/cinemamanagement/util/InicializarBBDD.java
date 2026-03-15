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
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Inception', 'Christopher Nolan', 2010, 148, 'FICCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'The Dark Knight', 'Christopher Nolan', 2008, 152, 'ACCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Interstellar', 'Christopher Nolan', 2014, 169, 'FICCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Pulp Fiction', 'Quentin Tarantino', 1994, 154, 'THRILLER')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Padrino', 'Francis Ford Coppola', 1972, 175, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Forrest Gump', 'Robert Zemeckis', 1994, 142, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Matrix', 'Lana Wachowski', 1999, 136, 'FICCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Gladiator', 'Ridley Scott', 2000, 155, 'ACCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Titanic', 'James Cameron', 1997, 195, 'ROMANCE')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Avatar', 'James Cameron', 2009, 162, 'FANTASIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Señor de los Anillos: La Comunidad', 'Peter Jackson', 2001, 178, 'FANTASIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Señor de los Anillos: Las Dos Torres', 'Peter Jackson', 2002, 179, 'FANTASIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Señor de los Anillos: El Retorno del Rey', 'Peter Jackson', 2003, 201, 'FANTASIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Star Wars: Una Nueva Esperanza', 'George Lucas', 1977, 121, 'FICCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Star Wars: El Imperio Contraataca', 'Irvin Kershner', 1980, 124, 'FICCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Schindler''s List', 'Steven Spielberg', 1993, 195, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Saving Private Ryan', 'Steven Spielberg', 1998, 169, 'BELICO')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Joker', 'Todd Phillips', 2019, 122, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Parasite', 'Bong Joon-ho', 2019, 132, 'THRILLER')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Whiplash', 'Damien Chazelle', 2014, 107, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'La La Land', 'Damien Chazelle', 2016, 128, 'MUSICAL')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Toy Story', 'John Lasseter', 1995, 81, 'ANIMACION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Up', 'Pete Docter', 2009, 96, 'ANIMACION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Coco', 'Lee Unkrich', 2017, 105, 'ANIMACION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Inside Out', 'Pete Docter', 2015, 95, 'ANIMACION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'The Shawshank Redemption', 'Frank Darabont', 1994, 142, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Seven', 'David Fincher', 1995, 127, 'POLICIAL')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Fight Club', 'David Fincher', 1999, 139, 'THRILLER')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Silencio de los Corderos', 'Jonathan Demme', 1991, 118, 'THRILLER')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Alien', 'Ridley Scott', 1979, 117, 'TERROR')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Resplandor', 'Stanley Kubrick', 1980, 144, 'TERROR')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Get Out', 'Jordan Peele', 2017, 104, 'TERROR')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Hereditary', 'Ari Aster', 2018, 127, 'TERROR')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'The Grand Budapest Hotel', 'Wes Anderson', 2014, 99, 'COMEDIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Superbad', 'Greg Mottola', 2007, 113, 'COMEDIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'La Vida es Bella', 'Roberto Benigni', 1997, 116, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Cinema Paradiso', 'Giuseppe Tornatore', 1988, 155, 'DRAMA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Amélie', 'Jean-Pierre Jeunet', 2001, 122, 'ROMANCE')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Oldboy', 'Park Chan-wook', 2003, 120, 'MISTERIO')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Mad Max: Fury Road', 'George Miller', 2015, 120, 'ACCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'John Wick', 'Chad Stahelski', 2014, 101, 'ACCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Indiana Jones y el Arca Perdida', 'Steven Spielberg', 1981, 115, 'AVENTURA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Jurassic Park', 'Steven Spielberg', 1993, 127, 'AVENTURA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Back to the Future', 'Robert Zemeckis', 1985, 116, 'FICCION')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Dunkerque', 'Christopher Nolan', 2017, 106, 'BELICO')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'El Gran Lebowski', 'Joel Coen', 1998, 117, 'COMEDIA')");
            stmt.executeUpdate("INSERT INTO Peliculas VALUES (seqPeliculas.NEXTVAL, 'Memento', 'Christopher Nolan', 2000, 113, 'MISTERIO')");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-06','YYYY-MM-DD'), '16:00', 1, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-06','YYYY-MM-DD'), '19:00', 2, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-07','YYYY-MM-DD'), '17:30', 1, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-07','YYYY-MM-DD'), '20:30', 3, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-08','YYYY-MM-DD'), '18:00', 2, 140)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-09','YYYY-MM-DD'), '16:30', 4, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-09','YYYY-MM-DD'), '21:00', 1, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 1, TO_DATE('2025-01-10','YYYY-MM-DD'), '19:30', 3, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-06','YYYY-MM-DD'), '17:00', 3, 130)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-06','YYYY-MM-DD'), '20:00', 4, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-07','YYYY-MM-DD'), '16:00', 2, 115)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-07','YYYY-MM-DD'), '21:30', 1, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-08','YYYY-MM-DD'), '19:00', 3, 145)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-09','YYYY-MM-DD'), '17:30', 2, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-10','YYYY-MM-DD'), '20:00', 4, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-11','YYYY-MM-DD'), '18:30', 1, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 2, TO_DATE('2025-01-11','YYYY-MM-DD'), '22:00', 3, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-06','YYYY-MM-DD'), '18:30', 2, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-07','YYYY-MM-DD'), '15:00', 4, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-07','YYYY-MM-DD'), '20:00', 1, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-08','YYYY-MM-DD'), '17:00', 3, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-09','YYYY-MM-DD'), '19:30', 2, 140)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-10','YYYY-MM-DD'), '16:30', 4, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-11','YYYY-MM-DD'), '21:00', 1, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 3, TO_DATE('2025-01-12','YYYY-MM-DD'), '18:00', 2, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-06','YYYY-MM-DD'), '20:30', 1, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-07','YYYY-MM-DD'), '18:00', 3, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-08','YYYY-MM-DD'), '20:00', 2, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-09','YYYY-MM-DD'), '22:00', 4, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-10','YYYY-MM-DD'), '19:00', 1, 115)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-11','YYYY-MM-DD'), '21:30', 3, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-12','YYYY-MM-DD'), '18:30', 2, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 4, TO_DATE('2025-01-13','YYYY-MM-DD'), '20:30', 4, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-07','YYYY-MM-DD'), '16:00', 4, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-07','YYYY-MM-DD'), '19:30', 2, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-08','YYYY-MM-DD'), '17:30', 1, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-09','YYYY-MM-DD'), '20:00', 3, 130)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-10','YYYY-MM-DD'), '18:30', 4, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-11','YYYY-MM-DD'), '16:30', 2, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-12','YYYY-MM-DD'), '21:00', 1, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 5, TO_DATE('2025-01-13','YYYY-MM-DD'), '19:00', 3, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 6, TO_DATE('2025-01-08','YYYY-MM-DD'), '16:00', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 6, TO_DATE('2025-01-09','YYYY-MM-DD'), '19:00', 2, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 6, TO_DATE('2025-01-10','YYYY-MM-DD'), '17:30', 4, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 6, TO_DATE('2025-01-11','YYYY-MM-DD'), '20:00', 3, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 6, TO_DATE('2025-01-12','YYYY-MM-DD'), '18:30', 1, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 6, TO_DATE('2025-01-13','YYYY-MM-DD'), '21:30', 2, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 7, TO_DATE('2025-01-08','YYYY-MM-DD'), '20:00', 3, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 7, TO_DATE('2025-01-09','YYYY-MM-DD'), '17:00', 1, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 7, TO_DATE('2025-01-10','YYYY-MM-DD'), '21:00', 4, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 7, TO_DATE('2025-01-11','YYYY-MM-DD'), '18:30', 2, 140)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 7, TO_DATE('2025-01-12','YYYY-MM-DD'), '20:30', 3, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 8, TO_DATE('2025-01-08','YYYY-MM-DD'), '17:00', 2, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 8, TO_DATE('2025-01-09','YYYY-MM-DD'), '20:30', 4, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 8, TO_DATE('2025-01-10','YYYY-MM-DD'), '16:00', 1, 115)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 8, TO_DATE('2025-01-11','YYYY-MM-DD'), '19:30', 3, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 8, TO_DATE('2025-01-13','YYYY-MM-DD'), '21:00', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 9, TO_DATE('2025-01-09','YYYY-MM-DD'), '16:00', 1, 145)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 9, TO_DATE('2025-01-09','YYYY-MM-DD'), '19:30', 3, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 9, TO_DATE('2025-01-10','YYYY-MM-DD'), '17:00', 2, 135)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 9, TO_DATE('2025-01-11','YYYY-MM-DD'), '20:00', 4, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 9, TO_DATE('2025-01-12','YYYY-MM-DD'), '18:30', 1, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 10, TO_DATE('2025-01-09','YYYY-MM-DD'), '18:00', 4, 130)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 10, TO_DATE('2025-01-10','YYYY-MM-DD'), '20:30', 2, 105)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 10, TO_DATE('2025-01-11','YYYY-MM-DD'), '16:30', 3, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 10, TO_DATE('2025-01-12','YYYY-MM-DD'), '19:00', 1, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 10, TO_DATE('2025-01-13','YYYY-MM-DD'), '17:30', 4, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 10, TO_DATE('2025-01-14','YYYY-MM-DD'), '21:00', 2, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 11, TO_DATE('2025-01-10','YYYY-MM-DD'), '15:00', 1, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 11, TO_DATE('2025-01-10','YYYY-MM-DD'), '19:00', 3, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 11, TO_DATE('2025-01-11','YYYY-MM-DD'), '17:30', 2, 115)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 11, TO_DATE('2025-01-12','YYYY-MM-DD'), '20:30', 4, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 11, TO_DATE('2025-01-13','YYYY-MM-DD'), '16:00', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 12, TO_DATE('2025-01-10','YYYY-MM-DD'), '18:00', 3, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 12, TO_DATE('2025-01-11','YYYY-MM-DD'), '21:00', 2, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 12, TO_DATE('2025-01-12','YYYY-MM-DD'), '16:30', 4, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 12, TO_DATE('2025-01-13','YYYY-MM-DD'), '19:30', 1, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 12, TO_DATE('2025-01-14','YYYY-MM-DD'), '17:00', 3, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 13, TO_DATE('2025-01-11','YYYY-MM-DD'), '15:30', 2, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 13, TO_DATE('2025-01-11','YYYY-MM-DD'), '20:00', 4, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 13, TO_DATE('2025-01-12','YYYY-MM-DD'), '17:30', 1, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 13, TO_DATE('2025-01-13','YYYY-MM-DD'), '21:00', 3, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 13, TO_DATE('2025-01-14','YYYY-MM-DD'), '19:00', 2, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 14, TO_DATE('2025-01-11','YYYY-MM-DD'), '17:00', 3, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 14, TO_DATE('2025-01-12','YYYY-MM-DD'), '20:00', 1, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 14, TO_DATE('2025-01-13','YYYY-MM-DD'), '16:30', 4, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 14, TO_DATE('2025-01-14','YYYY-MM-DD'), '19:30', 2, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 14, TO_DATE('2025-01-15','YYYY-MM-DD'), '21:30', 3, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 15, TO_DATE('2025-01-11','YYYY-MM-DD'), '16:00', 2, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 15, TO_DATE('2025-01-12','YYYY-MM-DD'), '19:00', 4, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 15, TO_DATE('2025-01-13','YYYY-MM-DD'), '17:30', 1, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 15, TO_DATE('2025-01-14','YYYY-MM-DD'), '21:00', 3, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 15, TO_DATE('2025-01-15','YYYY-MM-DD'), '18:30', 2, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 16, TO_DATE('2025-01-12','YYYY-MM-DD'), '16:00', 1, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 16, TO_DATE('2025-01-13','YYYY-MM-DD'), '19:30', 3, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 16, TO_DATE('2025-01-14','YYYY-MM-DD'), '17:00', 2, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 16, TO_DATE('2025-01-15','YYYY-MM-DD'), '21:00', 4, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 17, TO_DATE('2025-01-12','YYYY-MM-DD'), '18:30', 4, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 17, TO_DATE('2025-01-13','YYYY-MM-DD'), '21:00', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 17, TO_DATE('2025-01-14','YYYY-MM-DD'), '16:30', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 17, TO_DATE('2025-01-15','YYYY-MM-DD'), '20:00', 3, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 18, TO_DATE('2025-01-12','YYYY-MM-DD'), '20:00', 2, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 18, TO_DATE('2025-01-13','YYYY-MM-DD'), '17:30', 4, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 18, TO_DATE('2025-01-14','YYYY-MM-DD'), '20:30', 1, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 18, TO_DATE('2025-01-15','YYYY-MM-DD'), '18:00', 3, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 18, TO_DATE('2025-01-16','YYYY-MM-DD'), '21:30', 2, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 19, TO_DATE('2025-01-13','YYYY-MM-DD'), '16:00', 1, 130)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 19, TO_DATE('2025-01-13','YYYY-MM-DD'), '20:00', 3, 115)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 19, TO_DATE('2025-01-14','YYYY-MM-DD'), '18:30', 2, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 19, TO_DATE('2025-01-15','YYYY-MM-DD'), '21:00', 4, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 19, TO_DATE('2025-01-16','YYYY-MM-DD'), '17:00', 1, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 20, TO_DATE('2025-01-13','YYYY-MM-DD'), '18:00', 4, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 20, TO_DATE('2025-01-14','YYYY-MM-DD'), '21:00', 2, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 20, TO_DATE('2025-01-15','YYYY-MM-DD'), '16:30', 3, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 20, TO_DATE('2025-01-16','YYYY-MM-DD'), '20:00', 1, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 21, TO_DATE('2025-01-14','YYYY-MM-DD'), '16:00', 3, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 21, TO_DATE('2025-01-14','YYYY-MM-DD'), '19:30', 1, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 21, TO_DATE('2025-01-15','YYYY-MM-DD'), '17:30', 2, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 21, TO_DATE('2025-01-16','YYYY-MM-DD'), '20:30', 4, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 21, TO_DATE('2025-01-17','YYYY-MM-DD'), '18:00', 3, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 22, TO_DATE('2025-01-14','YYYY-MM-DD'), '12:00', 2, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 22, TO_DATE('2025-01-15','YYYY-MM-DD'), '15:30', 4, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 22, TO_DATE('2025-01-16','YYYY-MM-DD'), '12:30', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 22, TO_DATE('2025-01-17','YYYY-MM-DD'), '16:00', 3, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 23, TO_DATE('2025-01-14','YYYY-MM-DD'), '11:00', 1, 135)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 23, TO_DATE('2025-01-14','YYYY-MM-DD'), '16:00', 3, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 23, TO_DATE('2025-01-15','YYYY-MM-DD'), '11:30', 2, 115)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 23, TO_DATE('2025-01-15','YYYY-MM-DD'), '17:00', 4, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 23, TO_DATE('2025-01-16','YYYY-MM-DD'), '12:00', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 24, TO_DATE('2025-01-14','YYYY-MM-DD'), '13:00', 4, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 24, TO_DATE('2025-01-15','YYYY-MM-DD'), '12:00', 2, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 24, TO_DATE('2025-01-16','YYYY-MM-DD'), '14:00', 3, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 24, TO_DATE('2025-01-17','YYYY-MM-DD'), '11:30', 1, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 25, TO_DATE('2025-01-15','YYYY-MM-DD'), '12:00', 1, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 25, TO_DATE('2025-01-15','YYYY-MM-DD'), '17:00', 3, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 25, TO_DATE('2025-01-16','YYYY-MM-DD'), '11:00', 2, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 25, TO_DATE('2025-01-16','YYYY-MM-DD'), '16:30', 4, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 26, TO_DATE('2025-01-15','YYYY-MM-DD'), '20:00', 2, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 26, TO_DATE('2025-01-16','YYYY-MM-DD'), '17:30', 4, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 26, TO_DATE('2025-01-17','YYYY-MM-DD'), '20:00', 1, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 26, TO_DATE('2025-01-18','YYYY-MM-DD'), '18:30', 3, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 27, TO_DATE('2025-01-15','YYYY-MM-DD'), '22:00', 3, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 27, TO_DATE('2025-01-16','YYYY-MM-DD'), '20:30', 1, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 27, TO_DATE('2025-01-17','YYYY-MM-DD'), '22:30', 4, 40)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 27, TO_DATE('2025-01-18','YYYY-MM-DD'), '21:00', 2, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 28, TO_DATE('2025-01-15','YYYY-MM-DD'), '19:00', 4, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 28, TO_DATE('2025-01-16','YYYY-MM-DD'), '21:30', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 28, TO_DATE('2025-01-17','YYYY-MM-DD'), '19:30', 1, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 28, TO_DATE('2025-01-18','YYYY-MM-DD'), '22:00', 3, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 29, TO_DATE('2025-01-16','YYYY-MM-DD'), '18:00', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 29, TO_DATE('2025-01-17','YYYY-MM-DD'), '16:30', 3, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 29, TO_DATE('2025-01-18','YYYY-MM-DD'), '20:00', 2, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 29, TO_DATE('2025-01-19','YYYY-MM-DD'), '17:30', 4, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 30, TO_DATE('2025-01-16','YYYY-MM-DD'), '22:30', 2, 35)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 30, TO_DATE('2025-01-17','YYYY-MM-DD'), '21:00', 4, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 30, TO_DATE('2025-01-18','YYYY-MM-DD'), '22:00', 1, 40)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 30, TO_DATE('2025-01-19','YYYY-MM-DD'), '21:30', 3, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 31, TO_DATE('2025-01-17','YYYY-MM-DD'), '18:00', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 31, TO_DATE('2025-01-18','YYYY-MM-DD'), '20:30', 4, 40)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 31, TO_DATE('2025-01-19','YYYY-MM-DD'), '16:00', 1, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 32, TO_DATE('2025-01-17','YYYY-MM-DD'), '20:00', 3, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 32, TO_DATE('2025-01-18','YYYY-MM-DD'), '22:30', 1, 30)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 32, TO_DATE('2025-01-19','YYYY-MM-DD'), '20:30', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 32, TO_DATE('2025-01-20','YYYY-MM-DD'), '18:00', 4, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 33, TO_DATE('2025-01-17','YYYY-MM-DD'), '22:00', 4, 40)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 33, TO_DATE('2025-01-18','YYYY-MM-DD'), '19:30', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 33, TO_DATE('2025-01-19','YYYY-MM-DD'), '22:30', 1, 30)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 34, TO_DATE('2025-01-18','YYYY-MM-DD'), '17:00', 3, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 34, TO_DATE('2025-01-19','YYYY-MM-DD'), '20:00', 1, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 34, TO_DATE('2025-01-20','YYYY-MM-DD'), '18:30', 2, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 34, TO_DATE('2025-01-21','YYYY-MM-DD'), '21:00', 4, 40)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 35, TO_DATE('2025-01-18','YYYY-MM-DD'), '16:00', 4, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 35, TO_DATE('2025-01-19','YYYY-MM-DD'), '18:30', 2, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 35, TO_DATE('2025-01-20','YYYY-MM-DD'), '20:00', 3, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 36, TO_DATE('2025-01-18','YYYY-MM-DD'), '21:00', 1, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 36, TO_DATE('2025-01-19','YYYY-MM-DD'), '19:00', 3, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 36, TO_DATE('2025-01-20','YYYY-MM-DD'), '21:30', 2, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 37, TO_DATE('2025-01-19','YYYY-MM-DD'), '16:30', 2, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 37, TO_DATE('2025-01-20','YYYY-MM-DD'), '19:00', 4, 60)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 37, TO_DATE('2025-01-21','YYYY-MM-DD'), '17:30', 1, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 37, TO_DATE('2025-01-22','YYYY-MM-DD'), '20:30', 3, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 38, TO_DATE('2025-01-19','YYYY-MM-DD'), '18:00', 1, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 38, TO_DATE('2025-01-20','YYYY-MM-DD'), '21:00', 3, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 38, TO_DATE('2025-01-21','YYYY-MM-DD'), '19:30', 2, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 39, TO_DATE('2025-01-20','YYYY-MM-DD'), '17:00', 4, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 39, TO_DATE('2025-01-21','YYYY-MM-DD'), '20:00', 2, 75)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 39, TO_DATE('2025-01-22','YYYY-MM-DD'), '18:30', 1, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 39, TO_DATE('2025-01-23','YYYY-MM-DD'), '21:30', 3, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 40, TO_DATE('2025-01-20','YYYY-MM-DD'), '22:00', 1, 35)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 40, TO_DATE('2025-01-21','YYYY-MM-DD'), '22:30', 3, 40)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 40, TO_DATE('2025-01-22','YYYY-MM-DD'), '21:00', 4, 50)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 41, TO_DATE('2025-01-20','YYYY-MM-DD'), '16:00', 2, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 41, TO_DATE('2025-01-21','YYYY-MM-DD'), '18:30', 4, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 41, TO_DATE('2025-01-22','YYYY-MM-DD'), '20:30', 1, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 41, TO_DATE('2025-01-23','YYYY-MM-DD'), '17:00', 3, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 42, TO_DATE('2025-01-21','YYYY-MM-DD'), '16:00', 3, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 42, TO_DATE('2025-01-22','YYYY-MM-DD'), '19:00', 1, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 42, TO_DATE('2025-01-23','YYYY-MM-DD'), '17:30', 2, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 43, TO_DATE('2025-01-21','YYYY-MM-DD'), '12:00', 2, 120)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 43, TO_DATE('2025-01-22','YYYY-MM-DD'), '15:30', 4, 100)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 43, TO_DATE('2025-01-23','YYYY-MM-DD'), '12:00', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 43, TO_DATE('2025-01-24','YYYY-MM-DD'), '16:00', 3, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 44, TO_DATE('2025-01-21','YYYY-MM-DD'), '11:00', 1, 110)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 44, TO_DATE('2025-01-22','YYYY-MM-DD'), '13:30', 3, 95)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 44, TO_DATE('2025-01-23','YYYY-MM-DD'), '11:30', 2, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 45, TO_DATE('2025-01-22','YYYY-MM-DD'), '17:00', 2, 80)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 45, TO_DATE('2025-01-23','YYYY-MM-DD'), '19:30', 4, 65)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 45, TO_DATE('2025-01-24','YYYY-MM-DD'), '16:30', 1, 90)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 45, TO_DATE('2025-01-25','YYYY-MM-DD'), '20:00', 3, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 46, TO_DATE('2025-01-22','YYYY-MM-DD'), '18:00', 4, 70)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 46, TO_DATE('2025-01-23','YYYY-MM-DD'), '20:30', 2, 55)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 46, TO_DATE('2025-01-24','YYYY-MM-DD'), '18:30', 1, 85)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 47, TO_DATE('2025-01-22','YYYY-MM-DD'), '21:30', 1, 45)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 47, TO_DATE('2025-01-23','YYYY-MM-DD'), '22:00', 3, 35)");
            stmt.executeUpdate("INSERT INTO Sesiones VALUES (seqSesiones.NEXTVAL, 47, TO_DATE('2025-01-24','YYYY-MM-DD'), '21:00', 2, 60)");
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
