/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.model.Usuario;

/**
 *
 * @author ichueca
 */
public interface UsuarioDAO {
    Usuario nuevoUsuario(EntityManager em, Usuario nuevo);
    void modificarUsuario(EntityManager em, Usuario modificar);
    void eliminarUsuario(EntityManager em, Integer id);
    Usuario getUsuario(EntityManager em, Integer id);
    List<Usuario> getUsuarios(EntityManager em);
}
