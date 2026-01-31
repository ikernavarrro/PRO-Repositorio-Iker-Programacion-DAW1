/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.gestionempleados;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import org.zabalburu.daw1.gestionempleados.vista.EmpleadosVista;
import org.zabalburu.daw1.gestionempleados.vista.LoginView;

/**
 *
 * @author ichueca
 */
public class GestionEmpleados {
    
    public static void main(String[] args) {
        /*try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        
        FlatArcDarkOrangeIJTheme.setup();
        new EmpleadosVista();
        
    }
}
