/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author Iker Navarro Pérez
 */
public class GenerarImagenesJugadoresPrecargados {
    public static void main(String[] args) {
        Connection cnn = Conexion.getConnection();
        try {
            ResultSet rst = cnn.createStatement()
                    .executeQuery("SELECT id,imagen FROM JUGADORES");
            while (rst.next()) {                
                String ruta = rst.getString("imagen");
                int pos = ruta.lastIndexOf("/");
                
                Integer id = rst.getInt("id");
                ruta = ruta.substring(0,pos+1) + (id % 8 + 1) + ".jpg";
                BufferedImage imagen = ImageIO.read(URI.create(ruta).toURL());
                ImageIO.write(imagen, "PNG", new File("imagenes", "%d.png".formatted(id)));
                Image scaled = imagen.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                BufferedImage circleImage = (BufferedImage) CircleImage.getCircleImage(scaled);
                ImageIO.write(circleImage, "PNG", new File("imagenes/avatares", "%d.png".formatted(id)));
            }
        } catch (Exception ex) {
            System.getLogger(GenerarImagenesJugadoresPrecargados.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
    }
}
