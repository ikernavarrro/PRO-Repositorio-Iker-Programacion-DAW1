package org.zabalburu.seriesjpa.proyecto_tmdb.util;

import jakarta.persistence.EntityManager;
import java.time.LocalDate; // Import necesario
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Genero;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Serie;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Usuario;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Valoracion;

public class DataInitializer {

    public static void initialize() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            Long totalUsuarios = em.createQuery("""
                    SELECT COUNT(u)
                    FROM Usuario u
                    """, Long.class).getSingleResult();

            if (totalUsuarios == 0) {
                em.getTransaction().begin();

                Usuario admin = new Usuario("admin", "admin@seriesjpa.com", PasswordUtils.getHash("admin"));
                Usuario ana = new Usuario("unax", "unax@seriesjpa.com", PasswordUtils.getHash("unax"));
                Usuario luis = new Usuario("luis", "luis@seriesjpa.com", PasswordUtils.getHash("luis"));

                Genero drama = new Genero("Drama");
                Genero cienciaFiccion = new Genero("Ciencia ficción");
                Genero thriller = new Genero("Thriller");

                Serie dark = new Serie(1001, "Dark");
                dark.setSinopsis("Una serie alemana de misterio y viajes en el tiempo.");
                dark.addGenero(drama);
                dark.addGenero(cienciaFiccion);

                Serie lost = new Serie(1002, "Lost");
                lost.setSinopsis("Un grupo de supervivientes en una isla llena de misterios.");
                lost.addGenero(drama);
                lost.addGenero(thriller);

                Serie severance = new Serie(1003, "Severance");
                severance.setSinopsis("Una empresa separa quirúrgicamente recuerdos laborales y personales.");
                severance.addGenero(cienciaFiccion);
                severance.addGenero(thriller);

                em.persist(admin);
                em.persist(ana);
                em.persist(luis);

                em.persist(drama);
                em.persist(cienciaFiccion);
                em.persist(thriller);

                em.persist(dark);
                em.persist(lost);
                em.persist(severance);

                // Se añade LocalDate.now() al crear cada valoración
                LocalDate hoy = LocalDate.now();

                Valoracion v1 = new Valoracion(5, "Una obra maestra.", hoy);
                admin.addValoracion(v1);
                dark.addValoracion(v1);
                em.persist(v1);

                Valoracion v2 = new Valoracion(4, "Muy intrigante y original.", hoy);
                admin.addValoracion(v2);
                severance.addValoracion(v2);
                em.persist(v2);

                Valoracion v3 = new Valoracion(5, "De mis series favoritas.", hoy);
                ana.addValoracion(v3);
                lost.addValoracion(v3);
                em.persist(v3);

                Valoracion v4 = new Valoracion(4, "Muy buena atmósfera.", hoy);
                ana.addValoracion(v4);
                dark.addValoracion(v4);
                em.persist(v4);

                Valoracion v5 = new Valoracion(3, "Interesante, pero algo lenta.", hoy);
                luis.addValoracion(v5);
                severance.addValoracion(v5);
                em.persist(v5);

                Valoracion v6 = new Valoracion(4, "Muy entretenida.", hoy);
                luis.addValoracion(v6);
                lost.addValoracion(v6);
                em.persist(v6);

                em.getTransaction().commit();
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
