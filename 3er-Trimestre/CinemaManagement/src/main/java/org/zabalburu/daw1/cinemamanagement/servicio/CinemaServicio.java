/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.cinemamanagement.servicio;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.cinemamanagement.dao.PeliculaDAO;
import org.zabalburu.daw1.cinemamanagement.dao.SesionDAO;
import org.zabalburu.daw1.cinemamanagement.dao.impl.PeliculaDAOImpl;
import org.zabalburu.daw1.cinemamanagement.dao.impl.SesionDAOImpl;
import org.zabalburu.daw1.cinemamanagement.excepciones.PeliculaConSesionesException;
import org.zabalburu.daw1.cinemamanagement.modelo.Pelicula;
import org.zabalburu.daw1.cinemamanagement.modelo.Sesion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class CinemaServicio {

    private static CinemaServicio servicio;

    private static List<Pelicula> peliculas = new ArrayList<>();
    private static List<Sesion> sesiones = new ArrayList<>();

    private static PeliculaDAO peliculaDAO = new PeliculaDAOImpl();
    private static SesionDAO sesionDAO = new SesionDAOImpl();

    static {
        cargarBBDD();
    }

    public static CinemaServicio getServicio() {
        if (servicio == null) {
            servicio = new CinemaServicio();
        }
        return servicio;
    }

    private static void cargarBBDD() {
        peliculas = peliculaDAO.getPeliculas();
        sesiones = sesionDAO.getSesiones();
    }

    private CinemaServicio() {
    }

    // ==================== PELICULA ====================
    public Pelicula addPelicula(Pelicula nueva) {
        peliculas.add(nueva);
        return peliculaDAO.addPelicula(nueva);
    }

    public Pelicula getPelicula(Integer id) {
        return peliculas.stream()
                .filter(p -> p.getIdPelicula().equals(id))
                .findAny()
                .orElse(null);
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void modifyPelicula(Pelicula modificar) {
        peliculaDAO.modifyPelicula(modificar);
        int pos = peliculas.indexOf(modificar);
        if (pos != -1) {
            peliculas.set(pos, modificar);
        }
    }

    public void removePelicula(Integer id) {
        /*
        Si NO permitiesemos eliminar Peliculas con sesiones registradas:
        Pelicula p = getPelicula(id);
        if (p != null) {
            if (!p.getSesiones().isEmpty()) {
                throw new PeliculaConSesionesException(p);
            }
            peliculaDAO.removePelicula(id);
            for (Sesion s : sesiones.stream()
                                  .filter(s -> s.getPelicula().getIdPelicula().equals(p.getIdPelicula()))
                                  .toList()) {
                sesiones.remove(s);
            }
            peliculas.remove(p);
        }*/

        Pelicula p = getPelicula(id);
        if (p != null) {
            peliculaDAO.removePelicula(id);
            for (Sesion s : sesiones.stream()
                    .filter(s -> s.getPelicula().getIdPelicula().equals(p.getIdPelicula()))
                    .toList()) {
                sesiones.remove(s);
            }
            peliculas.remove(p);
        }
    }

    // ==================== SESIÓN ====================
    public Sesion addSesion(Sesion nueva) {
        sesiones.add(nueva);
        return sesionDAO.addSesion(nueva);
    }

    public Sesion getSesion(Integer id) {
        return sesiones.stream()
                .filter(s -> s.getIdSesion().equals(id))
                .findAny()
                .orElse(null);
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void modifySesion(Sesion modificar) {
        sesionDAO.modifySesion(modificar);
        int pos = sesiones.indexOf(modificar);
        if (pos != -1) {
            sesiones.set(pos, modificar);
        }
    }

    public void removeSesion(Integer id) {
        Sesion s = getSesion(id);
        if (s != null) {
            s.getPelicula().getSesiones().remove(s);
            sesionDAO.removeSesion(id);
            sesiones.remove(s);
        }
    }
}
