/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.cinemamanagement;


import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import org.zabalburu.daw1.cinemamanagement.vista.FrmCinema;


/**
 *
 * @author Iker Navarro Pérez
 */
public class CinemaManagement {

    public static void main(String[] args) {
        FlatLaf.setup(new FlatArcOrangeIJTheme());
        // Redondeo general
        UIManager.put("Component.arc", 12);
        UIManager.put("Button.arc", 25);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("ScrollBar.thumbArc", 999);

        // Espaciado (que no se vea "apretado")
        UIManager.put("TextField.margin", new Insets(6, 10, 6, 10));
        UIManager.put("PasswordField.margin", new Insets(6, 10, 6, 10));
        UIManager.put("TextArea.margin", new Insets(8, 10, 8, 10));
        UIManager.put("Button.margin", new Insets(8, 14, 8, 14));

        // Focus más discreto
        UIManager.put("Component.focusWidth", 2);
        UIManager.put("Component.innerFocusWidth", 0);

        // Tablas más modernas
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("Table.showHorizontalLines", false);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.intercellSpacing", new Dimension(0, 0));

        // Scrollbars finas
        UIManager.put("ScrollBar.width", 12);

        // (Opcional) Quitar borde por defecto de ScrollPane
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
        new FrmCinema().setVisible(true);
        System.out.println("Hello World!");
    }
}
