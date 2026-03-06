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

            // --- CREACIÓN DE TABLAS ---
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
                    id NUMBER(4) CONSTRAINT PK_JUGADOR PRIMARY KEY,
                    nombre VARCHAR2(100),
                    apellidos VARCHAR2(100),
                    nick VARCHAR2(50),
                    imagen VARCHAR2(500)
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE PARTIDAS(
                    id NUMBER(5) CONSTRAINT PK_PARTIDAS PRIMARY KEY,
                    idJuego NUMBER(3) CONSTRAINT FK_PARTIDAS_JUEGOS REFERENCES JUEGOS,
                    idGana NUMBER(4) CONSTRAINT FK_PARTIDAS_JUGADORES_GANA REFERENCES JUGADORES,
                    idPierde NUMBER(4) CONSTRAINT FK_PARTIDAS_JUGADORES_PIERDE REFERENCES JUGADORES,
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

            // --- DATOS: JUEGOS (45) ---
            String[] titulos = {
                "League of Legends", "Valorant", "Counter-Strike 2", "Rocket League", "Chess.com",
                "Street Fighter 6", "EA Sports FC 24", "Dota 2", "Overwatch 2", "Apex Legends",
                "Starcraft II", "Tekken 8", "Gran Turismo 7", "Hearthstone", "Teamfight Tactics",
                "PUBG", "Rainbow Six Siege", "League of Legends: Wild Rift", "Brawl Stars",
                "NBA 2K24", "Mortal Kombat 1", "F1 24", "Age of Empires IV",
                "Halo Infinite", "Call of Duty: Warzone", "Fortnite", "Minecraft Championships",
                "Clash Royale", "FIFA Online 4", "Smite 2", "Heroes of the Storm",
                "Warcraft III: Reforged", "Battlerite", "Paladins", "Quake Champions",
                "Splitgate", "Diabotical", "Eternal Return", "Omega Strikers",
                "Super Auto Pets", "Legends of Runeterra", "Marvel Snap", "Pokémon Unite",
                "Wild Rift", "Deadlock"
            };
            String[] tipos = {
                "MOBA", "Tactical Shooter", "Tactical Shooter", "Sports", "Board Game",
                "Fighting", "Sports", "MOBA", "Hero Shooter", "Battle Royale",
                "RTS", "Fighting", "Racing", "Card Game", "Auto-Battler",
                "Battle Royale", "Tactical Shooter", "MOBA Mobile", "Action Mobile",
                "Sports", "Fighting", "Racing", "RTS",
                "Hero Shooter", "Battle Royale", "Battle Royale", "Adventure",
                "Strategy Mobile", "Sports", "MOBA", "MOBA",
                "RTS", "Arena Brawler", "Hero Shooter", "Arena Shooter",
                "Hybrid Shooter", "Arena Shooter", "Battle Royale", "Sports Brawler",
                "Auto-Battler", "Card Game", "Card Game", "MOBA Mobile",
                "MOBA Mobile", "Hero Shooter"
            };

            for (int i = 0; i < titulos.length; i++) {
                stmt.executeUpdate("""
                    INSERT INTO JUEGOS (id, titulo, descripcion, tipo, imagen) 
                    VALUES (seqJuegos.NEXTVAL, '%s', 'Torneo oficial de %s', '%s', 'img/juego%d.png')
                    """.formatted(titulos[i], titulos[i], tipos[i], i + 1));
            }

            // --- DATOS: JUGADORES (348) ---
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
                {"Tomás", "Giner", "Tomas_G"},
                // 64 originales arriba, 284 nuevos abajo
                {"Alejandro", "Fuentes", "Alejo_F"}, {"Patricia", "Montoya", "Patri_M"}, {"Roberto", "Campos", "Rob_C"},
                {"Silvia", "Ortega", "Silvi_O"}, {"Álvaro", "Prieto", "Alva_P"}, {"Natalia", "Cabrera", "Nata_C"},
                {"Diego", "Flores", "Diego_F"}, {"Verónica", "Reyes", "Vero_R"}, {"Héctor", "Ibarra", "Hector_I"},
                {"Carmen", "Molina", "Carmi_M"}, {"Eduardo", "Varela", "Edu_V"}, {"Lorena", "Quintero", "Lore_Q"},
                {"Pedro", "Aguilar", "Pedro_A"}, {"Susana", "Herrera", "Susi_H"}, {"Antonio", "Vázquez", "Toni_V"},
                {"Rebeca", "Peña", "Rebe_P"}, {"Carlos", "Medina", "Carl_M"}, {"Pilar", "Delgado", "Pili_D"},
                {"Enrique", "Castillo", "Enri_C"}, {"Teresa", "Jiménez", "Tere_J"}, {"Manuel", "Pardo", "Manu_P"},
                {"Ana", "Nieto", "Ana_N"}, {"Francisco", "Serrano", "Fran_S"}, {"Alicia", "Cortés", "Ali_C"},
                {"Miguel", "Guerrero", "Migu_G"}, {"Beatriz", "Navarro", "Bea_Nav"}, {"Juan", "Bermejo", "Juan_B"},
                {"Rosa", "Ferrer", "Rosa_F"}, {"Pablo", "Cervantes", "Pablo_C"}, {"Laura", "Quiroga", "Lau_Q"},
                {"Sergio", "Mendoza", "Serg_M"}, {"Amalia", "Briones", "Ama_B"}, {"Rodrigo", "Lozano", "Rod_L"},
                {"Cristina", "Pizarro", "Cris_P"}, {"Guillermo", "Tapia", "Guille_T"}, {"Marina", "Espejo", "Mari_E"},
                {"Ignacio", "Cuesta", "Igna_C"}, {"Claudia", "Benítez", "Clau_B"}, {"Alfredo", "Mora", "Alfre_M"},
                {"Leticia", "Bernal", "Leti_B"}, {"Ernesto", "Giménez", "Ernes_G"}, {"Fátima", "Pascual", "Fati_P"},
                {"Álvaro", "Cruz", "Alva_C"}, {"Helena", "Domínguez", "Hele_D"}, {"Germán", "Acosta", "Ger_A"},
                {"Olga", "Fuente", "Olga_F"}, {"Jesús", "Morales", "Jesu_M"}, {"Amparo", "Salinas", "Amp_S"},
                {"Raúl", "Escribano", "Raul_E"}, {"Dolores", "Castro", "Dolo_C"}, {"Emilio", "Rubio", "Emi_R"},
                {"Concepción", "Iglesias", "Conch_I"}, {"Nicolás", "Rivas", "Nico_R"}, {"Esperanza", "Suárez", "Espe_S"},
                {"Julián", "Gallego", "Juli_G"}, {"Valentina", "Pons", "Vale_P"}, {"Andrés", "Carrasco", "Andre_C"},
                {"Natalia", "Ferreira", "Nata_F"}, {"Ángel", "Navarro", "Angel_N"}, {"Cristóbal", "Roca", "Crist_R"},
                {"Josefa", "Dominguez", "Pepa_D"}, {"Lorenzo", "Bautista", "Loren_B"}, {"Marta", "Villanueva", "Marta_Vi"},
                {"Xavier", "Soler", "Xavi_S"}, {"Tamara", "Aguilera", "Tama_A"}, {"Roberto", "Cifuentes", "Rob_Ci"},
                {"Sonia", "Leal", "Soni_L"}, {"Ezequiel", "Montero", "Eze_M"}, {"Nadia", "Prieto", "Nadi_P"},
                {"Bartolomé", "Vega", "Barto_V"}, {"Hortensia", "Blanco", "Hort_B"}, {"Asier", "Zabala", "Asier_Z"},
                {"Leyre", "Etxeberria", "Leyre_E"}, {"Unai", "Mendez", "Unai_M"}, {"Ane", "Larrea", "Ane_L"},
                {"Mikel", "Arrizabalaga", "Mikel_A"}, {"Garazi", "Zubiria", "Gara_Z"}, {"Jon", "Goikoetxea", "Jon_G"},
                {"Itziar", "Altuna", "Itz_A"}, {"Xabi", "Zulueta", "Xabi_Z"}, {"Amaia", "Etxezarreta", "Ama_E"},
                {"Hodei", "Larrañaga", "Hodei_L"}, {"Uxue", "Irazusta", "Uxue_I"}, {"Eneko", "Olaizola", "Ene_O"},
                {"Naroa", "Uribe", "Naro_U"}, {"Oier", "Berasategi", "Oier_B"}, {"Maialen", "Zubiaurre", "Maia_Z"},
                {"Julen", "Azcue", "Jules_A"}, {"Nora", "Kortazar", "Nora_K"}, {"Danel", "Egiguren", "Dane_E"},
                {"Ainhara", "Urrutia", "Ainh_U"}, {"Ander", "Aranguren", "Ande_A"}, {"Alaitz", "Etxegoien", "Alai_E"},
                {"Patxi", "Garmendia", "Patxi_G"}, {"Idoia", "Ibarguren", "Idoi_I"}, {"Imanol", "Iriondo", "Ima_I"},
                {"Eider", "Olazabal", "Eid_O"}, {"Lander", "Mujika", "Land_M"}, {"Esti", "Sarasola", "Esti_S"},
                {"Iñaki", "Rekondo", "Inaki_R"}, {"Olatz", "Zubizarreta", "Olatz_Z"}, {"Kerman", "Garitano", "Ker_G"},
                {"Miren", "Laskibar", "Mire_L"}, {"Gorka", "Azpiri", "Gorka_A"}, {"Haizea", "Mintegi", "Haiz_M"},
                {"Txomin", "Uriarte", "Txo_U"}, {"Naiara", "Zubimendi", "Naia_Z"}, {"Beñat", "Artetxe", "Ben_A"},
                {"Arantxa", "Goñi", "Aran_G"}, {"Markel", "Uranga", "Mark_U"}, {"Ione", "Zuazo", "Ione_Z"},
                {"Luca", "Ferrari", "Luca_F"}, {"Giulia", "Ricci", "Giul_R"}, {"Marco", "Esposito", "Marc_E"},
                {"Chiara", "Romano", "Chia_R"}, {"Alessandro", "Conti", "Ales_C"}, {"Francesca", "Lombardi", "Fra_L"},
                {"Matteo", "Gallo", "Matt_G"}, {"Elisa", "Rizzo", "Elis_R"}, {"Lorenzo", "Mancini", "Lore_M"},
                {"Sofia", "Greco", "Sofi_G"}, {"Davide", "Russo", "Davi_R"}, {"Alice", "De Luca", "Alic_DL"},
                {"Gabriel", "Marini", "Gabri_M"}, {"Anna", "Ferrero", "Anna_F"}, {"Filippo", "Bruno", "Fili_B"},
                {"Valentina", "Santoro", "Val_San"}, {"Edoardo", "Caruso", "Edo_C"}, {"Camilla", "Ferretti", "Cami_F"},
                {"Simone", "Gatti", "Simo_G"}, {"Beatrice", "Montanari", "Beat_Mo"}, {"Nicolas", "Dupont", "Nico_D"},
                {"Camille", "Martin", "Cami_Ma"}, {"Antoine", "Dubois", "Anto_D"}, {"Manon", "Bernard", "Man_B"},
                {"Thomas", "Petit", "Tho_P"}, {"Juliette", "Moreau", "Juli_Mo"}, {"Baptiste", "Simon", "Bap_S"},
                {"Léa", "Laurent", "Lea_L"}, {"Hugo", "Michel", "Hugo_M"}, {"Inès", "Leroy", "Ines_Le"},
                {"Théo", "David", "Theo_D"}, {"Océane", "Robert", "Ocea_R"}, {"Maxime", "Richard", "Max_R"},
                {"Pauline", "Thomas", "Pau_T"}, {"Lucas", "Blanc", "Luca_Bl"}, {"Emma", "Girard", "Emma_G"},
                {"Nathan", "Lefevre", "Nath_L"}, {"Clara", "Roux", "Clara_R"}, {"Alexis", "Garnier", "Alex_Ga"},
                {"Charlotte", "Morel", "Char_M"}, {"Kevin", "André", "Kev_A"}, {"Lucie", "Faure", "Lucy_F"},
                {"Romain", "Mercier", "Roma_M"}, {"Mathilde", "Lefebvre", "Math_L"}, {"Louis", "Rousseau", "Loui_R"},
                {"Jade", "Vincent", "Jade_V"}, {"Pierre", "Fontaine", "Pier_F"}, {"Marion", "Guerin", "Mari_Gu"},
                {"Julien", "Chevalier", "Juli_C"}, {"Elsa", "Muller", "Elsa_M"}, {"Simon", "Giraud", "Simo_Gi"},
                {"Anaïs", "Bonnet", "Anai_B"}, {"Florian", "Lambert", "Flor_L"}, {"Margot", "Denis", "Marg_D"},
                {"Dylan", "Perrin", "Dyla_P"}, {"Zoé", "Clement", "Zoe_C"}, {"Robin", "Roussel", "Robi_R"},
                {"Lilou", "Renard", "Lil_R"}, {"Adrien", "Barbier", "Adri_B"}, {"Noémie", "Colin", "Noem_C"},
                {"Valentin", "Lemaire", "Vale_Le"}, {"Justine", "Marchand", "Just_M"}, {"Quentin", "Arnaud", "Que_A"},
                {"Laetitia", "Olivier", "Laet_O"}, {"Sebastien", "Picard", "Seba_P"}, {"Amelie", "Legrand", "Amel_L"},
                {"Liam", "Smith", "Liam_S"}, {"Emma", "Jones", "Emma_J"}, {"Noah", "Williams", "Noah_W"},
                {"Olivia", "Brown", "Oliv_B"}, {"Ethan", "Taylor", "Eth_T"}, {"Ava", "Anderson", "Ava_A"},
                {"Mason", "Johnson", "Maso_J"}, {"Isabella", "Davis", "Isa_D"}, {"Logan", "Miller", "Log_M"},
                {"Sophia", "Wilson", "Soph_W"}, {"Lucas", "Moore", "Luca_Mo"}, {"Mia", "Martin", "Mia_Ma"},
                {"Jackson", "Thompson", "Jack_T"}, {"Charlotte", "Garcia", "Char_G"}, {"Aiden", "Martinez", "Aid_M"},
                {"Amelia", "Robinson", "Ame_R"}, {"Caleb", "Clark", "Cal_C"}, {"Harper", "Rodriguez", "Harp_R"},
                {"Sebastian", "Lewis", "Seba_L"}, {"Evelyn", "Lee", "Eve_L"}, {"Owen", "Walker", "Owe_W"},
                {"Abigail", "Hall", "Abi_H"}, {"Carter", "Allen", "Cart_A"}, {"Emily", "Young", "Emi_Y"},
                {"Wyatt", "Hernandez", "Wya_H"}, {"Elizabeth", "King", "Eli_K"}, {"Dylan", "Wright", "Dyla_W"},
                {"Sofia", "Lopez", "Sofi_L"}, {"Jayden", "Hill", "Jayd_H"}, {"Avery", "Scott", "Ave_S"},
                {"Gabriel", "Green", "Gabr_G"}, {"Ella", "Adams", "Ella_A"}, {"Julian", "Baker", "Ju_B"},
                {"Scarlett", "Gonzalez", "Scar_G"}, {"Matias", "Nelson", "Mati_N"}, {"Grace", "Carter", "Grac_C"},
                {"Isaac", "Mitchell", "Isaa_M"}, {"Chloe", "Perez", "Chlo_P"}, {"Nolan", "Roberts", "Nola_R"},
                {"Penelope", "Turner", "Pen_T"}, {"Eli", "Phillips", "Eli_Ph"}, {"Riley", "Campbell", "Rile_C"},
                {"Jaxon", "Parker", "Jax_P"}, {"Layla", "Evans", "Layl_E"}, {"Levi", "Edwards", "Levi_E"},
                {"Lillian", "Collins", "Lill_C"}, {"Aaron", "Stewart", "Aaro_S"}, {"Hannah", "Sanchez", "Hann_S"},
                {"Thomas", "Morris", "Tho_M"}, {"Addison", "Rogers", "Add_R"}, {"Charles", "Reed", "Char_Re"},
                {"Zoey", "Cook", "Zoey_C"}, {"Henry", "Morgan", "Henr_M"}, {"Brooklyn", "Bell", "Brook_B"},
                {"Hudson", "Murphy", "Hud_M"}, {"Violet", "Bailey", "Vio_B"}, {"Adrian", "Rivera", "Adri_R"},
                {"Samantha", "Cooper", "Sam_C"}, {"Ivan", "Richardson", "Ivan_R"}, {"Naomi", "Cox", "Naom_C"},
                {"Leo", "Howard", "Leo_H"}, {"Leah", "Ward", "Leah_W"}, {"Jonah", "Torres", "Jon_T"},
                {"Sofia", "Peterson", "Sofi_P"}, {"Jeremiah", "Gray", "Jere_G"}, {"Aria", "Ramirez", "Aria_R"},
                {"Elias", "James", "Eli_J"}, {"Audrey", "Watson", "Aud_W"}, {"Colton", "Brooks", "Col_B"},
                {"Kaylee", "Kelly", "Kayl_K"}, {"Jordan", "Sanders", "Jord_S"}, {"Allison", "Price", "Alli_P"},
                {"Hunter", "Bennett", "Hunt_B"}, {"Anna", "Wood", "Anna_W"}, {"Axel", "Barnes", "Axel_B"},
                {"Victoria", "Ross", "Vict_R"}, {"Ian", "Henderson", "Ian_H"}, {"Stella", "Coleman", "Stel_C"},
                {"Lincoln", "Jenkins", "Linc_J"}, {"Natalie", "Perry", "Nata_Pe"}, {"Christopher", "Powell", "Chri_P"},
                {"Aurora", "Long", "Auro_L"}, {"Anthony", "Patterson", "Anth_P"}, {"Savannah", "Hughes", "Sava_H"},
                {"Joshua", "Flores", "Josh_F"}, {"Paisley", "Washington", "Pais_W"}, {"Andrew", "Butler", "Andr_B"},
                {"Claire", "Simmons", "Clai_S"}, {"Samuel", "Foster", "Samu_F"}, {"Skylar", "Gonzales", "Sky_G"},
                {"David", "Bryant", "Dav_Br"}, {"Camila", "Alexander", "Cami_Al"}, {"Joseph", "Russell", "Jose_R"},
                {"Ariana", "Griffin", "Aria_Gr"}, {"John", "Diaz", "John_D"}, {"Aubrey", "Hayes", "Aub_H"},
                {"Eli", "Myers", "Eli_My"}, {"Lexi", "Ford", "Lexi_F"}, {"Wyatt", "Hamilton", "Wya_Ha"},
                {"Riley", "Graham", "Rile_Gr"}, {"Ryan", "Sullivan", "Ryan_S"}, {"Kylie", "Wallace", "Kyli_W"},
                {"Nicholas", "Woods", "Nich_W"}, {"Madelyn", "Cole", "Made_C"}, {"Tyler", "West", "Tyle_W"},
                {"Alexis", "Jordan", "Alex_J"}, {"Josiah", "Owens", "Josi_O"}, {"Genesis", "Reynolds", "Gene_R"},
                {"Landon", "Fisher", "Land_F"}, {"Destiny", "Ellis", "Dest_E"}, {"Jonathan", "Harrison", "Jona_H"},
                {"Sophie", "Gibson", "Soph_G"}, {"Gavin", "Mcdonald", "Gav_Mc"}, {"Everly", "Cruz", "Ever_C"},
                {"Cooper", "Marshall", "Coop_M"}, {"Cora", "Ortiz", "Cora_O"}, {"Evan", "Gomez", "Evan_G"},
                {"Willow", "Murray", "Will_M"}, {"Carson", "Freeman", "Cars_F"}, {"Adalyn", "Wells", "Adal_W"},
                {"Bentley", "Webb", "Bent_W"}, {"Peyton", "Simpson", "Peyt_S"}, {"Brayden", "Stevens", "Bray_S"},
                {"Kennedy", "Tucker", "Kenn_T"}, {"Easton", "Porter", "East_P"}, {"Mackenzie", "Hunter", "Mack_H"},
                {"Asher", "Hicks", "Ash_H"}, {"Sadie", "Crawford", "Sadi_C"}, {"Roman", "Henry", "Roma_H"},
                {"Jade", "Warren", "Jade_W"}, {"Miles", "Romero", "Mile_R"}, {"Clara", "Dixon", "Clara_D"},
                {"Declan", "Hawkins", "Dec_H"}, {"Piper", "Arnold", "Pip_A"}, {"Silas", "Kim", "Silas_K"},
                {"Madeline", "Rice", "Made_R"}, {"Tobias", "Stone", "Tobi_S"}, {"Eva", "Hunt", "Eva_H"},
                {"Elliot", "Barnes", "Ell_Ba"}, {"Brielle", "Robinson", "Brie_R"}, {"Marcus", "Bishop", "Marc_Bi"},
                {"Finley", "Mills", "Finl_M"}, {"Rhys", "Hart", "Rhys_H"}, {"London", "Newton", "Lond_N"},
                {"Enzo", "Young", "Enzo_Y"}, {"Emery", "Grant", "Emer_G"}, {"Oscar", "Knight", "Osca_K"},
                {"Reagan", "Roy", "Reag_R"}, {"Noel", "Freeman", "Noel_F"}, {"Rowan", "Hudson", "Rowa_H"}
            };

            for (String[] j : jugadores) {
                stmt.executeUpdate("""
                    INSERT INTO JUGADORES (id, nombre, apellidos, nick, imagen) 
                    VALUES (seqJugadores.NEXTVAL, '%s', '%s', '%s', 'img/perfil_%s.jpg')
                    """.formatted(j[0], j[1], j[2], j[2].toLowerCase()));
            }

            // --- DATOS: PARTIDAS (1634) ---
            Random rnd = new Random();
            String[] duracionesPosibles = {"5 min", "12 min", "25 min", "40 min", "55 min", "8 min", "18 min", "33 min", "47 min", "62 min"};

            for (int i = 0; i < 1634; i++) {
                int idJuego = rnd.nextInt(titulos.length) + 1;
                int idGana = rnd.nextInt(jugadores.length) + 1;
                int idPierde;
                do {
                    idPierde = rnd.nextInt(jugadores.length) + 1;
                } while (idGana == idPierde);

                int puntos = rnd.nextInt(500) + 10;
                String duracion = duracionesPosibles[rnd.nextInt(duracionesPosibles.length)];
                int diasAtras = rnd.nextInt(180);

                stmt.executeUpdate("""
                    INSERT INTO PARTIDAS (id, idJuego, idGana, idPierde, puntos, duracion, fecha) 
                    VALUES (seqPartidas.NEXTVAL, %d, %d, %d, %d, '%s', SYSDATE - %d)
                    """.formatted(idJuego, idGana, idPierde, puntos, duracion, diasAtras));
            }
            
            cnn.commit();
            System.out.println("Base de datos inicializada: 45 juegos, 348 jugadores y 1634 partidas.");

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
