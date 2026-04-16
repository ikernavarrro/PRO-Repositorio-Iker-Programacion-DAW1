/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Valoracion;

/**
 *
 * @author DAW1
 */
public class ValoracionDAOImpl {

    public void insert(EntityManager em, Valoracion valoracion) {
        em.persist(valoracion);
    }

    public Valoracion findById(EntityManager em, Long id) {
        return em.find(Valoracion.class, id);
    }

    public void delete(EntityManager em, Valoracion valoracion) {
        em.remove(valoracion);
    }

    public List<Valoracion> findBySerieId(EntityManager em, Long serieId) {
        return em.createQuery("""
                SELECT v
                FROM Valoracion v
                JOIN FETCH v.usuario
                WHERE v.serie.id = :serieId
                ORDER BY v.fecha DESC
                """, Valoracion.class)
                .setParameter("serieId", serieId)
                .getResultList();
    }

    public List<Valoracion> findBySerieIdConUsuario(EntityManager em, Long serieId) {
        return em.createQuery("""
                SELECT v
                FROM Valoracion v
                JOIN FETCH v.usuario
                WHERE v.serie.id = :serieId
                ORDER BY v.fecha DESC
                """, Valoracion.class)
                .setParameter("serieId", serieId)
                .getResultList();
    }

    public List<Valoracion> findByUsuarioId(EntityManager em, Long usuarioId) {
        return em.createQuery("""
                SELECT v
                FROM Valoracion v
                JOIN FETCH v.serie
                WHERE v.usuario.id = :usuarioId
                ORDER BY v.fecha DESC
                """, Valoracion.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    public List<Valoracion> findByUsuarioIdConSerie(EntityManager em, Long usuarioId) {
        return em.createQuery("""
                SELECT v
                FROM Valoracion v
                JOIN FETCH v.serie
                WHERE v.usuario.id = :usuarioId
                ORDER BY v.fecha DESC
                """, Valoracion.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    public Valoracion findByUsuarioAndSerie(EntityManager em, Long usuarioId, Long serieId) {
        try {
            return em.createQuery("""
                    SELECT v
                    FROM Valoracion v
                    JOIN FETCH v.usuario
                    JOIN FETCH v.serie
                    WHERE v.usuario.id = :usuarioId
                      AND v.serie.id = :serieId
                    """, Valoracion.class)
                    .setParameter("usuarioId", usuarioId)
                    .setParameter("serieId", serieId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
