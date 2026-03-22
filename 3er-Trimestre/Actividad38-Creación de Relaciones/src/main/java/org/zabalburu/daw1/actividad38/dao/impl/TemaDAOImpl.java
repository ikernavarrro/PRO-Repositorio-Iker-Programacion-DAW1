/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad38.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;
import org.zabalburu.daw1.actividad38.dao.TemaDAO;
import org.zabalburu.daw1.actividad38.modelo.Tema;

/**
 *
 * @author Iker Navarro Pérez
 */
public class TemaDAOImpl implements TemaDAO {

    @Override
    public Tema addTema(EntityManager em, Tema nuevo) {
        em.persist(nuevo);
        return nuevo;
    }

    @Override
    public Tema getTema(EntityManager em, Integer idTema) {
        return em.find(Tema.class, idTema);
    }

    @Override
    public Tema getTemaConNoticias(EntityManager em, Integer idTema) {
        return em.createQuery("""
                            SELECT DISTINCT t
                            FROM Tema t
                            LEFT JOIN FETCH e.noticias
                            WHERE e.id = :id
                            """, Tema.class)
                .setParameter("id", idTema)
                .getSingleResultOrNull();
    }

    @Override
    public List<Tema> getTemas(EntityManager em) {
        return em.createQuery("SELECT t FROM Tema t").getResultList();
    }

    @Override
    public Tema modifyTema(EntityManager em, Tema modificar) {
        return em.merge(modificar);
    }

    @Override
    public void removeTema(EntityManager em, Integer idTema) {
        Tema t = getTema(em, idTema);
        if (t != null) {
            em.remove(getTema(em, idTema));
        }
    }

}
