/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface NoticiaDAO {

    public Noticia addNoticia(EntityManager em, Noticia nueva);

    public Noticia getNoticia(EntityManager em, Integer idNoticia);

    public List<Noticia> getNoticias(EntityManager em);

    public Noticia modifyNoticia(EntityManager em, Noticia modificar);

    public void removeNoticia(EntityManager em, Integer idNoticia);
}
