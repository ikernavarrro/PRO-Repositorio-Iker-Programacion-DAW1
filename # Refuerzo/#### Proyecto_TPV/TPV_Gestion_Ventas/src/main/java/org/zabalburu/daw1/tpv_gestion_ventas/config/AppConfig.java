/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.config;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Usuario;

/**
 *
 * @author Iker Navarro Pérez
 */
public class AppConfig {

    //Fuentes de Texto
    public static final Font FNT_TITULO = new Font("Franklin Gothic", Font.BOLD, 24);
    public static final Font FNT_TEXTO = new Font("Franklin Gothic", Font.PLAIN, 12);
    public static final Font FNT_ETIQUETA = new Font("Calibri", Font.BOLD, 12);

    //Dimensiones Ventanas
    public static final Dimension dmVentanaLogin = new Dimension(400, 250);
    public static final Dimension dmVentanaMenu = new Dimension(600, 400);
    public static final Dimension dmVentanaTPV = new Dimension(0, 0);
    public static final Dimension dmVentanaGestionProductos = new Dimension(0, 0);

    //Bordes
    public static final CompoundBorder bordeMarco = BorderFactory.createCompoundBorder( //A.1
                                                        BorderFactory.createEmptyBorder(10, 10, 10, 10), //B.1.1
                                                        BorderFactory.createCompoundBorder( //C.1.2
                                                                BorderFactory.createCompoundBorder( //D.1.2.1                              // Tenemos un borde (A) el cual le componen dos bordes (B,C) y 
                                                                        BorderFactory.createRaisedBevelBorder(),//E.1.2.1.1                // dentro del borde (C) lo componen dos bordes mas (D,G) y   
                                                                        BorderFactory.createLoweredBevelBorder()),//F.1.2.1.2              // dentro del borde (D) lo componen dos bordes mas (E,F)     
                                                                BorderFactory.createEmptyBorder(10, 10, 10, 10))); //G.1.2.2

    //Configuración Usuario
    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        AppConfig.usuario = usuario;
    }

}
