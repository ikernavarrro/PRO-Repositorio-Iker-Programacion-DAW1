/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.pruebajpa;

import com.formdev.flatlaf.FlatLightLaf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.model.Usuario;
import org.zabalburu.daw1.pruebajpa.views.FrmTareas;

/**
 *
 * @author ichueca
 */
public class PruebaJPA {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new FrmTareas().setVisible(true);
    }
}
