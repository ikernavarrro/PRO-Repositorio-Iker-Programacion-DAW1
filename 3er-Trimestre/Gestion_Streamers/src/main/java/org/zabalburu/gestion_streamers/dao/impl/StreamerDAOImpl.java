/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestion_streamers.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.zabalburu.gestion_streamers.dao.StreamerDAO;
import org.zabalburu.gestion_streamers.model.Streamer;

/**
 *
 * @author Iker Navarro Pérez
 */
public class StreamerDAOImpl implements StreamerDAO {

    @Override
    public void addStreamer(EntityManager em, Streamer nuevo) {
        em.persist(nuevo);
    }

    @Override
    public Streamer getStreamer(EntityManager em, Integer idStreamer) {
        return em.find(Streamer.class, idStreamer);
    }

    @Override
    public Streamer getStreamerConSesiones(EntityManager em, Integer idStreamer) {
        Query q = em.createQuery("""
                                 SELECT s
                                 FROM Streamer s
                                 LEFT JOIN FETCH s.sesiones
                                 WHERE s.id = :id
                                 """);
        q.setParameter("id", idStreamer);
        return (Streamer) q.getSingleResultOrNull();
    }

    @Override
    public List<Streamer> getStreamers(EntityManager em) {
        Query q = em.createQuery("""
                                 SELECT s
                                 FROM Streamer s
                                 """);
        return q.getResultList();
    }

    @Override
    public void modifyStreamer(EntityManager em, Streamer modificar) {
        em.merge(modificar);
    }

    @Override
    public void removeStreamer(EntityManager em, Integer idStreamer) {
        em.remove(getStreamer(em, idStreamer));
    }

    @Override
    public boolean existsNick(EntityManager em, String nick, Integer idStreamer) {
        Query q = em.createQuery("""
                                 SELECT COUNT(s)
                                 FROM Streamer s
                                 WHERE LOWER(s.nick) = LOWER(:nick)
                                 AND s.id <> :id
                                 """);
        q.setParameter("nick", nick);
        q.setParameter("id", idStreamer == null ? -1 : idStreamer);

        Long count = (Long) q.getSingleResult();

        return count > 0;
    }

}
