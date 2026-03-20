/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.servicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao.NoticiaDAO;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao.impl.NoticiaDAOImpl;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.util.JPAUtil;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EditorialServicio {

    private static EditorialServicio servicio;

    private static NoticiaDAO noticiaDAO = new NoticiaDAOImpl();

    private EditorialServicio() {
    }

    public static EditorialServicio getServicio() {
        if (servicio == null) {
            servicio = new EditorialServicio();
        }
        return servicio;
    }

    // ================= Gestión Noticias =================
    public Noticia addNoticia(Noticia nueva) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            noticiaDAO.addNoticia(em, nueva);
            tx.commit();
            return nueva;
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public Noticia getNoticia(Integer idNoticia) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return noticiaDAO.getNoticia(em, idNoticia);
        } finally {
            em.close();
        }
    }

    public List<Noticia> getNoticias() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return noticiaDAO.getNoticias(em);
        } finally {
            em.close();
        }
    }

    public Noticia modifyNoticia(Noticia modificar) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Noticia noticiaModificada = noticiaDAO.modifyNoticia(em, modificar);
            tx.commit();
            return noticiaModificada;
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public void removeNoticia(Integer idNoticia) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            noticiaDAO.removeNoticia(em, idNoticia);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }
}
