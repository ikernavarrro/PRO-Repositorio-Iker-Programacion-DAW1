/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.dao;

import jakarta.persistence.EntityManager;
import org.zabalburu.daw1.pruebajpa.model.Tarea;

/**
 *
 * @author ichueca
 */
public class TareasJPA implements TareasDAO{

    @Override
    public Tarea nuevaTarea(EntityManager em, Tarea t) {
        em.persist(t);
        return t;
    }

    @Override
    public void modificarTarea(EntityManager em, Tarea m) {
        em.merge(m);
    }

    @Override
    public void eliminarTarea(EntityManager em, Integer id) {
        em.remove(getTarea(em,id));
    }

    @Override
    public Tarea getTarea(EntityManager em, Integer id) {
        return em.find(Tarea.class, id);
    }
    
}
