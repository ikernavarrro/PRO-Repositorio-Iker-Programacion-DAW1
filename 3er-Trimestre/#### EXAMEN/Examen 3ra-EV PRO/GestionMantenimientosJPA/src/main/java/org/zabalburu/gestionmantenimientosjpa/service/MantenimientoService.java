/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestionmantenimientosjpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.zabalburu.gestionmantenimientosjpa.dao.MantenimientoDAO;
import org.zabalburu.gestionmantenimientosjpa.dao.impl.MantenimientoOracle;
import org.zabalburu.gestionmantenimientosjpa.exception.RevisionNoValidaException;
import org.zabalburu.gestionmantenimientosjpa.model.Maquina;
import org.zabalburu.gestionmantenimientosjpa.model.Revision;
import org.zabalburu.gestionmantenimientosjpa.model.Tecnico;
import org.zabalburu.gestionmantenimientosjpa.util.JPAUtil;

/**
 *
 * @author Iker Navarro Pérez
 */
public class MantenimientoService {

    private MantenimientoDAO mantenimientoDAO;

    private static MantenimientoService service = null;

    public static MantenimientoService getService() {
        if (service == null) {
            service = new MantenimientoService();
        }
        return service;
    }

    private MantenimientoService() {
        this.mantenimientoDAO = new MantenimientoOracle();
    }

    // ===== Métodos
    public List<Tecnico> getTecnicos() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return mantenimientoDAO.getTecnicos(em);
        }
    }

    public Tecnico getTecnicoConMaquinas(Integer idTecnico) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return mantenimientoDAO.getTecnicoConMaquinas(em, idTecnico);
        }
    }

    public Maquina getMaquina(Integer idMaquina) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return mantenimientoDAO.getMaquina(em, idMaquina);
        }
    }

    public void nuevaRevision(Revision r) throws RevisionNoValidaException {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            validateRevision(r); // Lanza Excepción si no pasa la validación.
            et.begin();
            mantenimientoDAO.nuevaRevision(em, r); // Almacenamos en Base de Datos
            et.commit();
            r.getMaquina().getRevisiones().add(r);  // Sincronizamos en memoria
        } catch (RevisionNoValidaException e) { // Capturamos la Excepción personalizada.
            if (et.isActive()) {
                et.rollback();
            }
            throw new RevisionNoValidaException(e.getMessage());
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void eliminarRevision(Integer idRevision) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        Revision r = mantenimientoDAO.getRevision(em, idRevision);
        try {
            et.begin();
            mantenimientoDAO.eliminarRevision(em, idRevision);
            et.commit();
            if (r != null) {
                r.getMaquina().getRevisiones().remove(r);
            }
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
    }

    // ===== Validaciones
    private void validateRevision(Revision r) throws RevisionNoValidaException {
        if (r != null) {
            if (r.getMaquina() == null) {
                throw new RevisionNoValidaException("La revisión debe tener una máquina asociada.");
            } else if (r.getCoste() == null || r.getCoste() < 0) {
                throw new RevisionNoValidaException("El coste de la revisión no puede estar vacío ni ser negativo.");
            } else if (r.getDescripcion().isBlank()) {
                throw new RevisionNoValidaException("La descripción de la revisión no puede estar vacía.");
            }
        } else {
            throw new RevisionNoValidaException("La revisión es NULL");
        }
    }
}
