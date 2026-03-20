/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.Vista.NoticiaVista;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Actividad37Conceptos_Basicos_JPA {

    public static void main(String[] args) {
        if (JOptionPane.showConfirmDialog(null,"""
                                               ACTIVIDAD 37 Conceptos Básicos JPA
                                               ----------------------------------
                                               En esta actividad no se ha tenido en cuenta
                                               los estados de los componentes, solo CRUD
                                               
                                               Toca SI para continuar  a la Actividad37.
                                               """, "Aviso",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            new NoticiaVista().setVisible(true);
        }

    }
}
