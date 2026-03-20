/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao.NoticiaDAO;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;

/**
 *
 * @author Iker Navarro Pérez
 */
public class NoticiaDAOImpl implements NoticiaDAO {

    @Override
    public Noticia addNoticia(EntityManager em, Noticia nueva) {
        em.persist(nueva);
        return nueva;
    }

    @Override
    public Noticia getNoticia(EntityManager em, Integer idNoticia) {
        return em.find(Noticia.class, idNoticia);
    }

    @Override
    public List<Noticia> getNoticias(EntityManager em) {
        return em.createQuery("SELECT n FROM Noticia n", Noticia.class).getResultList();
    }

    @Override
    public Noticia modifyNoticia(EntityManager em, Noticia modificar) {
        return em.merge(modificar);
    }

    @Override
    public void removeNoticia(EntityManager em, Integer idNoticia) {
        Noticia n = getNoticia(em, idNoticia);
        if (n != null){
            em.remove(n);
        }
    }

}
