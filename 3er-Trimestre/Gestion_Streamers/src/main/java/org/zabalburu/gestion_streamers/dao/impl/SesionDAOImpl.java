/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestion_streamers.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.zabalburu.gestion_streamers.dao.SesionDAO;
import org.zabalburu.gestion_streamers.model.Sesion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class SesionDAOImpl implements SesionDAO {

    @Override
    public void addSesion(EntityManager em, Sesion nueva) {
        em.persist(nueva);
    }

    @Override
    public Sesion getSesion(EntityManager em, Integer idSesion) {
        return em.find(Sesion.class, idSesion);
    }

    @Override
    public List<Sesion> getSesiones(EntityManager em) {
        Query q = em.createQuery("""
                                 SELECT s
                                 FROM Sesion s
                                 """);
        return q.getResultList();
    }

    @Override
    public void modifySesion(EntityManager em, Sesion modificar) {
        em.merge(modificar);
    }

    @Override
    public void removeSesion(EntityManager em, Integer idSesion) {
        em.remove(getSesion(em, idSesion));
    }

}
