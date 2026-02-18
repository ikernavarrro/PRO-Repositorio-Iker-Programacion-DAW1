/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.zabalburu.daw1.tpv_gestion_ventas.config.AppConfig;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Usuario;

/**
 *
 * @author Iker navarro Pérez
 */
public class MenuPanel extends JPanel {
    
    //COMPONENTES
    //pnlUsuarioSesion
    private JLabel lblFotoUsuario = new JLabel();
    private JLabel lblNombreUsuario = new JLabel("Usuario: ");
    private JTextField txtNombreUsuario = new JTextField();
    private JLabel lblRango = new JLabel("Rango: ");
    private JTextField txtRango = new JTextField();
    private JButton btnCerrarSesion = new JButton();
     private GridBagLayout grid = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel pnlUsuarioSesion = new JPanel(grid);
    
    //pnlOpciones
    private JButton btnIniciarTPV = new JButton("Iniciar TPV");
    private JButton btnGestionarProductos = new JButton("Gestionar Productos");
    private JPanel pnlOpciones = new JPanel(); 
    
    //Constructor
    public MenuPanel() {
        //Configuración Panel Menu
         this.setBorder(AppConfig.bordeMarco);
         this.setLayout(new BorderLayout(5, 5));
         
         //pnlUsuarioSesion
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.gridwidth = 1;
         gbc.anchor = GridBagConstraints.WEST;
         grid.setConstraints(lblFotoUsuario, gbc);
         lblFotoUsuario.setOpaque(true);
         pnlUsuarioSesion.add(lblFotoUsuario);
         
         //pnlOpciones
         btnIniciarTPV.setIcon(new ImageIcon("icons/tpv.png"));
         btnIniciarTPV.setFont(AppConfig.FNT_TITULO.deriveFont(16f));
         pnlOpciones.add(btnIniciarTPV);
         btnGestionarProductos.setIcon(new ImageIcon("icons/gestionar_productos.png"));
         btnGestionarProductos.setFont(AppConfig.FNT_TITULO.deriveFont(16f));
         pnlOpciones.add(btnGestionarProductos);
         
         //Añadiendo paneles al principal.
         this.add(pnlUsuarioSesion, BorderLayout.NORTH);
         this.add(pnlOpciones, BorderLayout.SOUTH);
    }
    
    //GETTERS
    //MÉTODOS
    
}
