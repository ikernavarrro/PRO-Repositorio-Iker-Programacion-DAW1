package org.zabalburu.daw1.aplicaciontorneo.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

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

            // --- BORRADO DE TABLAS ---
            try {
                stmt.executeUpdate("DROP TABLE PARTIDAS");
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("DROP TABLE JUGADORES");
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("DROP TABLE JUEGOS");
            } catch (SQLException ex) {
            }

            cnn.setAutoCommit(false);

            // --- CREACIÓN DE TABLAS (CON PRECISIÓN CORREGIDA) ---
            stmt.executeUpdate("""
                CREATE TABLE JUEGOS(
                    id NUMBER(3) CONSTRAINT PK_JUEGOS PRIMARY KEY,
                    titulo VARCHAR2(100),
                    descripcion VARCHAR2(200),
                    tipo VARCHAR2(50),
                    imagen VARCHAR2(100)
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE JUGADORES(
                    id NUMBER(3) CONSTRAINT PK_JUGADOR PRIMARY KEY,
                    nombre VARCHAR2(100),
                    apellidos VARCHAR2(100),
                    nick VARCHAR2(50),
                    imagen VARCHAR2(100)
                )
                """);

            // Hemos subido el ID de Partidas a NUMBER(4) para que acepte hasta 9999
            stmt.executeUpdate("""
                CREATE TABLE PARTIDAS(
                    id NUMBER(4) CONSTRAINT PK_PARTIDAS PRIMARY KEY,
                    idJuego NUMBER(3) CONSTRAINT FK_PARTIDAS_JUEGOS REFERENCES JUEGOS,
                    idGana NUMBER(3) CONSTRAINT FK_PARTIDAS_JUGADORES_GANA REFERENCES JUGADORES,
                    idPierde NUMBER(3) CONSTRAINT FK_PARTIDAS_JUGADORES_PIERDE REFERENCES JUGADORES,
                    puntos NUMBER(4),
                    duracion VARCHAR2(50),
                    fecha DATE
                )
                """);

            // --- REINICIO DE SECUENCIAS ---
            String[] secuencias = {"seqJuegos", "seqJugadores", "seqPartidas"};
            for (String secuencia : secuencias) {
                try {
                    stmt.executeUpdate("DROP SEQUENCE %s".formatted(secuencia));
                } catch (SQLException ex) {
                }
                stmt.executeUpdate("CREATE SEQUENCE %s".formatted(secuencia));
            }

            // --- DATOS: JUEGOS (23) ---
            String[] titulos = {
                "League of Legends", "Valorant", "Counter-Strike 2", "Rocket League", "Chess.com",
                "Street Fighter 6", "EA Sports FC 24", "Dota 2", "Overwatch 2", "Apex Legends",
                "Starcraft II", "Tekken 8", "Gran Turismo 7", "Hearthstone", "Teamfight Tactics",
                "PUBG", "Rainbow Six Siege", "League of Legends: Wild Rift", "Brawl Stars",
                "NBA 2K24", "Mortal Kombat 1", "F1 24", "Age of Empires IV"
            };
            String[] tipos = {
                "MOBA", "Tactical Shooter", "Tactical Shooter", "Sports", "Board Game",
                "Fighting", "Sports", "MOBA", "Hero Shooter", "Battle Royale",
                "RTS", "Fighting", "Racing", "Card Game", "Auto-Battler",
                "Battle Royale", "Tactical Shooter", "MOBA Mobile", "Action Mobile",
                "Sports", "Fighting", "Racing", "RTS"
            };

            for (int i = 0; i < titulos.length; i++) {
                stmt.executeUpdate("""
                    INSERT INTO JUEGOS (id, titulo, descripcion, tipo, imagen) 
                    VALUES (seqJuegos.NEXTVAL, '%s', 'Torneo oficial de %s', '%s', 'img/juego%d.png')
                    """.formatted(titulos[i], titulos[i], tipos[i], i + 1));
            }

            // --- DATOS: JUGADORES (64) ---
            String[][] jugadores = {
                {"Iker", "Navarro", "IkerNP"}, {"Sara", "García", "Sarinha"}, {"Marc", "Torres", "MT_Ghost"},
                {"Elena", "Rodríguez", "EleStar"}, {"Alex", "Sánchez", "AlexKiller"}, {"Lucía", "Martín", "Lulu99"},
                {"David", "Pérez", "DavoRider"}, {"Marta", "López", "MartaV"}, {"Hugo", "Gómez", "HugoBoss"},
                {"Paula", "Ruiz", "PauPau"}, {"Javier", "Hernández", "JaviPro"}, {"Sofía", "Díaz", "Sofi_"},
                {"Daniel", "Moreno", "DaniM"}, {"Carla", "Muñoz", "CarlaGamer"}, {"Mario", "Álvarez", "Mario_Kart"},
                {"Julia", "Romero", "Jules"}, {"Adrián", "Alonso", "AdriFlow"}, {"Clara", "Gutiérrez", "Clara_Z"},
                {"Iván", "Navas", "Ivanhoe"}, {"Nerea", "Sanz", "Nery"}, {"Raúl", "Iglesias", "Raul_9"},
                {"Inés", "Cano", "Ines_it"}, {"Óscar", "Gil", "Oskitar"}, {"Noelia", "Vidal", "Noe_V"},
                {"Rubén", "Blanco", "Ruby"}, {"Fernando", "Moro", "Fer_Alpha"}, {"Gema", "Soto", "Gems"},
                {"Mateo", "Pascual", "Teo_X"}, {"Alba", "Vargas", "Alba_V"}, {"Lucas", "Diez", "LukeSky"},
                {"Irene", "Crespo", "Ire_98"}, {"Marcos", "León", "LionKing"}, {"Lola", "Méndez", "Lola_Win"},
                {"Víctor", "Soler", "Vic_Master"}, {"Rocío", "Ramos", "Ro_Ramos"}, {"Saúl", "Duarte", "Saul_G"},
                {"Ainhoa", "Moya", "Ainhoa_M"}, {"Gonzalo", "Marín", "Gonza_Pro"}, {"Beatriz", "Vega", "Bea_V"},
                {"Santi", "Calvo", "Santi_C"}, {"Celia", "Esteban", "Celia_E"}, {"Felipe", "Guerra", "Pipe_G"},
                {"Nuria", "Ibáñez", "Nuria_I"}, {"Xavier", "Bosch", "Xavi_B"}, {"Maite", "Espinosa", "Maite_E"},
                {"Borja", "Vila", "Borja_V"}, {"Esther", "Lorenzo", "Esther_L"}, {"Rafa", "Guillen", "Rafa_G"},
                {"Tania", "Bravo", "Tania_B"}, {"Eloy", "Serrano", "Eloy_S"}, {"Belén", "Rey", "Belen_R"},
                {"Jaime", "Gallardo", "Jaime_G"}, {"Lidia", "Casas", "Lidia_C"}, {"Paco", "Maza", "Paco_M"},
                {"Miriam", "Lara", "Miriam_L"}, {"Aitor", "Sanz", "Aitor_S"}, {"Vanesa", "Duro", "Vane_D"},
                {"Ismael", "Blanes", "Isma_B"}, {"Sandra", "Fornes", "Sandra_F"}, {"Quique", "López", "Quique_L"},
                {"Yolanda", "Parra", "Yoli_P"}, {"Jordi", "Valls", "Jordi_V"}, {"Mónica", "Roda", "Moni_R"},
                {"Tomás", "Giner", "Tomas_G"}
            };

            for (String[] j : jugadores) {
                stmt.executeUpdate("""
                    INSERT INTO JUGADORES (id, nombre, apellidos, nick, imagen) 
                    VALUES (seqJugadores.NEXTVAL, '%s', '%s', '%s', 'img/perfil_%s.jpg')
                    """.formatted(j[0], j[1], j[2], j[2].toLowerCase()));
            }

            // --- DATOS: PARTIDAS (135) ---
            Random rnd = new Random();
            String[] duracionesPosibles = {"5 min", "12 min", "25 min", "40 min", "55 min"};

            for (int i = 0; i < 135; i++) {
                int idJuego = rnd.nextInt(titulos.length) + 1;
                int idGana = rnd.nextInt(jugadores.length) + 1;
                int idPierde;
                do {
                    idPierde = rnd.nextInt(jugadores.length) + 1;
                } while (idGana == idPierde);

                int puntos = rnd.nextInt(300) + 10;
                String duracion = duracionesPosibles[rnd.nextInt(duracionesPosibles.length)];
                int diasAtras = rnd.nextInt(60);

                stmt.executeUpdate("""
                    INSERT INTO PARTIDAS (id, idJuego, idGana, idPierde, puntos, duracion, fecha) 
                    VALUES (seqPartidas.NEXTVAL, %d, %d, %d, %d, '%s', SYSDATE - %d)
                    """.formatted(idJuego, idGana, idPierde, puntos, duracion, diasAtras));
            }

            cnn.commit();
            System.out.println("Base de datos inicializada: 23 juegos, 64 jugadores y 135 partidas.");

        } catch (SQLException ex) {
            try {
                if (cnn != null) {
                    cnn.rollback();
                }
            } catch (SQLException exc) {
            }
        }
    }
}
