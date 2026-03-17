/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Actividad37Conceptos_Basicos_JPA {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("Actividad37PU").createEntityManager();
        Noticia n = new Noticia();
        em.getTransaction().begin();
        n.setTitular("Aprendiendo a usar JPA con el Framework de Hibernate");
        n.setSinopsis("""
                      Esto es una prueba de persist.
                      """);
        n.setFecha(LocalDate.now());
        n.setUrl("www.prueba.com");
        em.persist(n);
        em.getTransaction().commit();
        em.close();
    }
}
