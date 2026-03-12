/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.pruebajpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.zabalburu.daw1.pruebajpa.modelo.Usuario;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PruebaJPA {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("UsuariosPU").createEntityManager();
        em.getTransaction().begin();
        Usuario u = new Usuario();
        u.setNombre("Iñigo");
        u.setApellidos("Chueca");
        u.setEmail("ichueca@zabalburu.org");
        u.setFechaAlta(LocalDate.now());
        u.setSueldo(5400.0);
        
        em.persist(u);
        em.getTransaction().commit();
    }
}
