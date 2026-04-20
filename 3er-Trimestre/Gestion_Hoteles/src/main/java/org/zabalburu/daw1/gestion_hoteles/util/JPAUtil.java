/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase utilitaria para gestionar el ciclo de vida de JPA Implementa patrón
 * Singleton para el EntityManagerFactory y espone EntityManager de forma
 * segura.
 *
 * @author Iker Navarro Pérez
 */
public final class JPAUtil {

    private static final String PERSISTENCE_UNIT = "HotelesJPA";

    private static EntityManagerFactory emf;

    static {
        try {
            openEntityManagerFactory();
        } catch (Exception ex) {
            System.getLogger(JPAUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void openEntityManagerFactory() throws Exception {
        if (emf == null || !emf.isOpen()) {
            try {
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            } catch (Exception e) {
                throw new RuntimeException("No se ha podido establecer la conexion con la unidad de persistencia. | " + e.getMessage());
            }
        } else {
            throw new Exception("El EntityManagerFactory ya está abierto.");
        }
    }

    private JPAUtil() {
        throw new UnsupportedOperationException("JPAUtil es una clase de utilidad, NO instanciar.");
    }

    public static EntityManager getEntityManager() throws Exception {
        if (!emf.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory está cerrado. No se puede crear EntityManager.");
        }
        return emf.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }

}
