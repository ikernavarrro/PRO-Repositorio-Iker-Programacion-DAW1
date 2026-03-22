/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad38.servicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.zabalburu.daw1.actividad38.dao.NoticiaDAO;
import org.zabalburu.daw1.actividad38.dao.TemaDAO;
import org.zabalburu.daw1.actividad38.dao.impl.NoticiaDAOImpl;
import org.zabalburu.daw1.actividad38.dao.impl.TemaDAOImpl;
import org.zabalburu.daw1.actividad38.modelo.Noticia;
import org.zabalburu.daw1.actividad38.modelo.Tema;
import org.zabalburu.daw1.actividad38.util.JPAUtil;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EditorialServicio {

    private static EditorialServicio servicio;

    private static NoticiaDAO noticiaDAO = new NoticiaDAOImpl();
    private static TemaDAO temaDAO = new TemaDAOImpl();

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
        if (nueva != null) {
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
        } else {
            return null;
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

    // ================= Gestión Temas =================
    public Tema addTema(Tema nuevo) {
        if (nuevo != null) {
            EntityManager em = JPAUtil.getEntityManager();
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                temaDAO.addTema(em, nuevo);
                tx.commit();
                return nuevo;
            } catch (Exception ex) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw ex;
            } finally {
                em.close();
            }
        } else {
            return null;
        }
    }

    public Tema getTema(Integer idTema) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return temaDAO.getTema(em, idTema);
        } finally {
            em.close();;
        }
    }

    public Tema getTemaConNoticias(Integer idTema) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return temaDAO.getTemaConNoticias(em, idTema);
        } finally {
            em.close();
        }
    }

    public List<Tema> getTemas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return temaDAO.getTemas(em);
        } finally {
            em.close();
        }
    }

    public Tema modifyTema(Tema modificar) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            temaDAO.modifyTema(em, modificar);
            tx.commit();
            return modificar;
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public void removeTema(Integer idTema) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            temaDAO.removeTema(em, idTema);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()){
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }
}
