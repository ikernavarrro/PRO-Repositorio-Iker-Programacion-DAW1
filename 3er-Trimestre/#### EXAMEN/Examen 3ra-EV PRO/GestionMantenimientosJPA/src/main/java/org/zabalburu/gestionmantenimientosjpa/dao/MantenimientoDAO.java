/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.gestionmantenimientosjpa.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.gestionmantenimientosjpa.model.Maquina;
import org.zabalburu.gestionmantenimientosjpa.model.Revision;
import org.zabalburu.gestionmantenimientosjpa.model.Tecnico;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface MantenimientoDAO {
    
    public List<Tecnico> getTecnicos(EntityManager em);
   
    public Tecnico getTecnicoConMaquinas (EntityManager em, Integer idTecnico);
    
    public Maquina getMaquina(EntityManager em, Integer idMaquina);
    
    public void nuevaRevision(EntityManager em, Revision r);
    
    public Revision getRevision(EntityManager em, Integer idRevision);
    
    public void eliminarRevision(EntityManager em, Integer idRevision);
}
