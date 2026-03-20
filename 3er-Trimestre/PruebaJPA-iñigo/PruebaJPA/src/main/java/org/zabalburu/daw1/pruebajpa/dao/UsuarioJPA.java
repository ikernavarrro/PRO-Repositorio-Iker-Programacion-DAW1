/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.model.Usuario;
import org.zabalburu.daw1.pruebajpa.util.JPAUtil;

/**
 *
 * @author ichueca
 */
public class UsuarioJPA implements UsuarioDAO {

    @Override
    public Usuario nuevoUsuario(EntityManager em, Usuario nuevo) {
        em.persist(nuevo);
        return nuevo;
    }

    @Override
    public void modificarUsuario(EntityManager em, Usuario modificar) {
        em.merge(modificar);
    }

    @Override
    public void eliminarUsuario(EntityManager em, Integer id) {
        Usuario u = getUsuario(em,id);
        em.remove(u);
    }

    @Override
    public Usuario getUsuario(EntityManager em, Integer id) {
        Query q = em.createQuery(
        """
            Select u
            From Usuario u
            LEFT JOIN FETCH u.tareas
            WHERE u.id = :idTarea
        """);
        q.setParameter("idTarea", id);
        Usuario u = (Usuario) q.getSingleResultOrNull();
        return u;
    }

    @Override
    public List<Usuario> getUsuarios(EntityManager em) {
        Query q = em.createQuery(
        """
            Select u 
            From Usuario u
        """);
        return q.getResultList();
    }
}
