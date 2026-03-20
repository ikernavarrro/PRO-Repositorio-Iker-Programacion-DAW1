/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.dao;

import jakarta.persistence.EntityManager;
import org.zabalburu.daw1.pruebajpa.model.Tarea;

/**
 *
 * @author ichueca
 */
public interface TareasDAO {
    public Tarea nuevaTarea(EntityManager em, Tarea t);
    public void modificarTarea(EntityManager em, Tarea m);
    public void eliminarTarea(EntityManager em, Integer id);
    public Tarea getTarea(EntityManager em, Integer id);
}
