/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.servicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.zabalburu.daw1.flintbibliotecalibro_1.dao.BibliotecaDAO;
import org.zabalburu.daw1.flintbibliotecalibro_1.dao.LibroDAO;
import org.zabalburu.daw1.flintbibliotecalibro_1.dao.impl.BibliotecaIMPL;
import org.zabalburu.daw1.flintbibliotecalibro_1.dao.impl.LibroIMPL;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Biblioteca;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Libro;
import org.zabalburu.daw1.flintbibliotecalibro_1.util.Categoria;

/**
 *
 * @author Iker Navarro Pérez
 */
public class BibliotecaServicio {

    private BibliotecaDAO bibliotecaDAO;
    private LibroDAO libroDAO;

    public BibliotecaServicio() {
        this.bibliotecaDAO = new BibliotecaIMPL();
        this.libroDAO = new LibroIMPL();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Crear 30 bibliotecas
        Biblioteca[] bibliotecas = new Biblioteca[30];

        bibliotecas[0] = new Biblioteca(null, "Biblioteca Central de Bilbao", "Calle Autonomía 3, Bilbao", "944 794 060");
        bibliotecas[1] = new Biblioteca(null, "Biblioteca Pública de Santander", "Avenida Reina Victoria 8, Santander", "942 203 564");
        bibliotecas[2] = new Biblioteca(null, "Biblioteca Municipal de Vitoria", "Paseo de la Florida 3, Vitoria", "945 161 858");
        bibliotecas[3] = new Biblioteca(null, "Biblioteca de Gipuzkoa", "Calle Peñaflorida 11, Donostia", "943 481 880");
        bibliotecas[4] = new Biblioteca(null, "Biblioteca Pública de Logroño", "Calle Portales 47, Logroño", "941 291 260");
        bibliotecas[5] = new Biblioteca(null, "Biblioteca Municipal de Pamplona", "Avenida Zaragoza 25, Pamplona", "948 426 641");
        bibliotecas[6] = new Biblioteca(null, "Biblioteca de Castilla y León", "Calle Torrecilla 10, Valladolid", "983 351 000");
        bibliotecas[7] = new Biblioteca(null, "Biblioteca Pública de Burgos", "Plaza Santa María 5, Burgos", "947 288 860");
        bibliotecas[8] = new Biblioteca(null, "Biblioteca Municipal de Palencia", "Calle Mayor 92, Palencia", "979 740 600");
        bibliotecas[9] = new Biblioteca(null, "Biblioteca de Soria", "Calle Nicolás Rabal 8, Soria", "975 221 454");
        bibliotecas[10] = new Biblioteca(null, "Biblioteca Pública de Segovia", "Calle Juan Bravo 40, Segovia", "921 462 697");
        bibliotecas[11] = new Biblioteca(null, "Biblioteca de Ávila", "Calle López Núñez 2, Ávila", "920 211 693");
        bibliotecas[12] = new Biblioteca(null, "Biblioteca Pública de Salamanca", "Calle Compañía 2, Salamanca", "923 291 550");
        bibliotecas[13] = new Biblioteca(null, "Biblioteca Municipal de Zamora", "Calle Santa Clara 2, Zamora", "980 531 061");
        bibliotecas[14] = new Biblioteca(null, "Biblioteca de León", "Avenida Ordoño II 16, León", "987 237 480");
        bibliotecas[15] = new Biblioteca(null, "Biblioteca Pública de Astorga", "Plaza España 1, Astorga", "987 616 838");
        bibliotecas[16] = new Biblioteca(null, "Biblioteca Municipal de Ponferrada", "Calle Ancha 1, Ponferrada", "987 409 760");
        bibliotecas[17] = new Biblioteca(null, "Biblioteca de La Rioja", "Calle Portales 47, Logroño", "941 291 260");
        bibliotecas[18] = new Biblioteca(null, "Biblioteca Pública de Haro", "Plaza Paz 1, Haro", "941 310 693");
        bibliotecas[19] = new Biblioteca(null, "Biblioteca Municipal de Calahorra", "Calle Mercado 1, Calahorra", "941 130 811");
        bibliotecas[20] = new Biblioteca(null, "Biblioteca de Navarra", "Calle Navarrería 1, Pamplona", "948 426 641");
        bibliotecas[21] = new Biblioteca(null, "Biblioteca Pública de Tudela", "Calle Gaztambide 1, Tudela", "948 410 700");
        bibliotecas[22] = new Biblioteca(null, "Biblioteca Municipal de Estella", "Calle Curtidores 1, Estella", "948 546 238");
        bibliotecas[23] = new Biblioteca(null, "Biblioteca de Tafalla", "Plaza Navarra 1, Tafalla", "948 700 500");
        bibliotecas[24] = new Biblioteca(null, "Biblioteca Pública de Olite", "Calle Rúa Mayor 1, Olite", "948 740 640");
        bibliotecas[25] = new Biblioteca(null, "Biblioteca Municipal de Sangüesa", "Calle Mayor 1, Sangüesa", "948 870 329");
        bibliotecas[26] = new Biblioteca(null, "Biblioteca de Jaca", "Calle Ramiro I 1, Jaca", "974 360 098");
        bibliotecas[27] = new Biblioteca(null, "Biblioteca Pública de Huesca", "Calle Coso Alto 1, Huesca", "974 292 700");
        bibliotecas[28] = new Biblioteca(null, "Biblioteca Municipal de Barbastro", "Calle Vía 1, Barbastro", "974 310 528");
        bibliotecas[29] = new Biblioteca(null, "Biblioteca de Teruel", "Calle Amantes 1, Teruel", "978 618 900");

        // Añadir todas las bibliotecas
        for (Biblioteca b : bibliotecas) {
            bibliotecaDAO.addBiblioteca(b);
        }

        // Obtener las bibliotecas para asignarlas a los libros
        for (int i = 0; i < 30; i++) {
            bibliotecas[i] = bibliotecaDAO.getBiblioteca(i + 1);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Array de datos de libros (título, descripción, autor, editorial, fecha, ISBN, categoría)
            String[][] librosData = {
                // Ficción (40 libros)
                {"Cien años de soledad", "Una novela que narra la historia de la familia Buendía en Macondo.", "Gabriel García Márquez", "Editorial Sudamericana", "30/05/1967", "978-8439702017", "FICCION"},
                {"Don Quijote de la Mancha", "Las aventuras de un hidalgo que se cree caballero andante.", "Miguel de Cervantes", "Francisco de Robles", "16/01/1605", "978-8408086994", "FICCION"},
                {"1984", "Novela distópica sobre un régimen totalitario y la lucha por la libertad.", "George Orwell", "Secker & Warburg", "08/06/1949", "978-0451524935", "FICCION"},
                {"Orgullo y prejuicio", "Romance que explora temas de clase social, matrimonio y amor.", "Jane Austen", "T. Egerton", "28/01/1813", "978-0141439518", "FICCION"},
                {"Crimen y castigo", "Historia de un estudiante que comete un crimen y sufre sus consecuencias.", "Fiódor Dostoievski", "The Russian Messenger", "01/01/1866", "978-0199232765", "FICCION"},
                {"La guerra y la paz", "Epopeya sobre la invasión napoleónica de Rusia.", "León Tolstói", "The Russian Messenger", "01/01/1869", "978-0199232772", "FICCION"},
                {"Madame Bovary", "Historia de una mujer insatisfecha con su vida matrimonial.", "Gustave Flaubert", "Revue de Paris", "01/10/1856", "978-0140449129", "FICCION"},
                {"El Quijote", "Las aventuras del caballero Don Quijote de la Mancha.", "Miguel de Cervantes", "Francisco de Robles", "16/01/1605", "978-8408086994", "FICCION"},
                {"Jane Eyre", "Historia de una joven huérfana que se convierte en institutriz.", "Charlotte Brontë", "Smith, Elder & Co", "19/10/1847", "978-0141441146", "FICCION"},
                {"Cumbres borrascosas", "Novela gótica sobre el amor y la venganza en los páramos de Yorkshire.", "Emily Brontë", "Thomas Cautley Newby", "19/12/1847", "978-0141439556", "FICCION"},
                {"El conde de Montecristo", "Historia de venganza y redención de Edmond Dantès.", "Alejandro Dumas", "Journal des Débats", "28/08/1844", "978-0140449266", "FICCION"},
                {"Los tres mosqueteros", "Aventuras de D'Artagnan y sus amigos en la corte de Luis XIII.", "Alejandro Dumas", "Le Siècle", "01/03/1844", "978-0140449273", "FICCION"},
                {"El jorobado de Notre-Dame", "Historia de Quasimodo y Esmeralda en la París medieval.", "Víctor Hugo", "Gosselin", "14/04/1831", "978-0140449204", "FICCION"},
                {"Los miserables", "Epopeya sobre la redención y la justicia en la Francia revolucionaria.", "Víctor Hugo", "Pagnerre", "03/04/1862", "978-0140399523", "FICCION"},
                {"Moby Dick", "Novela sobre la obsesión del capitán Ahab por cazar la ballena blanca.", "Herman Melville", "Richard Bentley", "18/10/1851", "978-0142437247", "FICCION"},
                {"La isla del tesoro", "Aventura de piratas y búsqueda de tesoro en el Caribe.", "Robert Louis Stevenson", "Cassell & Co", "01/11/1882", "978-0141439662", "FICCION"},
                {"Drácula", "Novela gótica sobre el famoso conde vampiro.", "Bram Stoker", "Archibald Constable", "01/05/1897", "978-0141439846", "FICCION"},
                {"Frankenstein", "Historia de un científico que crea una criatura monstruosa.", "Mary Shelley", "Lackington", "01/01/1818", "978-0141439471", "FICCION"},
                {"El extranjero", "Novela existencialista sobre un hombre indiferente a la vida.", "Albert Camus", "Gallimard", "01/06/1942", "978-0679720201", "FICCION"},
                {"La metamorfosis", "Relato de un hombre que se transforma en insecto.", "Franz Kafka", "Kurt Wolff Verlag", "01/10/1915", "978-0553213676", "FICCION"},
                {"El proceso", "Novela sobre un hombre acusado de un crimen desconocido.", "Franz Kafka", "Verlag Die Schmiede", "01/04/1925", "978-0805209990", "FICCION"},
                {"El castillo", "Novela sobre un agrimensor que intenta acceder a un misterioso castillo.", "Franz Kafka", "Verlag Die Schmiede", "01/08/1926", "978-0805209983", "FICCION"},
                {"Ulises", "Novela experimental que sigue un día en la vida de Leopold Bloom.", "James Joyce", "Shakespeare and Company", "02/02/1922", "978-0199535675", "FICCION"},
                {"El gran Gatsby", "Novela sobre el sueño americano y la obsesión amorosa.", "F. Scott Fitzgerald", "Scribner", "10/04/1925", "978-0743273565", "FICCION"},
                {"Adiós a las armas", "Novela sobre la Primera Guerra Mundial y el amor.", "Ernest Hemingway", "Scribner", "27/09/1929", "978-0684801469", "FICCION"},
                {"Por quién doblan las campanas", "Novela sobre la Guerra Civil Española.", "Ernest Hemingway", "Scribner", "21/10/1940", "978-0684803357", "FICCION"},
                {"El viejo y el mar", "Novela sobre un pescador anciano y su lucha contra un pez gigante.", "Ernest Hemingway", "Scribner", "01/09/1952", "978-0684801223", "FICCION"},
                {"Cien años de soledad", "Novela mágica realista sobre varias generaciones de la familia Buendía.", "Gabriel García Márquez", "Editorial Sudamericana", "30/05/1967", "978-8439702017", "FICCION"},
                {"El amor en los tiempos del cólera", "Novela sobre el amor duradero entre dos personas.", "Gabriel García Márquez", "Oveja Negra", "06/03/1985", "978-8439702024", "FICCION"},
                {"Crónica de una muerte anunciada", "Novela sobre un asesinato anunciado en un pueblo caribeño.", "Gabriel García Márquez", "Oveja Negra", "01/04/1981", "978-8439702031", "FICCION"},
                {"La casa de los espíritus", "Novela multigeneracional sobre una familia chilena.", "Isabel Allende", "Plaza & Janés", "01/10/1982", "978-8401428529", "FICCION"},
                {"Paula", "Novela autobiográfica sobre la vida de la autora.", "Isabel Allende", "Plaza & Janés", "01/01/1994", "978-8401428536", "FICCION"},
                {"El reino del dragón de oro", "Novela de aventura y misterio en el Himalaya.", "Isabel Allende", "Plaza & Janés", "01/10/2003", "978-8401428543", "FICCION"},
                {"La ciudad de las bestias", "Novela de aventura en la selva amazónica.", "Isabel Allende", "Plaza & Janés", "01/10/2002", "978-8401428550", "FICCION"},
                {"Zafón", "Novela sobre un misterio literario en Barcelona.", "Carlos Ruiz Zafón", "Planeta", "01/04/2001", "978-8408029755", "FICCION"},
                {"El príncipe de la niebla", "Novela de misterio y suspense en la costa vasca.", "Carlos Ruiz Zafón", "Planeta", "01/01/1992", "978-8408029762", "FICCION"},
                {"La sombra del viento", "Novela sobre un joven que descubre un misterio literario.", "Carlos Ruiz Zafón", "Planeta", "01/04/2001", "978-8408029779", "FICCION"},
                {"El juego del ángel", "Novela sobre dos escritores en la Barcelona de los años 20.", "Carlos Ruiz Zafón", "Planeta", "01/11/2008", "978-8408029786", "FICCION"},
                {"Ficciones", "Colección de cuentos fantásticos y metafísicos.", "Jorge Luis Borges", "Sur", "01/01/1944", "978-8420627687", "FICCION"},
                {"El Aleph", "Colección de cuentos sobre temas fantásticos y filosóficos.", "Jorge Luis Borges", "Losada", "01/01/1949", "978-8420627694", "FICCION"},
                // No Ficción (40 libros)
                {"Sapiens", "Una historia breve de la humanidad desde el Homo sapiens hasta hoy.", "Yuval Noah Harari", "Harvill Secker", "01/09/2011", "978-0062316097", "NO_FICCION"},
                {"Homo Deus", "Exploración del futuro de la humanidad y la tecnología.", "Yuval Noah Harari", "Harvill Secker", "01/09/2015", "978-0062316104", "NO_FICCION"},
                {"21 lecciones para el siglo XXI", "Reflexiones sobre los desafíos del mundo moderno.", "Yuval Noah Harari", "Jonathan Cape", "01/09/2018", "978-0062316111", "NO_FICCION"},
                {"Una breve historia del tiempo", "Introducción a conceptos de cosmología y física cuántica.", "Stephen Hawking", "Bantam Books", "01/04/1988", "978-0553380163", "NO_FICCION"},
                {"El universo en una cáscara de nuez", "Explicación de conceptos de física y cosmología de forma accesible.", "Stephen Hawking", "Bantam Books", "01/10/2001", "978-0553802023", "NO_FICCION"},
                {"La teoría de todo", "Ensayo sobre la búsqueda de una teoría unificada.", "Stephen Hawking", "New Millennium Press", "01/01/2002", "978-0553380170", "NO_FICCION"},
                {"El gen egoísta", "Teoría sobre la evolución desde la perspectiva de los genes.", "Richard Dawkins", "Oxford University Press", "01/01/1976", "978-0192860926", "NO_FICCION"},
                {"La escalera de la vida", "Exploración de la complejidad en la naturaleza.", "Richard Dawkins", "Oxford University Press", "01/01/2004", "978-0192860933", "NO_FICCION"},
                {"El espejismo de Dios", "Crítica de la religión desde una perspectiva científica.", "Richard Dawkins", "Bantam Press", "01/10/2006", "978-0192860940", "NO_FICCION"},
                {"Breve historia de la filosofía", "Recorrido por los principales filósofos de la historia.", "Nigel Warburton", "Routledge", "01/01/1992", "978-0415093385", "NO_FICCION"},
                {"Filosofía para principiantes", "Introducción accesible a los conceptos filosóficos.", "Richard Osborne", "Icon Books", "01/01/1992", "978-1874166017", "NO_FICCION"},
                {"El mundo de Sofía", "Novela educativa sobre la historia de la filosofía.", "Jostein Gaarder", "Berkley", "01/01/1991", "978-0425143469", "NO_FICCION"},
                {"Pensar rápido, pensar lento", "Análisis de cómo tomamos decisiones.", "Daniel Kahneman", "Farrar, Straus and Giroux", "01/10/2011", "978-0374275631", "NO_FICCION"},
                {"Inteligencia emocional", "Importancia de las emociones en la inteligencia.", "Daniel Goleman", "Bantam Books", "01/09/1995", "978-0553375237", "NO_FICCION"},
                {"Inteligencia emocional 2.0", "Guía práctica para mejorar la inteligencia emocional.", "Travis Bradberry", "TalentSmart", "01/01/2009", "978-0974320625", "NO_FICCION"},
                {"El poder del hábito", "Cómo los hábitos moldean nuestras vidas.", "Charles Duhigg", "Random House", "01/02/2012", "978-1400069286", "NO_FICCION"},
                {"Hábitos atómicos", "Guía práctica para construir buenos hábitos y eliminar los malos.", "James Clear", "Avery", "16/10/2018", "978-0735211292", "NO_FICCION"},
                {"Enfocarse", "Cómo mantener la concentración en un mundo distraído.", "Daniel Goleman", "Bantam Books", "01/12/2013", "978-0553386721", "NO_FICCION"},
                {"Profundo trabajo", "Cómo lograr un trabajo profundo en un mundo distraído.", "Cal Newport", "Grand Central Publishing", "01/01/2016", "978-1455586691", "NO_FICCION"},
                {"Minimalismo", "Vivir con menos para vivir mejor.", "Joshua Fields Millburn", "Essentialism LLC", "01/01/2011", "978-0615648927", "NO_FICCION"},
                {"Esencialismo", "La disciplina de perseguir menos pero mejor.", "Greg McKeown", "Crown Business", "01/04/2014", "978-0804137522", "NO_FICCION"},
                {"El arte de la guerra", "Tratado antiguo sobre estrategia militar.", "Sun Tzu", "Varios editores", "01/01/1910", "978-0140455778", "NO_FICCION"},
                {"Meditations", "Reflexiones personales del emperador romano Marco Aurelio.", "Marco Aurelio", "Varios editores", "01/01/1908", "978-0140449334", "NO_FICCION"},
                {"Discursos", "Enseñanzas del filósofo estoico Epicteto.", "Epicteto", "Varios editores", "01/01/1916", "978-0140442662", "NO_FICCION"},
                {"Cartas de un estoico", "Correspondencia del filósofo Séneca.", "Séneca", "Varios editores", "01/01/1969", "978-0140442069", "NO_FICCION"},
                {"El banquete", "Diálogos de Platón sobre el amor.", "Platón", "Varios editores", "01/01/1871", "978-0140449273", "NO_FICCION"},
                {"La república", "Obra maestra de Platón sobre la justicia y el estado ideal.", "Platón", "Varios editores", "01/01/1871", "978-0140449280", "NO_FICCION"},
                {"Ética a Nicómaco", "Tratado de Aristóteles sobre la ética.", "Aristóteles", "Varios editores", "01/01/1925", "978-0140449297", "NO_FICCION"},
                {"Metafísica", "Obra fundamental de Aristóteles sobre la naturaleza del ser.", "Aristóteles", "Varios editores", "01/01/1925", "978-0140449304", "NO_FICCION"},
                {"Crítica de la razón pura", "Obra fundamental de Kant sobre el conocimiento.", "Immanuel Kant", "Varios editores", "01/01/1998", "978-0140449311", "NO_FICCION"},
                {"Fenomenología del espíritu", "Obra de Hegel sobre la historia de la conciencia.", "Georg Wilhelm Friedrich Hegel", "Varios editores", "01/01/1977", "978-0140449328", "NO_FICCION"},
                {"El ser y la nada", "Obra fundamental del existencialismo de Sartre.", "Jean-Paul Sartre", "Gallimard", "01/01/1943", "978-0671867522", "NO_FICCION"},
                {"El segundo sexo", "Análisis de la condición de la mujer por Simone de Beauvoir.", "Simone de Beauvoir", "Gallimard", "01/06/1949", "978-0679734772", "NO_FICCION"},
                {"La estructura de las revoluciones científicas", "Análisis de cómo avanzan las ciencias.", "Thomas Kuhn", "University of Chicago Press", "01/01/1962", "978-0226458083", "NO_FICCION"},
                {"El método científico", "Introducción al método científico.", "Karl Popper", "Routledge", "01/01/1959", "978-0415278645", "NO_FICCION"},
                {"Gödel, Escher, Bach", "Exploración de la conciencia y la inteligencia artificial.", "Douglas Hofstadter", "Basic Books", "01/04/1979", "978-0465026562", "NO_FICCION"},
                {"La mente nueva del emperador", "Reflexión sobre la conciencia y la computación.", "Roger Penrose", "Oxford University Press", "01/01/1989", "978-0198784913", "NO_FICCION"},
                {"El gen de Dios", "Exploración de la espiritualidad desde la neurociencia.", "Dean Hamer", "Doubleday", "01/01/2004", "978-0385509007", "NO_FICCION"},
                {"La ilusión de Dios", "Crítica de la religión desde la neurociencia.", "Sam Harris", "Free Press", "01/09/2006", "978-0743268936", "NO_FICCION"},
                // Literatura Juvenil (30 libros)
                {"Harry Potter y la piedra filosofal", "Las aventuras de un joven mago en su primer año en Hogwarts.", "J.K. Rowling", "Bloomsbury", "26/06/1997", "978-0747532699", "LITERATURA_JUVENIL"},
                {"Harry Potter y la cámara secreta", "Segundo año de Harry Potter en Hogwarts.", "J.K. Rowling", "Bloomsbury", "07/07/1998", "978-0747538493", "LITERATURA_JUVENIL"},
                {"Harry Potter y el prisionero de Azkaban", "Tercer año de Harry Potter en Hogwarts.", "J.K. Rowling", "Bloomsbury", "08/07/1999", "978-0747542155", "LITERATURA_JUVENIL"},
                {"Harry Potter y el cáliz de fuego", "Cuarto año de Harry Potter en Hogwarts.", "J.K. Rowling", "Bloomsbury", "08/07/2000", "978-0747546245", "LITERATURA_JUVENIL"},
                {"Harry Potter y la Orden del Fénix", "Quinto año de Harry Potter en Hogwarts.", "J.K. Rowling", "Bloomsbury", "21/06/2003", "978-0747551003", "LITERATURA_JUVENIL"},
                {"Harry Potter y el misterio del príncipe", "Sexto año de Harry Potter en Hogwarts.", "J.K. Rowling", "Bloomsbury", "16/07/2005", "978-0747581086", "LITERATURA_JUVENIL"},
                {"Harry Potter y las Reliquias de la Muerte", "Séptimo y último año de Harry Potter en Hogwarts.", "J.K. Rowling", "Bloomsbury", "21/07/2007", "978-0747591054", "LITERATURA_JUVENIL"},
                {"El Hobbit", "La aventura de un hobbit en la Tierra Media.", "J.R.R. Tolkien", "Allen & Unwin", "21/09/1937", "978-0547928227", "LITERATURA_JUVENIL"},
                {"El Señor de los Anillos: La Comunidad del Anillo", "Primera parte de la epopeya de la Tierra Media.", "J.R.R. Tolkien", "Allen & Unwin", "29/07/1954", "978-0544003415", "LITERATURA_JUVENIL"},
                {"El Señor de los Anillos: Las Dos Torres", "Segunda parte de la epopeya de la Tierra Media.", "J.R.R. Tolkien", "Allen & Unwin", "11/11/1954", "978-0544003422", "LITERATURA_JUVENIL"},
                {"El Señor de los Anillos: El Retorno del Rey", "Tercera parte de la epopeya de la Tierra Media.", "J.R.R. Tolkien", "Allen & Unwin", "20/10/1955", "978-0544003439", "LITERATURA_JUVENIL"},
                {"Las Crónicas de Narnia: El león, la bruja y el armario", "Primera aventura en el mundo de Narnia.", "C.S. Lewis", "Geoffrey Bles", "16/10/1950", "978-0064471046", "LITERATURA_JUVENIL"},
                {"Las Crónicas de Narnia: El príncipe Caspian", "Segunda aventura en el mundo de Narnia.", "C.S. Lewis", "Geoffrey Bles", "15/10/1951", "978-0064471053", "LITERATURA_JUVENIL"},
                {"Las Crónicas de Narnia: La travesía del Viajero del Alba", "Tercera aventura en el mundo de Narnia.", "C.S. Lewis", "Geoffrey Bles", "15/09/1952", "978-0064471060", "LITERATURA_JUVENIL"},
                {"Percy Jackson y los dioses del Olimpo: El ladrón del rayo", "Primera aventura de Percy Jackson.", "Rick Riordan", "Hyperion Books", "01/07/2005", "978-0786856084", "LITERATURA_JUVENIL"},
                {"Percy Jackson y los dioses del Olimpo: El mar de los monstruos", "Segunda aventura de Percy Jackson.", "Rick Riordan", "Hyperion Books", "02/07/2006", "978-0786856091", "LITERATURA_JUVENIL"},
                {"Percy Jackson y los dioses del Olimpo: La maldición del Titán", "Tercera aventura de Percy Jackson.", "Rick Riordan", "Hyperion Books", "01/05/2007", "978-0786856107", "LITERATURA_JUVENIL"},
                {"Crepúsculo", "Historia de amor entre una humana y un vampiro.", "Stephenie Meyer", "Little, Brown", "05/10/2005", "978-0316015844", "LITERATURA_JUVENIL"},
                {"Luna Nueva", "Segunda parte de la saga Crepúsculo.", "Stephenie Meyer", "Little, Brown", "06/09/2006", "978-0316015851", "LITERATURA_JUVENIL"},
                {"Eclipse", "Tercera parte de la saga Crepúsculo.", "Stephenie Meyer", "Little, Brown", "07/08/2007", "978-0316015868", "LITERATURA_JUVENIL"},
                {"Amanecer", "Cuarta y última parte de la saga Crepúsculo.", "Stephenie Meyer", "Little, Brown", "02/08/2008", "978-0316015875", "LITERATURA_JUVENIL"},
                {"Los Juegos del Hambre", "Primera parte de la trilogía de Los Juegos del Hambre.", "Suzanne Collins", "Scholastic Press", "14/09/2008", "978-0439023481", "LITERATURA_JUVENIL"},
                {"Los Juegos del Hambre: En llamas", "Segunda parte de la trilogía de Los Juegos del Hambre.", "Suzanne Collins", "Scholastic Press", "01/09/2009", "978-0439023498", "LITERATURA_JUVENIL"},
                {"Los Juegos del Hambre: Sinsajo", "Tercera parte de la trilogía de Los Juegos del Hambre.", "Suzanne Collins", "Scholastic Press", "18/08/2010", "978-0439023505", "LITERATURA_JUVENIL"},
                {"Divergente", "Primera parte de la trilogía Divergente.", "Veronica Roth", "Katherine Tegen Books", "22/04/2011", "978-0061985935", "LITERATURA_JUVENIL"},
                {"Insurgente", "Segunda parte de la trilogía Divergente.", "Veronica Roth", "Katherine Tegen Books", "01/05/2012", "978-0061985942", "LITERATURA_JUVENIL"},
                {"Leal", "Tercera parte de la trilogía Divergente.", "Veronica Roth", "Katherine Tegen Books", "22/10/2013", "978-0061985959", "LITERATURA_JUVENIL"},
                {"La culpa es de las estrellas", "Historia de amor entre dos adolescentes con cáncer.", "John Green", "Dutton Books", "10/01/2012", "978-0525478812", "LITERATURA_JUVENIL"},
                {"Bajo la misma estrella", "Novela de romance y aventura.", "John Green", "Dutton Books", "16/10/2008", "978-0525478806", "LITERATURA_JUVENIL"},
                {"Buscando a Alaska", "Novela sobre amistad y autodescubrimiento.", "John Green", "Dutton Books", "16/03/2005", "978-0525475065", "LITERATURA_JUVENIL"},
                // Poesía (20 libros)
                {"Veinte poemas de amor y una canción de desesperación", "Colección de poemas románticos y melancólicos.", "Pablo Neruda", "Editorial Nascimento", "01/01/1924", "978-8408086994", "POESIA"},
                {"Rimas y leyendas", "Colección de poesías y leyendas románticas españolas.", "Gustavo Adolfo Bécquer", "Imprenta de Fortanet", "01/01/1871", "978-8408086994", "POESIA"},
                {"Hojas de hierba", "Colección de poemas de Walt Whitman.", "Walt Whitman", "Fowler & Wells", "04/07/1855", "978-0451526342", "POESIA"},
                {"El cuervo", "Poema narrativo de Edgar Allan Poe.", "Edgar Allan Poe", "American Review", "01/01/1845", "978-0486272665", "POESIA"},
                {"Poesías completas", "Colección de poemas de Lord Byron.", "Lord Byron", "Varios editores", "01/01/1904", "978-0140424522", "POESIA"},
                {"Oda a la alegría", "Poema de Friedrich Schiller.", "Friedrich Schiller", "Varios editores", "01/01/1785", "978-0140424539", "POESIA"},
                {"La divina comedia", "Epopeya poética de Dante Alighieri.", "Dante Alighieri", "Varios editores", "01/01/1320", "978-0140448955", "POESIA"},
                {"Paraíso perdido", "Epopeya poética de John Milton.", "John Milton", "Samuel Simmons", "20/08/1667", "978-0140424935", "POESIA"},
                {"Poemas de amor", "Colección de poemas románticos.", "Varios autores", "Varios editores", "01/01/1900", "978-0140424942", "POESIA"},
                {"Sonetos", "Colección de sonetos de William Shakespeare.", "William Shakespeare", "Thomas Thorpe", "01/01/1609", "978-0140424949", "POESIA"},
                {"Poesía moderna", "Antología de poesía moderna.", "Varios autores", "Varios editores", "01/01/1950", "978-0140424956", "POESIA"},
                {"Poesía contemporánea", "Antología de poesía contemporánea.", "Varios autores", "Varios editores", "01/01/2000", "978-0140424963", "POESIA"},
                {"Cantos", "Poema épico de Ezra Pound.", "Ezra Pound", "Boni & Liveright", "01/01/1925", "978-0811204934", "POESIA"},
                {"La tierra baldía", "Poema de T.S. Eliot.", "T.S. Eliot", "The Criterion", "01/10/1922", "978-0811204941", "POESIA"},
                {"Howl", "Poema de Allen Ginsberg.", "Allen Ginsberg", "City Lights Publishers", "01/11/1956", "978-0811204958", "POESIA"},
                {"Ariel", "Colección de poemas de Sylvia Plath.", "Sylvia Plath", "Faber and Faber", "01/01/1965", "978-0811204965", "POESIA"},
                {"Poemas de la soledad", "Colección de poemas sobre la soledad.", "Varios autores", "Varios editores", "01/01/1980", "978-0811204972", "POESIA"},
                {"Poemas de la naturaleza", "Colección de poemas sobre la naturaleza.", "Varios autores", "Varios editores", "01/01/1990", "978-0811204979", "POESIA"},
                {"Poemas de la ciudad", "Colección de poemas sobre la ciudad.", "Varios autores", "Varios editores", "01/01/2000", "978-0811204986", "POESIA"},
                {"Poemas de la esperanza", "Colección de poemas sobre la esperanza.", "Varios autores", "Varios editores", "01/01/2010", "978-0811204993", "POESIA"},
                // Teatro (15 libros)
                {"La casa de Bernarda Alba", "Drama que retrata la opresión en una familia andaluza.", "Federico García Lorca", "Ediciones Ulises", "01/01/1945", "978-8408086994", "TEATRO"},
                {"Hamlet", "Tragedia sobre el príncipe de Dinamarca y su venganza.", "William Shakespeare", "First Folio", "01/01/1603", "978-0743477116", "TEATRO"},
                {"Macbeth", "Tragedia sobre la ambición y el poder.", "William Shakespeare", "First Folio", "01/01/1606", "978-0743477123", "TEATRO"},
                {"Otelo", "Tragedia sobre los celos y la traición.", "William Shakespeare", "First Folio", "01/01/1604", "978-0743477130", "TEATRO"},
                {"El rey Lear", "Tragedia sobre la vejez y la locura.", "William Shakespeare", "First Folio", "01/01/1606", "978-0743477147", "TEATRO"},
                {"Sueño de una noche de verano", "Comedia sobre el amor y la magia.", "William Shakespeare", "First Folio", "01/01/1595", "978-0743477154", "TEATRO"},
                {"La tempestad", "Comedia sobre la magia y la redención.", "William Shakespeare", "First Folio", "01/01/1610", "978-0743477161", "TEATRO"},
                {"Edipo Rey", "Tragedia griega sobre el destino.", "Sófocles", "Varios editores", "01/01/1927", "978-0140440522", "TEATRO"},
                {"Antígona", "Tragedia griega sobre el conflicto entre la ley y la conciencia.", "Sófocles", "Varios editores", "01/01/1927", "978-0140440529", "TEATRO"},
                {"Las ranas", "Comedia griega sobre la literatura.", "Aristófanes", "Varios editores", "01/01/1927", "978-0140440536", "TEATRO"},
                {"Medea", "Tragedia griega sobre la venganza.", "Eurípides", "Varios editores", "01/01/1927", "978-0140440543", "TEATRO"},
                {"Esperando a Godot", "Obra de teatro del absurdo de Samuel Beckett.", "Samuel Beckett", "Éditions de Minuit", "01/01/1952", "978-0802144423", "TEATRO"},
                {"Rhinoceros", "Obra de teatro del absurdo de Eugène Ionesco.", "Eugène Ionesco", "Gallimard", "01/01/1959", "978-0802144430", "TEATRO"},
                {"Un tranvía llamado Deseo", "Obra de teatro de Tennessee Williams.", "Tennessee Williams", "New Directions", "01/12/1947", "978-0811216517", "TEATRO"},
                {"La muerte de un viajante", "Obra de teatro de Arthur Miller.", "Arthur Miller", "Penguin Classics", "01/01/1949", "978-0142000671", "TEATRO"},
                // Cuentos (15 libros)
                {"Cuentos de hadas", "Recopilación de cuentos tradicionales europeos.", "Hans Christian Andersen", "Varios editores", "01/01/1835", "978-8408086994", "CUENTOS"},
                {"Cuentos de terror", "Colección de cuentos de horror.", "Edgar Allan Poe", "Varios editores", "01/01/1845", "978-0140436556", "CUENTOS"},
                {"Cuentos de misterio", "Colección de cuentos de misterio.", "Arthur Conan Doyle", "Varios editores", "01/01/1892", "978-0140436563", "CUENTOS"},
                {"Cuentos de ciencia ficción", "Colección de cuentos de ciencia ficción.", "Isaac Asimov", "Varios editores", "01/01/1950", "978-0140436570", "CUENTOS"},
                {"Cuentos de fantasía", "Colección de cuentos de fantasía.", "J.R.R. Tolkien", "Varios editores", "01/01/1949", "978-0140436587", "CUENTOS"},
                {"Cuentos de amor", "Colección de cuentos románticos.", "Varios autores", "Varios editores", "01/01/1900", "978-0140436594", "CUENTOS"},
                {"Cuentos de aventura", "Colección de cuentos de aventura.", "Varios autores", "Varios editores", "01/01/1920", "978-0140436601", "CUENTOS"},
                {"Cuentos de humor", "Colección de cuentos humorísticos.", "Varios autores", "Varios editores", "01/01/1930", "978-0140436618", "CUENTOS"},
                {"Cuentos de la tradición oral", "Recopilación de cuentos de la tradición oral.", "Varios autores", "Varios editores", "01/01/1940", "978-0140436625", "CUENTOS"},
                {"Cuentos latinoamericanos", "Antología de cuentos latinoamericanos.", "Varios autores", "Varios editores", "01/01/1960", "978-0140436632", "CUENTOS"},
                {"Cuentos españoles", "Antología de cuentos españoles.", "Varios autores", "Varios editores", "01/01/1970", "978-0140436639", "CUENTOS"},
                {"Cuentos europeos", "Antología de cuentos europeos.", "Varios autores", "Varios editores", "01/01/1980", "978-0140436646", "CUENTOS"},
                {"Cuentos asiáticos", "Antología de cuentos asiáticos.", "Varios autores", "Varios editores", "01/01/1990", "978-0140436653", "CUENTOS"},
                {"Cuentos africanos", "Antología de cuentos africanos.", "Varios autores", "Varios editores", "01/01/2000", "978-0140436660", "CUENTOS"},
                {"Cuentos del mundo", "Antología de cuentos del mundo.", "Varios autores", "Varios editores", "01/01/2010", "978-0140436677", "CUENTOS"},
                // Autoayuda (15 libros)
                {"Hábitos atómicos", "Guía práctica para construir buenos hábitos y eliminar los malos.", "James Clear", "Avery", "16/10/2018", "978-0735211292", "AUTOAYUDA"},
                {"El poder del hábito", "Cómo los hábitos moldean nuestras vidas.", "Charles Duhigg", "Random House", "01/02/2012", "978-1400069286", "AUTOAYUDA"},
                {"El poder del ahora", "Guía para vivir en el presente y alcanzar la paz interior.", "Eckhart Tolle", "Namaste Publishing", "01/09/1997", "978-0340733509", "AUTOAYUDA"},
                {"Inteligencia emocional", "Importancia de las emociones en la inteligencia.", "Daniel Goleman", "Bantam Books", "01/09/1995", "978-0553375237", "AUTOAYUDA"},
                {"Inteligencia emocional 2.0", "Guía práctica para mejorar la inteligencia emocional.", "Travis Bradberry", "TalentSmart", "01/01/2009", "978-0974320625", "AUTOAYUDA"},
                {"Pensar rápido, pensar lento", "Análisis de cómo tomamos decisiones.", "Daniel Kahneman", "Farrar, Straus and Giroux", "01/10/2011", "978-0374275631", "AUTOAYUDA"},
                {"Enfocarse", "Cómo mantener la concentración en un mundo distraído.", "Daniel Goleman", "Bantam Books", "01/12/2013", "978-0553386721", "AUTOAYUDA"},
                {"Profundo trabajo", "Cómo lograr un trabajo profundo en un mundo distraído.", "Cal Newport", "Grand Central Publishing", "01/01/2016", "978-1455586691", "AUTOAYUDA"},
                {"Minimalismo", "Vivir con menos para vivir mejor.", "Joshua Fields Millburn", "Essentialism LLC", "01/01/2011", "978-0615648927", "AUTOAYUDA"},
                {"Esencialismo", "La disciplina de perseguir menos pero mejor.", "Greg McKeown", "Crown Business", "01/04/2014", "978-0804137522", "AUTOAYUDA"},
                {"El arte de la negociación", "Técnicas para negociar efectivamente.", "Roger Fisher", "Penguin Books", "01/01/1981", "978-0140065305", "AUTOAYUDA"},
                {"Cómo ganar amigos e influir sobre las personas", "Clásico sobre relaciones humanas.", "Dale Carnegie", "Simon & Schuster", "01/01/1936", "978-0671027032", "AUTOAYUDA"},
                {"El hombre en busca de sentido", "Reflexión sobre el significado de la vida.", "Viktor Frankl", "Beacon Press", "01/01/1946", "978-0807014295", "AUTOAYUDA"},
                {"La vida es ahora", "Guía para vivir plenamente.", "Wayne Dyer", "Hay House", "01/01/1976", "978-0937611005", "AUTOAYUDA"},
                {"El camino menos transitado", "Reflexión sobre el crecimiento personal.", "M. Scott Peck", "Simon & Schuster", "01/01/1978", "978-0671251482", "AUTOAYUDA"},
                // Ciencia y Tecnología (15 libros)
                {"El universo en una cáscara de nuez", "Explicación de conceptos de física y cosmología de forma accesible.", "Stephen Hawking", "Bantam Books", "01/10/2001", "978-0553802023", "CIENCIA_Y_TECNOLOGIA"},
                {"Una breve historia del tiempo", "Introducción a conceptos de cosmología y física cuántica.", "Stephen Hawking", "Bantam Books", "01/04/1988", "978-0553380163", "CIENCIA_Y_TECNOLOGIA"},
                {"La teoría de todo", "Ensayo sobre la búsqueda de una teoría unificada.", "Stephen Hawking", "New Millennium Press", "01/01/2002", "978-0553380170", "CIENCIA_Y_TECNOLOGIA"},
                {"El gen egoísta", "Teoría sobre la evolución desde la perspectiva de los genes.", "Richard Dawkins", "Oxford University Press", "01/01/1976", "978-0192860926", "CIENCIA_Y_TECNOLOGIA"},
                {"La escalera de la vida", "Exploración de la complejidad en la naturaleza.", "Richard Dawkins", "Oxford University Press", "01/01/2004", "978-0192860933", "CIENCIA_Y_TECNOLOGIA"},
                {"El espejismo de Dios", "Crítica de la religión desde una perspectiva científica.", "Richard Dawkins", "Bantam Press", "01/10/2006", "978-0192860940", "CIENCIA_Y_TECNOLOGIA"},
                {"Gödel, Escher, Bach", "Exploración de la conciencia y la inteligencia artificial.", "Douglas Hofstadter", "Basic Books", "01/04/1979", "978-0465026562", "CIENCIA_Y_TECNOLOGIA"},
                {"La mente nueva del emperador", "Reflexión sobre la conciencia y la computación.", "Roger Penrose", "Oxford University Press", "01/01/1989", "978-0198784913", "CIENCIA_Y_TECNOLOGIA"},
                {"El gen de Dios", "Exploración de la espiritualidad desde la neurociencia.", "Dean Hamer", "Doubleday", "01/01/2004", "978-0385509007", "CIENCIA_Y_TECNOLOGIA"},
                {"La ilusión de Dios", "Crítica de la religión desde la neurociencia.", "Sam Harris", "Free Press", "01/09/2006", "978-0743268936", "CIENCIA_Y_TECNOLOGIA"},
                {"Cosmos", "Exploración del universo y la ciencia.", "Carl Sagan", "Random House", "01/09/1980", "978-0394501529", "CIENCIA_Y_TECNOLOGIA"},
                {"El mundo y sus demonios", "Defensa de la razón y la ciencia.", "Carl Sagan", "Random House", "01/01/1996", "978-0394535581", "CIENCIA_Y_TECNOLOGIA"},
                {"La vida en el universo", "Exploración de la posibilidad de vida extraterrestre.", "Carl Sagan", "Simon & Schuster", "01/01/1966", "978-0671238629", "CIENCIA_Y_TECNOLOGIA"},
                {"Inteligencia artificial", "Introducción a la IA.", "Stuart Russell", "Prentice Hall", "01/01/1995", "978-0131038059", "CIENCIA_Y_TECNOLOGIA"},
                {"El futuro de la humanidad", "Reflexión sobre el futuro de la tecnología.", "Michio Kaku", "Doubleday", "01/04/2011", "978-0385533836", "CIENCIA_Y_TECNOLOGIA"},
                // Arte (10 libros)
                {"Historia del arte", "Recorrido por los movimientos artísticos desde la antigüedad hasta la modernidad.", "E.H. Gombrich", "Phaidon Press", "01/01/1950", "978-0714832470", "ARTE"},
                {"El arte moderno", "Exploración del arte moderno.", "Clement Greenberg", "Thames and Hudson", "01/01/1961", "978-0500015018", "ARTE"},
                {"El arte contemporáneo", "Análisis del arte contemporáneo.", "Hal Foster", "Thames and Hudson", "01/01/1996", "978-0500015025", "ARTE"},
                {"La historia de la pintura", "Recorrido por la historia de la pintura.", "Michael Levey", "Thames and Hudson", "01/01/1998", "978-0500015032", "ARTE"},
                {"La historia de la escultura", "Recorrido por la historia de la escultura.", "Adam Barkman", "Thames and Hudson", "01/01/2000", "978-0500015039", "ARTE"},
                {"La historia de la arquitectura", "Recorrido por la historia de la arquitectura.", "Nikolaus Pevsner", "Thames and Hudson", "01/01/1943", "978-0500015046", "ARTE"},
                {"La historia de la fotografía", "Recorrido por la historia de la fotografía.", "Beaumont Newhall", "Thames and Hudson", "01/01/1964", "978-0500015053", "ARTE"},
                {"La historia del cine", "Recorrido por la historia del cine.", "David Bordwell", "McGraw-Hill", "01/01/1994", "978-0070065061", "ARTE"},
                {"La historia de la música", "Recorrido por la historia de la música.", "Donald Jay Grout", "W.W. Norton", "01/01/1960", "978-0393954341", "ARTE"},
                {"La historia de la danza", "Recorrido por la historia de la danza.", "Jennifer Homans", "Knopf", "01/01/2010", "978-0307268616", "ARTE"},
                // Religión y Espiritualidad (10 libros)
                {"El Bhagavad Gita", "Texto sagrado hindú con enseñanzas sobre la vida y el deber.", "Vyasa", "Varios editores", "01/01/1500", "978-0140447392", "RELIGION_Y_ESPIRITUALIDAD"},
                {"El Corán", "Texto sagrado del Islam.", "Mahoma", "Varios editores", "01/01/632", "978-0140449395", "RELIGION_Y_ESPIRITUALIDAD"},
                {"La Biblia", "Texto sagrado del Cristianismo.", "Varios autores", "Varios editores", "01/01/1611", "978-0310408130", "RELIGION_Y_ESPIRITUALIDAD"},
                {"El Tao Te Ching", "Texto sagrado del Taoísmo.", "Lao Tzu", "Varios editores", "01/01/1963", "978-0140449402", "RELIGION_Y_ESPIRITUALIDAD"},
                {"El Libro de Mormón", "Texto sagrado de la Iglesia de Jesucristo de los Santos de los Últimos Días.", "Joseph Smith", "Varios editores", "01/01/1830", "978-0877519683", "RELIGION_Y_ESPIRITUALIDAD"},
                {"El Tripitaka", "Texto sagrado del Budismo.", "Buda", "Varios editores", "01/01/1956", "978-0486427522", "RELIGION_Y_ESPIRITUALIDAD"},
                {"Meditaciones", "Reflexiones personales del emperador romano Marco Aurelio.", "Marco Aurelio", "Varios editores", "01/01/1908", "978-0140449334", "RELIGION_Y_ESPIRITUALIDAD"},
                {"Discursos", "Enseñanzas del filósofo estoico Epicteto.", "Epicteto", "Varios editores", "01/01/1916", "978-0140442662", "RELIGION_Y_ESPIRITUALIDAD"},
                {"Cartas de un estoico", "Correspondencia del filósofo Séneca.", "Séneca", "Varios editores", "01/01/1969", "978-0140442069", "RELIGION_Y_ESPIRITUALIDAD"},
                {"El camino del monje", "Guía espiritual moderna.", "Cory Muscara", "Penguin Books", "01/01/2020", "978-0143131151", "RELIGION_Y_ESPIRITUALIDAD"},
                // Cocina (10 libros)
                {"Cocina española tradicional", "Recetas auténticas de la gastronomía española.", "Simone Ortega", "Ediciones Espasa", "01/01/1972", "978-8423992935", "COCINA"},
                {"Cocina francesa clásica", "Recetas de la cocina francesa.", "Julia Child", "Knopf", "01/01/1961", "978-0394721705", "COCINA"},
                {"Cocina italiana", "Recetas de la cocina italiana.", "Marcella Hazan", "Knopf", "01/01/1973", "978-0394494624", "COCINA"},
                {"Cocina asiática", "Recetas de la cocina asiática.", "Fuschia Dunlop", "Bloomsbury", "01/01/2003", "978-0747569558", "COCINA"},
                {"Cocina mexicana", "Recetas de la cocina mexicana.", "Diana Kennedy", "Knopf", "01/01/1972", "978-0394474151", "COCINA"},
                {"Cocina vegetariana", "Recetas vegetarianas.", "Madhur Jaffrey", "Knopf", "01/01/1981", "978-0394511597", "COCINA"},
                {"Cocina sin gluten", "Recetas sin gluten.", "Shauna James Ahern", "Wiley", "01/01/2007", "978-0470115671", "COCINA"},
                {"Cocina saludable", "Recetas saludables.", "Jamie Oliver", "Hyperion", "01/01/1999", "978-0786863761", "COCINA"},
                {"Cocina rápida", "Recetas rápidas y fáciles.", "Gordon Ramsay", "HarperCollins", "01/01/2005", "978-0007193288", "COCINA"},
                {"Cocina del mundo", "Recetas de diferentes culturas.", "Varios autores", "Varios editores", "01/01/2010", "978-0141039267", "COCINA"}
            };

            // Distribuir los libros entre las bibliotecas
            int libroIndex = 0;
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 7; j++) {
                    if (libroIndex < librosData.length) {
                        String[] datos = librosData[libroIndex];
                        Categoria categoria = Categoria.valueOf(datos[6]);
                        libroDAO.addLibro(new Libro(null, categoria, datos[0], datos[1], datos[2],
                                datos[3], sdf.parse(datos[4]), datos[5], bibliotecas[i]));
                        libroIndex++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Gestión Biblioteca
    public Biblioteca addBiblioteca(String nombre, String ubicacion, String telefono) {
        Biblioteca b = new Biblioteca(null, nombre, ubicacion, telefono);
        return bibliotecaDAO.addBiblioteca(b);
    }

    public Biblioteca getBiblioteca(Integer id) {
        return bibliotecaDAO.getBiblioteca(id);
    }

    public List<Biblioteca> getBibliotecas() {
        return bibliotecaDAO.getBibliotecas();
    }

    public Biblioteca modifyBiblioteca(Biblioteca modificar) {
        return bibliotecaDAO.modifyBiblioteca(modificar);
    }

    public Biblioteca deleteBiblioteca(Integer id) {
        return bibliotecaDAO.deleteBiblioteca(id);
    }

    // Gestión Libro
    public Libro addLibro(Categoria categoria, String titulo, String descripcion, String autor, String editorial, Date fechaPublicacion, String codigoISBN, Biblioteca biblioteca) {
        Libro lbr = new Libro(null, categoria, titulo, descripcion, autor, editorial, fechaPublicacion, codigoISBN, biblioteca);
        return libroDAO.addLibro(lbr);
    }

    public Libro getLibro(Integer id) {
        return libroDAO.getLibro(id);
    }

    public List<Libro> getLibros() {
        return libroDAO.getLibros();
    }

    public List<Libro> getLibrosBiblioteca(Integer idBiblioteca) {
        return libroDAO.getLibrosBiblioteca(idBiblioteca);
    }

    public Libro modifyLibro(Libro modificar) {
        return libroDAO.modifyLibro(modificar);
    }

    public Libro deleteLibro(Integer id) {
        return libroDAO.deleteLibro(id);
    }
}
