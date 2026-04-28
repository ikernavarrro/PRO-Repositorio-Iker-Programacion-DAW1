/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestionmantenimientosjpa.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.zabalburu.gestionmantenimientosjpa.dao.MantenimientoDAO;
import org.zabalburu.gestionmantenimientosjpa.model.Maquina;
import org.zabalburu.gestionmantenimientosjpa.model.Revision;
import org.zabalburu.gestionmantenimientosjpa.model.Tecnico;

/**
 *
 * @author Iker Navarro Pérez
 */
public class MantenimientoOracle implements MantenimientoDAO {

    @Override
    public List<Tecnico> getTecnicos(EntityManager em) {
        Query q = em.createQuery("""
                                 SELECT t
                                 FROM Tecnico t
                                 ORDER BY nombre
                                 """);
        return q.getResultList();
    }

    @Override
    public Tecnico getTecnicoConMaquinas(EntityManager em, Integer idTecnico) {
        Query q = em.createQuery("""
                                 SELECT t
                                 FROM Tecnico t
                                 LEFT JOIN FETCH t.maquinas
                                 WHERE t.id = :id
                                 """);
        q.setParameter("id", idTecnico);
        return (Tecnico) q.getSingleResultOrNull();
    }

    @Override
    public Maquina getMaquina(EntityManager em, Integer idMaquina) {
        Query q = em.createQuery("""
                                 SELECT m
                                 FROM Maquina m
                                 LEFT JOIN FETCH m.revisiones
                                 WHERE m.id = :id
                                 """);
        q.setParameter("id", idMaquina);
        return (Maquina) q.getSingleResultOrNull();
    }

    @Override
    public void nuevaRevision(EntityManager em, Revision r) {
        em.persist(r);
    }

    @Override
    public Revision getRevision(EntityManager em, Integer idRevision) {
        Query q = em.createQuery("""
                                 SELECT r
                                 FROM Revision r
                                 WHERE r.id = :id
                                 """);
        q.setParameter("id", idRevision);
        return (Revision) q.getSingleResultOrNull();
    }

    @Override
    public void eliminarRevision(EntityManager em, Integer idRevision) {
        Revision r = getRevision(em, idRevision);
        if (r != null) {
            em.remove(r);
        }
    }
}
