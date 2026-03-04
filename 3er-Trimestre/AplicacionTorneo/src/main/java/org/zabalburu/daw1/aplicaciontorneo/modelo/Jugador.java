/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.zabalburu.daw1.aplicaciontorneo.util.CircleImage;

/**
 *
 * @author Iker Navarro Pérez
 */
@Data  
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jugador {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre;
    private String apellidos;
    private String nick;
    private String imagen;
    @ToString.Exclude
    private List<Partida> partidas = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private ImageIcon normal;
    @Setter(AccessLevel.NONE)
    private ImageIcon avatar;

    private static final int ANCHO_NORMAL = 150;
    private static final int ANCHO_AVATAR = 40;
    private static final String DIR_PRINCIPAL = "imagenes/";
    private static final String DIR_AVATARES = "imagenes/avatares/";
    private static final String DIR_NORMAL = "imagenes/normal/";
    private static final String DIR_IMAGEN_DEFECTO_NORMAL = "imagenes/defecto/imagen_normal.png";
    private static final String DIR_IMAGEN_DEFECTO_AVATAR = "imagenes/defecto/imagen_avatar.png";

    public void setImagenesNormalAvatar() {
        File f = new File(imagen); // Buscamos la original.
        if (f.exists()) {
            try {
                // === NORMAL ===
                BufferedImage bffImage = ImageIO.read(f); // Leemos el archivo.
                Image img = bffImage; //Lo transformamos a Imagen.
                img = img.getScaledInstance(ANCHO_NORMAL, ANCHO_NORMAL, Image.SCALE_SMOOTH); // Escalamos normal 
                BufferedImage circleImage = (BufferedImage) CircleImage.getCircleImage(img); // Convertimos a circulo
                ImageIO.write(circleImage, "PNG", new File("imagenes/normal", nick + "normal.png")); // Escribimos
                normal = new ImageIcon(circleImage); // Asignamos
                // === AVATAR ===
                img = bffImage;// Volvemos a transformar para después escalar desde la original y no desde la escalada anteriormente
                img = img.getScaledInstance(ANCHO_AVATAR, ANCHO_AVATAR, Image.SCALE_SMOOTH); // Escalamos avatar
                circleImage = (BufferedImage) CircleImage.getCircleImage(img); // Convertimos a circulo
                ImageIO.write(circleImage, "PNG", new File("imagenes/avatares", nick + "avatar.png")); // Escribimos
                avatar = new ImageIcon(circleImage); // Asignamos
            } catch (IOException ex) {
                System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    public ImageIcon getImagenNormal() {
        if (normal == null) {
            File f = new File(DIR_NORMAL + nick + "normal.png"); // Buscamos el archivo.
            if (f.exists()) { //Si el archivo existe.
                try {
                    BufferedImage bffImage = ImageIO.read(f); // Leemos el archivo.
                    Image img = bffImage; //Lo transformamos a Imagen.
                    normal = new ImageIcon(img); // Asignamos
                } catch (IOException ex) {
                    System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } else {
                try { // IMAGEN POR DEFECTO 
                    File dft = new File(DIR_IMAGEN_DEFECTO_NORMAL);
                    BufferedImage bffImage2 = ImageIO.read(dft);
                    Image imagenDefecto = bffImage2;
                    normal = new ImageIcon(imagenDefecto);
                } catch (IOException ex) {
                    System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
        return normal;
    }

    public ImageIcon getImagenAvatar() {
        if (avatar == null) {
            File f = new File(DIR_AVATARES + nick + "avatar.png"); // Buscamos el archivo.
            if (f.exists()) { //Si el archivo existe.
                try {
                    BufferedImage bffImage = ImageIO.read(f); // Leemos el archivo.
                    Image img = bffImage; //Lo transformamos a Imagen.
                    avatar = new ImageIcon(img); // Asignamos
                } catch (IOException ex) {
                    System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } else {
                try { // IMAGEN POR DEFECTO 
                    File dft = new File(DIR_IMAGEN_DEFECTO_AVATAR);
                    BufferedImage bffImage2 = ImageIO.read(dft);
                    Image imagenDefecto = bffImage2;
                    avatar = new ImageIcon(imagenDefecto);
                } catch (IOException ex) {
                    System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
        return avatar;
    }

}
