/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl;

import jakarta.persistence.EntityManager;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Genero;

/**
 *
 * @author DAW1
 */
public class GeneroDAOImpl {

    public void insert(EntityManager em, Genero genero) {
        em.persist(genero);
    }

    public Genero findById(EntityManager em, Long id) {
        return em.find(Genero.class, id);
    }

    public Genero findByNombre(EntityManager em, String nombre) {
        try {
            return em.createQuery("""
                    SELECT g
                    FROM Genero g
                    WHERE g.nombre = :nombre
                    """, Genero.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
