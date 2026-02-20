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
                               DROP SEQUENCE %s
                               """.formatted(secuencia));
                } catch (SQLException ex) {
                }
                stmt.executeUpdate("""
                               CREATE SEQUENCE %s
                               """.formatted(secuencia));
            }

            // --- INSERCIÓN DE JUEGOS (7) ---
            String[] titulos = {"League of Legends", "Valorant", "Counter-Strike 2", "Rocket League", "Chess.com", "Street Fighter 6", "EA Sports FC 24"};
            String[] tipos = {"MOBA", "Tactical Shooter", "Tactical Shooter", "Sports", "Board Game", "Fighting", "Sports"};
            for (int i = 0; i < titulos.length; i++) {
                stmt.executeUpdate("""
                    INSERT INTO JUEGOS (id, titulo, descripcion, tipo, imagen) 
                    VALUES (seqJuegos.NEXTVAL, '%s', 'Torneo oficial de %s', '%s', 'img/juego%d.png')
                    """.formatted(titulos[i], titulos[i], tipos[i], i + 1));
            }

            // --- INSERCIÓN DE JUGADORES (25) ---
            String[][] jugadores = {
                {"Iker", "Navarro", "IkerNP"}, {"Sara", "García", "Sarinha"}, {"Marc", "Torres", "MT_Ghost"},
                {"Elena", "Rodríguez", "EleStar"}, {"Alex", "Sánchez", "AlexKiller"}, {"Lucía", "Martín", "Lulu99"},
                {"David", "Pérez", "DavoRider"}, {"Marta", "López", "MartaV"}, {"Hugo", "Gómez", "HugoBoss"},
                {"Paula", "Ruiz", "PauPau"}, {"Javier", "Hernández", "JaviPro"}, {"Sofía", "Díaz", "Sofi_"},
                {"Daniel", "Moreno", "DaniM"}, {"Carla", "Muñoz", "CarlaGamer"}, {"Mario", "Álvarez", "Mario_Kart"},
                {"Julia", "Romero", "Jules"}, {"Adrián", "Alonso", "AdriFlow"}, {"Clara", "Gutiérrez", "Clara_Z"},
                {"Iván", "Navas", "Ivanhoe"}, {"Nerea", "Sanz", "Nery"}, {"Raúl", "Iglesias", "Raul_9"},
                {"Inés", "Cano", "Ines_it"}, {"Óscar", "Gil", "Oskitar"}, {"Noelia", "Vidal", "Noe_V"},
                {"Rubén", "Blanco", "Ruby"}
            };
            for (String[] j : jugadores) {
                stmt.executeUpdate("""
                    INSERT INTO JUGADORES (id, nombre, apellidos, nick, imagen) 
                    VALUES (seqJugadores.NEXTVAL, '%s', '%s', '%s', 'img/perfil_%s.jpg')
                    """.formatted(j[0], j[1], j[2], j[2].toLowerCase()));
            }

            // --- INSERCIÓN DE PARTIDAS (8) ---
            // El array 'partidas' ahora solo contiene los números (ids y puntos)
            int[][] partidas = {
                {1, 5, 12, 150}, // LoL
                {2, 3, 8, 13}, // Valorant
                {3, 19, 1, 16}, // CS2
                {4, 10, 22, 3}, // Rocket League
                {5, 2, 15, 1}, // Chess
                {6, 7, 25, 2}, // SF6
                {7, 11, 4, 4}, // FC24
                {1, 14, 20, 145} // LoL
            };

            // Array paralelo con las duraciones
            String[] duraciones = {"35 min", "42 min", "28 min", "5 min", "12 min", "8 min", "15 min", "40 min"};

            for (int i = 0; i < partidas.length; i++) {
                stmt.executeUpdate("""
                        INSERT INTO PARTIDAS (id, idJuego, idGana, idPierde, puntos, duracion, fecha) 
                        VALUES (seqPartidas.NEXTVAL, %d, %d, %d, %d, '%s', SYSDATE)
                        """.formatted(
                        partidas[i][0], // idJuego
                        partidas[i][1], // idGana
                        partidas[i][2], // idPierde
                        partidas[i][3], // puntos
                        duraciones[i] // duracion
                ));
            }
            cnn.commit(); // Confirmamos los cambios
            System.out.println("Base de datos inicializada con éxito.");

        } catch (SQLException ex) {
            System.getLogger(InicializarDatos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            try {
                cnn.rollback();
            } catch (SQLException exc) {
            }
        }
    }
}
