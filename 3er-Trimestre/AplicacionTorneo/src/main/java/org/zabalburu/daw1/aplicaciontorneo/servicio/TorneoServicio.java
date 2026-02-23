/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.servicio;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.aplicaciontorneo.dao.JuegoDAO;
import org.zabalburu.daw1.aplicaciontorneo.dao.JugadorDAO;
import org.zabalburu.daw1.aplicaciontorneo.dao.PartidaDAO;
import org.zabalburu.daw1.aplicaciontorneo.dao.impl.JuegoDAOImpl;
import org.zabalburu.daw1.aplicaciontorneo.dao.impl.JugadorDAOImpl;
import org.zabalburu.daw1.aplicaciontorneo.dao.impl.PartidaDAOImpl;
import org.zabalburu.daw1.aplicaciontorneo.exception.JuegoConPartidasException;
import org.zabalburu.daw1.aplicaciontorneo.exception.JugadorConPartidasException;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Partida;

/**
 *
 * @author Iker Navarro Pérez
 */
public final class TorneoServicio {

    private static TorneoServicio servicio;

    private static List<Juego> juegos = new ArrayList<>();
    private static List<Jugador> jugadores = new ArrayList<>();
    private static List<Partida> partidas = new ArrayList<>();

    private static JuegoDAO juegoDAO = new JuegoDAOImpl();
    private static JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private static PartidaDAO partidaDAO = new PartidaDAOImpl();

    static {
        cargarBBDD();
    }

    public static TorneoServicio getServicio() {
        if (servicio == null) {
            servicio = new TorneoServicio();
        }
        return servicio;
    }

    private static void cargarBBDD() {
        juegos = juegoDAO.getJuegos();
        jugadores = jugadorDAO.getJugadores();
        partidas = partidaDAO.getPartidas();
        for (Partida p : partidas) {
            int pos = juegos.indexOf(p.getJuego());
            Juego j = juegos.get(pos);
            p.setJuego(j);
            j.getPartidas().add(p);
            Jugador gana = jugadores.get(jugadores.indexOf(p.getGana()));
            p.setGana(gana);
            gana.getPartidas().add(p);
            Jugador pierde = jugadores.get(jugadores.indexOf(p.getPierde()));
            p.setPierde(pierde);
            pierde.getPartidas().add(p);

        }
    }

    private TorneoServicio() {
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public Juego getJuego(Integer id) {
        return juegos.stream()
                .filter(j -> j.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Jugador getJugador(Integer id) {
        return jugadores.stream()
                .filter(j -> j.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Partida getPartida(Integer id) {
        return partidas.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Juego addJuego(Juego j) {
        juegos.add(j);
        return juegoDAO.addJuego(j);
    }

    public Jugador addJugador(Jugador j) {
        jugadores.add(j);
        return jugadorDAO.addJugador(j);
    }

    public Partida addPartida(Partida p) {
        partidas.add(p);
        p.getJuego().getPartidas().add(p);
        p.getGana().getPartidas().add(p);
        p.getPierde().getPartidas().add(p);
        return partidaDAO.addPartida(p);
    }

    public void removeJuego(Integer id) {
        Juego j = getJuego(id);
        if (j != null) {
            if (!j.getPartidas().isEmpty()) {
                throw new JuegoConPartidasException(j);
            }
            /*if (j != null) {
            partidas.removeAll(j.getPartidas());
            for (Partida p : j.getPartidas()) {
                p.getGana().getPartidas().remove(p);
                p.getPierde().getPartidas().remove(p);
                partidaDAO.removePartida(p.getId());
            }*/
            juegos.remove(j);
            juegoDAO.removeJuego(id);
        }
    }

    public void removeJugador(Integer id) {
        Jugador j = getJugador(id);
        if (j != null) {
            if (!j.getPartidas().isEmpty()) {
                throw new JugadorConPartidasException(j);
            }
            jugadorDAO.removeJugador(id);
            jugadores.remove(j);
        }
    }

    public void removepartida(Integer id) {
        Partida p = getPartida(id);
        if (p != null) {
            p.getJuego().getPartidas().remove(p);
            p.getGana().getPartidas().remove(p);
            p.getPierde().getPartidas().remove(p);
            partidas.remove(p);
            partidaDAO.removePartida(id);
        }
    }

    public static void main(String[] args) {
        TorneoServicio servicio = TorneoServicio.getServicio();
        for (Juego juego : servicio.getJuegos()) {
            System.out.println("Juego : " + juego.getTitulo());
            for (Partida partida : juego.getPartidas()) {
                System.out.println("""
                                   \tFecha : %s\t(Ganador/a:%s vs Perdedor/a:%s)
                                   """.formatted(partida.getFecha().format(DateTimeFormatter.ISO_DATE),
                        partida.getGana().getNick(),
                        partida.getPierde().getNick()));
            }

        }
    }
}
