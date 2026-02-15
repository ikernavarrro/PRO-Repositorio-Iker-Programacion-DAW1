/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.zabalburu.daw1.tpv_gestion_ventas.config.AppConfig;

/**
 *
 * @author Iker Navarro Pérez
 */
public class MainFrame extends JFrame {

    private LoginPanel pnlLogin;
    private MenuPanel pnlMenu;
    private TPVPanel pnlTPV;
    private GestorProductosPanel pnlGestorProductos;

    //Constructor
    public MainFrame() {
        //Inicializamos los paneles
        pnlLogin = new LoginPanel();
        pnlMenu = new MenuPanel();
        pnlTPV = new TPVPanel();
        pnlGestorProductos = new GestorProductosPanel();

        //Configuración MainFrame
        this.setTitle("TPV - NAVIKER");
        this.setIconImage(new ImageIcon("icons/favicon_naviker.png").getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(pnlLogin);
    }

    //Métodos 
    
    private void reconfigurarVentana(boolean maximizada, Dimension tamaño) {
        this.setVisible(false);
        this.getContentPane().removeAll(); // Quitamos TODOS los paneles que hayan.
        this.setResizable(false);    
        if (maximizada) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizamos
        } else {
            this.dispose();                              // Destruímos la ventana temporalmente
            this.setExtendedState(JFrame.NORMAL);        // Volvemos a normal
            this.invalidate();                           // Recalcula layout           
            this.repaint();                              // Redibujamos la ventana                  
            this.setSize(tamaño);                         
        }
        this.setLocationRelativeTo(null); // Centramos
    }

    public void mostrarMenuPanel() {
        reconfigurarVentana(false, AppConfig.dmVentanaMenu);
        this.add(pnlMenu); 
        this.revalidate(); 
        this.repaint(); 
        this.setVisible(true);
    }

    public void mostrarTPVPanel() {
        reconfigurarVentana(true, AppConfig.dmVentanaTPV);
        this.add(pnlTPV);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void mostrarGestorPanel() {
        reconfigurarVentana(false, AppConfig.dmVentanaGestionProductos);
        this.add(pnlGestorProductos);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void mostrarLoginPanel() {
        reconfigurarVentana(false, AppConfig.dmVentanaLogin);
        this.add(pnlLogin);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "TPV - Informa", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "TPV - Error", JOptionPane.ERROR_MESSAGE);
    }
}
