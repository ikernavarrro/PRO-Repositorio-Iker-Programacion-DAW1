/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.gestion_streamers.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.gestion_streamers.model.Streamer;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface StreamerDAO {

    public void addStreamer(EntityManager em, Streamer nuevo);

    public Streamer getStreamer(EntityManager em, Integer idStreamer);

    public Streamer getStreamerConSesiones(EntityManager em, Integer idStreamer);

    public List<Streamer> getStreamers(EntityManager em);

    public void modifyStreamer(EntityManager em, Streamer modificar);

    public void removeStreamer(EntityManager em, Integer idStreamer);

    public boolean existsNick(EntityManager em, String nick, Integer idStreamer);
}
