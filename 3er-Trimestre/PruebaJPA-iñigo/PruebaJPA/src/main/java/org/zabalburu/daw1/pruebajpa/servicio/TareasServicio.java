/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.servicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.dao.TareasDAO;
import org.zabalburu.daw1.pruebajpa.dao.TareasJPA;
import org.zabalburu.daw1.pruebajpa.dao.UsuarioDAO;
import org.zabalburu.daw1.pruebajpa.dao.UsuarioJPA;
import org.zabalburu.daw1.pruebajpa.model.Tarea;
import org.zabalburu.daw1.pruebajpa.model.Usuario;
import org.zabalburu.daw1.pruebajpa.util.JPAUtil;

/**
 *
 * @author ichueca
 */
public class TareasServicio {
    private TareasDAO tareasDAO;
    private UsuarioDAO usuarioDAO;
    
    public TareasServicio(){
        tareasDAO = new TareasJPA();
        usuarioDAO = new UsuarioJPA();
    }
    
    public List<Usuario> getUsuarios(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return usuarioDAO.getUsuarios(em);
        } finally {
            em.close();
        }
    }
    
    public Usuario getUsuario(Integer id){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return usuarioDAO.getUsuario(em,id);
        } finally {
            em.close();
        }
    }
    
    public Usuario nuevoUsuario(Usuario nuevo){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            usuarioDAO.nuevoUsuario(em, nuevo);
            tx.commit();
        } catch (Exception ex){
            if (tx.isActive()){
                tx.rollback();
            }
        } finally {
            em.close();
        }
        return nuevo;
    }
    
    public void modificarUsuario(Usuario modificar){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            usuarioDAO.modificarUsuario(em, modificar);
            tx.commit();
        } catch (Exception ex){
            if (tx.isActive()){
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
    
    public void eliminarUsuario(Integer id){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            usuarioDAO.eliminarUsuario(em, id);
            tx.commit();
        } catch (Exception ex){
            if (tx.isActive()){
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
    
    public void nuevaTarea(Tarea t){
        if (t.getUsuario() == null){
            throw new IllegalArgumentException("El usuario es obligatorio en la tarea");
        }
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            tareasDAO.nuevaTarea(em, t); // Almacenar en la BBDD
            tx.commit();
            t.getUsuario().addTarea(t); // Sincronizar en memoria
        } catch (Exception ex){
            if (tx.isActive()){
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
    
    public void eliminarTarea(Tarea t){
        if (t.getUsuario() == null){
            throw new IllegalArgumentException("El usuario es obligatorio en la tarea");
        }
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            tareasDAO.nuevaTarea(em, t); // Almacenar en la BBDD
            tx.commit();
            t.getUsuario().removeTarea(t); // Sincronizar en memoria
        } catch (Exception ex){
            if (tx.isActive()){
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        TareasServicio servicio = new TareasServicio();
        for(Usuario u : servicio.getUsuarios()){
            System.out.println(u);
        }
        Usuario u = servicio.getUsuario(953);
        System.out.println(u.getTareas().size());
        Tarea t = new Tarea();
        t.setDescripcion("Nueva Tarea");
        t.setFecha(LocalDate.now());
        t.setFinalizada(Boolean.FALSE);
        t.setTitulo("Nueva Tarea");
        t.setUsuario(u);
        servicio.nuevaTarea(t);
        System.out.println(u.getTareas());
    }
    
}
