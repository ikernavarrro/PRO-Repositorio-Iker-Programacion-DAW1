/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.cinemamanagement;


import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme;
import org.zabalburu.daw1.cinemamanagement.vista.FrmCinema;


/**
 *
 * @author Iker Navarro Pérez
 */
public class CinemaManagement {

    public static void main(String[] args) {
        FlatLaf.setup(new FlatSpacegrayIJTheme());
        new FrmCinema().setVisible(true);
        System.out.println("Hello World!");
    }
}
