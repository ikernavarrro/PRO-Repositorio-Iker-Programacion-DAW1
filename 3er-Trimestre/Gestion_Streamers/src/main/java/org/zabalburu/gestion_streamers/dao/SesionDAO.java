/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.gestion_streamers.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.gestion_streamers.model.Sesion;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface SesionDAO {
    
    public void addSesion(EntityManager em, Sesion nueva);
    
    public Sesion getSesion(EntityManager em, Integer idSesion);
    
    public List<Sesion> getSesiones(EntityManager em);
    
    public void modifySesion(EntityManager em, Sesion modificar);
    
    public void removeSesion(EntityManager em, Integer idSesion);
    
}
