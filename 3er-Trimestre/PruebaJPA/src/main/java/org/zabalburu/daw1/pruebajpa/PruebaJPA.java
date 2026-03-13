/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.pruebajpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.modelo.Usuario;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PruebaJPA {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("UsuariosPU").createEntityManager();
        em.getTransaction().begin();
        /* CREAR (Persist)
        Usuario u = new Usuario();
        u.setNombre("Iñigo");
        u.setApellidos("Chueca");
        u.setEmail("ichueca@zabalburu.org");
        u.setFechaAlta(LocalDate.now());
        u.setSueldo(5400.0);
        em.persist(u);
        em.getTransaction().commit();*/

 /*RECUPERAR POR ID (find)
        Usuario u = em.find(Usuario.class, 1);
        // u está GESTIONADO (Mientras siga en la transacción)
        System.out.println(u);
        u.setSueldo(1500.0);
        em.merge(u); //Modifica el usuario en base al ID
        em.getTransaction().commit();*/
 /*ELIMINAR (Tiene que estar gestionado)
        Usuario eliminar = em.find(Usuario.class, 1);
        eliminar.setId(1);
        em.remove(em);
        em.getTransaction().commit();*/
        /*PRUEBAS*/
        /*Usuario u1 = new Usuario();
        u1.setNombre("María");
        u1.setApellidos("García López");
        u1.setEmail("mgarcia@zabalburu.org");
        u1.setFechaAlta(LocalDate.now());
        u1.setSueldo(4800.0);
        em.persist(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Carlos");
        u2.setApellidos("Martínez Ruiz");
        u2.setEmail("cmartinez@zabalburu.org");
        u2.setFechaAlta(LocalDate.now());
        u2.setSueldo(5100.0);
        em.persist(u2);

        Usuario u3 = new Usuario();
        u3.setNombre("Lucía");
        u3.setApellidos("Fernández Pérez");
        u3.setEmail("lfernandez@zabalburu.org");
        u3.setFechaAlta(LocalDate.now());
        u3.setSueldo(6200.0);
        em.persist(u3);

        Usuario u4 = new Usuario();
        u4.setNombre("Andoni");
        u4.setApellidos("Etxebarria Aguirre");
        u4.setEmail("aetxebarria@zabalburu.org");
        u4.setFechaAlta(LocalDate.now());
        u4.setSueldo(4950.0);
        em.persist(u4);

        em.getTransaction().commit();*/
        
        /*CONSULTAS JPQL*/
        //Todos los usuarios (con o sin condición)
        Query q = em.createQuery("""
                                 SELECT u
                                 FROM Usuario u
                                 """);
        List<Usuario> usuarios = q.getResultList();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
            
        }
        em.getTransaction().commit();
    }
}
