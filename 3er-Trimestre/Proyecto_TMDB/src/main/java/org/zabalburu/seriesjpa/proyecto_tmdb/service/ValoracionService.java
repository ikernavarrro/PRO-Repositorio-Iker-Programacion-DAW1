/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;
import org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl.SerieDAOImpl;
import org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl.UsuarioDAOImpl;
import org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl.ValoracionDAOImpl;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Serie;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Usuario;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Valoracion;

/**
 *
 * @author DAW1
 */
public class ValoracionService {

    private final EntityManagerFactory emf;
    private final UsuarioDAOImpl usuarioDAO;
    private final SerieDAOImpl serieDAO;
    private final ValoracionDAOImpl valoracionDAO;

    public ValoracionService(EntityManagerFactory emf, UsuarioDAOImpl usuarioDAO, SerieDAOImpl serieDAO, ValoracionDAOImpl valoracionDAO) {
        this.emf = emf;
        this.usuarioDAO = usuarioDAO;
        this.serieDAO = serieDAO;
        this.valoracionDAO = valoracionDAO;
    }

    public void anadirValoracion(Long usuarioId, Long serieId, int estrellas, String comentario) {
        if (estrellas < 1 || estrellas > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5.");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Usuario usuario = usuarioDAO.findById(em, usuarioId);
            Serie serie = serieDAO.findById(em, serieId);

            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            if (serie == null) {
                throw new IllegalArgumentException("Serie no encontrada");
            }

            Valoracion existente = valoracionDAO.findByUsuarioAndSerie(em, usuarioId, serieId);
            if (existente != null) {
                valoracionDAO.delete(em, existente);
                usuario.removeValoracion(existente);
                serie.removeValoracion(existente);
            }
            Valoracion nueva = new Valoracion();
            nueva.setUsuario(usuario);
            nueva.setSerie(serie);
            nueva.setEstrellas(estrellas);
            nueva.setComentario(comentario);
            valoracionDAO.insert(em, nueva);
            usuario.addValoracion(nueva);
            serie.addValoracion(nueva);
            em.getTransaction().commit();

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void borrarValoracion(Long valoracionId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Valoracion valoracion = valoracionDAO.findById(em, valoracionId);
            if (valoracion == null) {
                throw new IllegalArgumentException("Valoración no encontrada");
            }

            valoracion.getUsuario().removeValoracion(valoracion);
            valoracion.getSerie().removeValoracion(valoracion);

            valoracionDAO.delete(em, valoracion);

            em.getTransaction().commit();

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Valoracion> getValoracionesSerie(Long serieId) {
        EntityManager em = emf.createEntityManager();
        try {
            return valoracionDAO.findBySerieIdConUsuario(em, serieId);
        } finally {
            em.close();
        }
    }

    public List<Valoracion> getValoracionesUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return valoracionDAO.findByUsuarioIdConSerie(em, usuarioId);
        } finally {
            em.close();
        }
    }
}
