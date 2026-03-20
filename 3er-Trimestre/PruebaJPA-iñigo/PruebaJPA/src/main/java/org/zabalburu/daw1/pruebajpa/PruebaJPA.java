/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.pruebajpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.model.Usuario;

/**
 *
 * @author ichueca
 */
public class PruebaJPA {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("UsuariosPU")
                .createEntityManager();
        em.getTransaction().begin();
        /* CREAR (persist)
        Usuario u = new Usuario();
        u.setNombre("Juan");
        u.setApellidos("López");
        u.setEmail("juan@gmail.com");
        u.setFechaAlta(LocalDate.now());
        u.setSueldo(1245.57);
        em.persist(u);*/
 /* RECUPERAR POR ID (find) */
        //Usuario u = em.find(Usuario.class, 1);
        // u está ahora GESTIONADO
        //System.out.println(u);
        //u.setSueldo(2500.0);
        /*Usuario modificar = new Usuario();
        modificar.setId(30);
        modificar.setNombre("Juan2");
        modificar.setApellidos("López2");
        modificar.setSueldo(2250.0);
        em.merge(modificar); // Modificar el usuario u EN BASE AL ID*/
 /*  Eliminar un objeto GESTIONADO (remove) */
 /*Usuario eliminar = em.find(Usuario.class,1);
        em.remove(eliminar); */
        /*Usuario u1 = new Usuario();
        u1.setNombre("María");
        u1.setApellidos("García");
        u1.setEmail("maria.garcia@outlook.com");
        u1.setFechaAlta(LocalDate.now());
        u1.setSueldo(2100.50);
        em.persist(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Carlos");
        u2.setApellidos("Sánchez");
        u2.setEmail("csanchez@yahoo.es");
        u2.setFechaAlta(LocalDate.now());
        u2.setSueldo(1850.00);
        em.persist(u2);

        Usuario u3 = new Usuario();
        u3.setNombre("Elena");
        u3.setApellidos("Martín");
        u3.setEmail("elena.mtnz@gmail.com");
        u3.setFechaAlta(LocalDate.now());
        u3.setSueldo(3200.75);
        em.persist(u3);

        Usuario u4 = new Usuario();
        u4.setNombre("Ricardo");
        u4.setApellidos("Gómez");
        u4.setEmail("r.gomez@empresa.com");
        u4.setFechaAlta(LocalDate.now());
        u4.setSueldo(1580.30);
        em.persist(u4);*/

        /* CONSULTAS JPQL */
        // Todos los usuarios (con condicion)
        Query q = em.createQuery(
        """
            Select u
            From Usuario u
            Where u.sueldo > 2000
        """);
        //q = em.createNamedQuery("usuario.buscarTodos");
        List<Usuario> usuarios = q.getResultList();
        for(Usuario u : usuarios){
            System.out.println(u);
        }
        // Consultas con un único resultado (parámetros)
        /*Query qEmail = em.createQuery(
        """
            Select u
            From Usuario u
            Where u.email = :email
        """, Usuario.class);
        qEmail.setParameter("email", "maria.garcia@outlook.cm");
        Usuario u = null;
        /*try {
            u = (Usuario) qEmail.getSingleResult();
        } catch (NoResultException ex){
        }
        u = (Usuario) qEmail.getSingleResultOrNull();
        System.out.println(u);*/
        /*Usuario u = em.find(Usuario.class, 952);
        System.out.println(u.getApellidos());*/
        
        /*em.getTransaction().commit();
        em.close();
        em = Persistence.createEntityManagerFactory("UsuariosPU")
                .createEntityManager();*/
        //System.out.println(u.getTareas());
    }
}
