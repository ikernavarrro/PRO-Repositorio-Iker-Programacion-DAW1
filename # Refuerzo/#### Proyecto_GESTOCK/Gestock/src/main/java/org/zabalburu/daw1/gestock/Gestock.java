/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.gestock;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.zabalburu.daw1.gestock.util.ConexionBBDD;
import org.zabalburu.daw1.gestock.vista.GestockVista;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Gestock {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        System.out.println("Hello Gestock!");
        System.out.println(ConexionBBDD.getConnection());
        new GestockVista().setVisible(true);
    }
}
