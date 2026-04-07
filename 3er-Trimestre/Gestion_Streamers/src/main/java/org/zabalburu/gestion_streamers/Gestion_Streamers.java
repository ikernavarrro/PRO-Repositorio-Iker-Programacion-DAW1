/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.gestion_streamers;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.zabalburu.gestion_streamers.view.FrmMenu;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Gestion_Streamers {

    public static void main(String[] args) {
        FlatLaf.setup(new FlatMacDarkLaf());
        new FrmMenu().setVisible(true);
    }
}
