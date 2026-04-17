/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl.UsuarioDAOImpl;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Usuario;
import org.zabalburu.seriesjpa.proyecto_tmdb.util.PasswordUtils;

/**
 *
 * @author DAW1
 */
public class AuthService {

    private final EntityManagerFactory emf;
    private final UsuarioDAOImpl usuarioDAO;

    public AuthService(EntityManagerFactory emf, UsuarioDAOImpl usuarioDAO) {
        this.emf = emf;
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario login(String nombreUsuario, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            Usuario usuario = usuarioDAO.findByNombreUsuarioConValoraciones(em, nombreUsuario);

            if (usuario == null) {
                return null;
            }

            if (!PasswordUtils.checkPw(password, usuario.getPassword())) {
                return null;
            }

            return usuario;
        } finally {
            em.close();
        }
    }
}
