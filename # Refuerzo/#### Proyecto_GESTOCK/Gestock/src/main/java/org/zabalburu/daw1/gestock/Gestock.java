/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.gestock;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.zabalburu.daw1.gestock.util.ConexionBBDD;
import org.zabalburu.daw1.gestock.vista.GestockVistaMain;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Gestock {

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        GestockVistaMain pruebas = new GestockVistaMain();
        pruebas.setVisible(true);
        System.out.println("Hello Gestock!");
        System.out.println(ConexionBBDD.getConnection());
    }
}
