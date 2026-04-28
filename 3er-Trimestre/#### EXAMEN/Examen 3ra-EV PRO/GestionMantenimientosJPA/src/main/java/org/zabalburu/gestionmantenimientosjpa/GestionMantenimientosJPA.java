/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.gestionmantenimientosjpa;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import org.zabalburu.gestionmantenimientosjpa.view.MantenimientoView;

/**
 *
 * @author DAW1
 */
public class GestionMantenimientosJPA {

    public static void main(String[] args) {
        System.out.println("Hello Iñigo!");
        
        FlatLaf.setup(new FlatIntelliJLaf());
        new MantenimientoView().setVisible(true);
    }
}
