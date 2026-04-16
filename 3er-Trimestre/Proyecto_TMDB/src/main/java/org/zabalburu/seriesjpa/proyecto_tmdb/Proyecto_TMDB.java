/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.seriesjpa.proyecto_tmdb;

import jakarta.persistence.EntityManager;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Genero;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Serie;
import org.zabalburu.seriesjpa.proyecto_tmdb.util.JPAUtil;

/**
 *
 * @author Iker Navarro Pérez
 *
 */
public class Proyecto_TMDB {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();

            Genero drama = new Genero("Drama");
            Genero cienciaFiccion = new Genero("Ciencia ficción");

            Serie serie = new Serie(1399, "Game of Thrones");
            serie.setSinopsis("Lucha por el Trono de Hierro.");
            serie.addGenero(drama);
            serie.addGenero(cienciaFiccion);

            em.persist(drama);
            em.persist(cienciaFiccion);
            em.persist(serie);

            em.getTransaction().commit();

            System.out.println("Serie guardada correctamente con id: " + serie.getId());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
