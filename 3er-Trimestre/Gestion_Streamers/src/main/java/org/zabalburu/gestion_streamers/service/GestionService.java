/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestion_streamers.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.zabalburu.gestion_streamers.dao.SesionDAO;
import org.zabalburu.gestion_streamers.dao.StreamerDAO;
import org.zabalburu.gestion_streamers.dao.impl.SesionDAOImpl;
import org.zabalburu.gestion_streamers.dao.impl.StreamerDAOImpl;
import org.zabalburu.gestion_streamers.exception.SesionNoValidaException;
import org.zabalburu.gestion_streamers.exception.StreamerNoValidoException;
import org.zabalburu.gestion_streamers.model.Sesion;
import org.zabalburu.gestion_streamers.model.Streamer;
import org.zabalburu.gestion_streamers.util.JPAUtil;

/**
 *
 * @author Iker Navarro Pérez
 */
public class GestionService {

    private StreamerDAO streamerDAO;
    private SesionDAO sesionDAO;

    /*
    == SINGLETON ==
    Solo permitimos un único servicio, para garantizarlo aplicamos el patrón Singleton.
     */
    private static GestionService service = null;

    public static GestionService getService() {
        if (service == null) {
            service = new GestionService();
        }
        return service;
    }

    private GestionService() {
        this.streamerDAO = new StreamerDAOImpl();
        this.sesionDAO = new SesionDAOImpl();
    }

    // ===== STREAMER =====
    public void addStreamer(Streamer nuevo) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            validateStreamer(nuevo); // Lanza Excepción si no pasa la validación.
            et.begin();
            streamerDAO.addStreamer(em, nuevo);
            et.commit();
        } catch (StreamerNoValidoException e) { // Capturamos la Excepción personalizada.
            if (et.isActive()) {
                et.rollback();
            }
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public Streamer getStreamer(Integer idStreamer) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return streamerDAO.getStreamer(em, idStreamer);
        }
    }

    public Streamer getStreamerConSesiones(Integer idStreamer) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return streamerDAO.getStreamerConSesiones(em, idStreamer);
        }
    }

    public List<Streamer> getStreamers() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return streamerDAO.getStreamers(em);
        }
    }

    public void modifyStreamer(Streamer modificar) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            validateStreamer(modificar); // Lanza Excepción si no pasa la validación.
            et.begin();
            streamerDAO.modifyStreamer(em, modificar);
            et.commit();

        } catch (StreamerNoValidoException e) { // Capturamos la Excepción personalizada.
            if (et.isActive()) {
                et.rollback();
            }
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void removeStreamer(Integer idStreamer) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            streamerDAO.removeStreamer(em, idStreamer);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public boolean existsNick(String nick, Integer idStreamer) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return streamerDAO.existsNick(em, nick, idStreamer);
        }
    }

    /*
    == VALIDACIÓN STREAMER ==
    Validamos lo siguiente:
    1.No hay ningún campo vacío.
    2.No hay un Streamer con un nick igual.
    
    Si algo falla lanzamos excepción.
     */
    public void validateStreamer(Streamer s) throws StreamerNoValidoException {
        if (s.getNombre() == null || s.getNombre().isBlank()) {
            throw new StreamerNoValidoException("El Streamer no tiene nombre.");
        } else if (s.getApellidos() == null || s.getApellidos().isBlank()) {
            throw new StreamerNoValidoException("El Streamer no tiene apellidos.");
        } else if (s.getNick() == null || s.getNick().isBlank()) {
            throw new StreamerNoValidoException("El Streamer no tiene nick.");
        } else if (s.getSeguidores() == null || s.getSeguidores() < 0) {
            throw new StreamerNoValidoException("El Streamer no puede tener menos de 0 seguidores.");
        } else if (existsNick(s.getNick(), s.getId())) {
            throw new StreamerNoValidoException("El Nick del Streamer ya está ocupado.");
        }

    }

    // ===== SESIÓN =====
    public void addSesion(Sesion nueva) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            validateSesion(nueva); // Lanza Excepción si no pasa la validación.
            et.begin();
            sesionDAO.addSesion(em, nueva); // Almacenamos en Base de Datos
            et.commit();
            nueva.getStreamer().getSesiones().add(nueva);  // Sincronizamos en memoria
        } catch (SesionNoValidaException e) { // Capturamos la Excepción personalizada.
            if (et.isActive()) {
                et.rollback();
            }
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public Sesion getSesion(Integer idSesion) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return sesionDAO.getSesion(em, idSesion);
        }
    }

    public List<Sesion> getSesiones() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return sesionDAO.getSesiones(em);
        }
    }

    public void modifySesion(Sesion modificar) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            validateSesion(modificar); // Lanza Excepción si no pasa la validación.
            et.begin();
            sesionDAO.modifySesion(em, modificar);
            et.commit();
            List<Sesion> sesionesStreamer = modificar.getStreamer().getSesiones();
            int pos = sesionesStreamer.indexOf(modificar);
            if (pos != -1) {
                sesionesStreamer.set(pos, modificar);
            }
        } catch (SesionNoValidaException e) { // Capturamos la Excepción personalizada.
            if (et.isActive()) {
                et.rollback();
            }
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void removeSesion(Integer idSesion) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        Sesion s = getSesion(idSesion);
        try {
            et.begin();
            sesionDAO.removeSesion(em, idSesion);
            et.commit();
            if (s != null) {
                s.getStreamer().getSesiones().remove(s);
            }
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    /*
    == VALIDACIÓN SESIÓN ==
    Validamos lo siguiente:
    1.No hay ningún campo vacío.
    2.La Sesión tiene Streamer.
    
    Si algo falla lanzamos excepción.
     */
    public void validateSesion(Sesion s) throws SesionNoValidaException {
        if (s.getDuracion() == null || s.getDuracion() < 0) {
            throw new SesionNoValidaException("La duración de la Sesión no puede ser inferior a 0 minutos.");
        } else if (s.getFecha() == null) {
            throw new SesionNoValidaException("La Sesión no tiene fecha.");
        } else if (s.getCategoria() == null) {
            throw new SesionNoValidaException("La Sesión no tiene categoría.");
        } else if (s.getStreamer() == null) {
            throw new SesionNoValidaException("La Sesión no tiene Streamer.");
        }
    }
}
