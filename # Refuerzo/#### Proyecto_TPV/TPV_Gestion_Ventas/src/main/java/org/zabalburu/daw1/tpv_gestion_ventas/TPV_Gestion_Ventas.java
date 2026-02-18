/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.tpv_gestion_ventas;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Usuario;
import org.zabalburu.daw1.tpv_gestion_ventas.util.RangoUsuario;
import org.zabalburu.daw1.tpv_gestion_ventas.vista.LoginPanel;
import org.zabalburu.daw1.tpv_gestion_ventas.vista.MainFrame;

/**
 *
 * @author Iker Navarro Pérez
 */
public class TPV_Gestion_Ventas {

    public static void main(String[] args) {
        
        // --- Configuración Previa
        FlatIntelliJLaf.setup();
        
        // --- Pruebas
        
        int opc = 99999;
        MainFrame prueba = new MainFrame();
        while (opc != 0) {            
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, "opciones: 1-login, 2-menu, 3-tpv, 4-gestion"));
            
            switch (opc) {
                case 1:
                    prueba.mostrarLoginPanel();
                    break;
                case 2:
                    prueba.mostrarMenuPanel();
                    break;
                case 3:
                    prueba.mostrarTPVPanel();
                    break;
                case 4:
                    prueba.mostrarGestorPanel();
                    break;    
            }
        }
        
    }
    
}
