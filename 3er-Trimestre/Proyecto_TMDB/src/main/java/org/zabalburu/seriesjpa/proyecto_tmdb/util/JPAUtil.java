/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("seriesjpa_dev");
        }
        return emf;
    }

    public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName) {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
