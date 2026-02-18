/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.zabalburu.daw1.tpv_gestion_ventas.config.AppConfig;

/**
 *
 * @author Iker Navarro Pérez
 */
public class LoginPanel extends JPanel {

    //Componentes
    //pnlUsuario
    private JLabel lblUsuario = new JLabel("Usuario");
    private JTextField txtUsuario = new JTextField();
    private JPanel pnlUsuario = new JPanel(new BorderLayout());
    //pnlPassword        
    private JLabel lblPassword = new JLabel("Contraseña");
    private JPasswordField pswPassword = new JPasswordField();
    private JPanel pnlPassword = new JPanel(new BorderLayout());
    //pnlContenido
    private JLabel lblTexto = new JLabel("Desarrollado Por Iker Navarro Pérez");
    private JButton btnLogin = new JButton("Iniciar Sesión");
    private JPanel pnlContenido = new JPanel(new BorderLayout());

    //Constructor
    public LoginPanel() {
        //Configuración Panel Login
        this.setBorder(AppConfig.bordeMarco);
        this.setLayout(new GridLayout(3, 1, 10, 10));

        //pnlUsuario
        lblUsuario.setFont(AppConfig.FNT_TEXTO);
        pnlUsuario.add(lblUsuario, BorderLayout.NORTH);
        pnlUsuario.add(txtUsuario, BorderLayout.SOUTH);

        //pnlPassword
        lblPassword.setFont(AppConfig.FNT_TEXTO);
        pnlPassword.add(lblPassword, BorderLayout.NORTH);
        pnlPassword.add(pswPassword, BorderLayout.SOUTH);

        //pnlContenido 
        lblTexto.setFont(AppConfig.FNT_ETIQUETA);
        pnlContenido.add(lblTexto, BorderLayout.WEST);
        btnLogin.setIcon(new ImageIcon("icons/login.png"));
        btnLogin.setFont(AppConfig.FNT_TEXTO.deriveFont(Font.BOLD));
        pnlContenido.add(btnLogin, BorderLayout.EAST);

        //Añadiendo paneles al panel principal.
        this.add(pnlUsuario);
        this.add(pnlPassword);
        this.add(pnlContenido);

    }

    // GETTERS
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContraseña() {
        return new String(pswPassword.getPassword());
    }

    public JButton getBotonLogin() {
        return btnLogin;
    }

    // MÉTODOS
    public void limpiarCampos() {
        txtUsuario.setText("");
        pswPassword.setText("");
    }

    public void enfocarUsuario() {
        txtUsuario.requestFocus();
    }
}
