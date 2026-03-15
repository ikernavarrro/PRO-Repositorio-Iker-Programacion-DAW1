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
import org.zabalburu.daw1.cinemamanagement.excepciones.PeliculaNoValida;
import org.zabalburu.daw1.cinemamanagement.excepciones.SesionNoValida;
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

    public static void cargarBBDD() {
        peliculas = peliculaDAO.getPeliculas();
        sesiones = sesionDAO.getSesiones();
    }

    private CinemaServicio() {
    }

    // ==================== PELICULA ====================
    public Pelicula addPelicula(Pelicula nueva) throws PeliculaNoValida {
        if (validarPelicula(nueva)) {
            nueva = peliculaDAO.addPelicula(nueva); // Devuelve la película si todo va bien.
            if (nueva != null) {
                peliculas.add(nueva); // Añadimos en caché.
            }
        }
        return nueva;
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

    public void modifyPelicula(Pelicula modificar) throws PeliculaNoValida {
        if (validarPelicula(modificar)) {
            peliculaDAO.modifyPelicula(modificar);
            int pos = peliculas.indexOf(modificar);
            if (pos != -1) {
                peliculas.set(pos, modificar);
            }
        }
    }

    public void removePelicula(Integer id, boolean permitirBorrarPeliculaConSesiones) {
        Pelicula p = getPelicula(id);
        if (p != null) {
            if (!permitirBorrarPeliculaConSesiones && !p.getSesiones().isEmpty()) { // NO PERMITIMOS BORRAR PELICULAS CON SESIONES
                throw new PeliculaConSesionesException(p);
            } else { // SI PERMITIMOS BORRAR PELICULAS CON SESIONES
                peliculaDAO.removePelicula(id);
                sesiones.removeIf(s -> s.getPelicula().getIdPelicula().equals(p.getIdPelicula()));
                peliculas.remove(p);
            }
        }
    }

    private boolean validarPelicula(Pelicula p) throws PeliculaNoValida {
        if (p != null) {
            if (p.getTitulo().isBlank()) {
                throw new PeliculaNoValida("Título OBLIGATORIO");
            } else if (p.getDirector().isBlank()) {
                throw new PeliculaNoValida("Director OBLIGATORIO");
            } else if (p.getAño() == null) {
                throw new PeliculaNoValida("Año OBLIGATORIO");
            } else if (p.getDuracion() == null || p.getDuracion() <= 0) {
                throw new PeliculaNoValida("Duración OBLIGATORIA");
            } else if (p.getGenero() == null) {
                throw new PeliculaNoValida("Genero OBLIGATORIO");
            } else if (p.getSesiones() == null) {
                throw new PeliculaNoValida("La lista de sesiones no puede ser NULL");
            }
        } else {
            throw new PeliculaNoValida("La película es NULL");
        }
        return true;
    }

    // ==================== SESIÓN ====================
    public Sesion addSesion(Sesion nueva) throws SesionNoValida {
        if (validarSesion(nueva)) {
            nueva = sesionDAO.addSesion(nueva);
            if (nueva != null) {
                sesiones.add(nueva);
            }
        }
        return nueva;
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

    public void modifySesion(Sesion modificar) throws SesionNoValida {
        if (validarSesion(modificar)) {
            sesionDAO.modifySesion(modificar);
            int pos = sesiones.indexOf(modificar);
            if (pos != -1) {
                sesiones.set(pos, modificar);
            }
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

    private boolean validarSesion(Sesion nueva) throws SesionNoValida {
        if (nueva != null) {
            if (nueva.getPelicula() == null) {
                throw new SesionNoValida("Película OBLIGATORIA");
            } else if (nueva.getFecha() == null) {
                throw new SesionNoValida("Fecha OBLIGATORIA");
            } else if (nueva.getHora().isBlank() || nueva.getHora().length() != 5 || !nueva.getHora().contains(":")) {
                throw new SesionNoValida("Hora OBLIGATORIA (Formato hh:mm)");
            } else if (nueva.getSala() == null || nueva.getSala() <= 0) {
                throw new SesionNoValida("Sala OBLIGATORIA");
            } else if (nueva.getAsientosDisponibles() == null || nueva.getAsientosDisponibles() < 0) {
                throw new SesionNoValida("Asientos Disponibles OBLIGATORIOS");
            }
        } else {
            throw new SesionNoValida("La sesión es NULL");
        }
        return true;
    }
}
