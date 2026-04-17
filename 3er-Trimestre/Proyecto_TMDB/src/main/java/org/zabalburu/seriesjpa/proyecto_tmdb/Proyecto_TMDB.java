/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.seriesjpa.proyecto_tmdb;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl.UsuarioDAOImpl;
import org.zabalburu.seriesjpa.proyecto_tmdb.service.AuthService;
import org.zabalburu.seriesjpa.proyecto_tmdb.util.DataInitializer;
import org.zabalburu.seriesjpa.proyecto_tmdb.util.JPAUtil;
import org.zabalburu.seriesjpa.proyecto_tmdb.view.LoginFrame;

/**
 *
 * @author Iker Navarro Pérez
 *
 */
public class Proyecto_TMDB {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        
        DataInitializer.initialize();
        
        SwingUtilities.invokeLater(() -> {
            AuthService authService = new AuthService(
                    JPAUtil.getEntityManagerFactory(),
                    new UsuarioDAOImpl()
            );

            LoginFrame loginFrame = new LoginFrame(authService);
            loginFrame.setVisible(true);
        });
    }
}
