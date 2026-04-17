/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static org.hibernate.internal.util.collections.ArrayHelper.add;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Usuario;

/**
 *
 * @author DAW1
 */
public class MainFrame extends JFrame {
    
    private Usuario usuario;
    
    public MainFrame(Usuario usuario) {
        setTitle("SeriesJPA - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        initComponents(usuario);
    }

    private void initComponents(Usuario usuario) {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Bienvenido, " + usuario.getNombreUsuario(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JLabel lblInfo = new JLabel("Pantalla principal de la aplicación");
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        centerPanel.add(lblInfo);

        add(lblTitulo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
