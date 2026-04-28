/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestionmantenimientosjpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Iker Navarro Pérez
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "MantenimientosPU";

    private static EntityManagerFactory emf;

   static {
        try {
            openEntityManagerFactory();
        } catch (Exception ex) {
            System.getLogger(JPAUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void openEntityManagerFactory() throws RuntimeException {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            } catch (Exception e) {
                throw new RuntimeException("No se ha podido establecer la conexion con la unidad de persistencia. | " + e.getMessage());
            }
        }
    }

    private JPAUtil() {
        throw new UnsupportedOperationException("JPAUtil es una clase de utilidad, NO instanciar.");
    }

    public static EntityManager getEntityManager() throws RuntimeException {
        return emf.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }
    
}
