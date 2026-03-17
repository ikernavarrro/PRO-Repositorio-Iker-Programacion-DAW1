/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.dao.NoticiaDAO;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;

/**
 *
 * @author Iker Navarro Pérez
 */
public class NoticiaDAOImpl implements NoticiaDAO {

    @Override
    public Noticia addNoticia(EntityManager em, Noticia nueva) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Noticia getNoticia(EntityManager em, Integer idNoticia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Noticia> getNoticias(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Noticia modifyNoticia(EntityManager em, Noticia modificar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeNoticia(EntityManager em, Integer idNoticia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
