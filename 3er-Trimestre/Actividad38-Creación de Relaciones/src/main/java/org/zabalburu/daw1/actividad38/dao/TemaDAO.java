/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.actividad38.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.actividad38.modelo.Tema;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface TemaDAO {

    public Tema addTema(EntityManager em, Tema nuevo);

    public Tema getTema(EntityManager em, Integer idTema);

    public Tema getTemaConNoticias(EntityManager em, Integer idTema);

    public List<Tema> getTemas(EntityManager em);

    public Tema modifyTema(EntityManager em, Tema modificar);

    public void removeTema(EntityManager em, Integer idTema);

}
