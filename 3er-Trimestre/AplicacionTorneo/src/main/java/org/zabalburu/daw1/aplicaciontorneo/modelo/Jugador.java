/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    private ImageIcon normal;
    private ImageIcon avatar;

    public ImageIcon getImagen() {
        if (normal == null) {
            File f = new File("imagenes", "%d.png".formatted(id));
            if (f.exists()) {
                try {
                    BufferedImage imag = ImageIO.read(f);
                    ImageIcon im = new ImageIcon(imag);
                    normal = im;
                } catch (IOException ex) {
                    System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } else {
                normal = null;
            }
        }
        return normal;
    }
    
    public ImageIcon getAvatar() {
        if (avatar == null) {
            File f = new File("imagenes/avatares", "%d.png".formatted(id));
            if (f.exists()) {
                try {
                    BufferedImage imag = ImageIO.read(f);
                    ImageIcon im = new ImageIcon(imag);
                    avatar = im;
                } catch (IOException ex) {
                    System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } else {
                avatar = null;
            }
        }
        return avatar;
    }

    public void setImagen(String imagen) {
        /*this.imagen = imagen;
        if (imagen != null){
            try {
                normal = new ImageIcon(URI.create(imagen).toURL());
                avatar = new ImageIcon(URI.create(imagen).toURL());
                Image imag = avatar.getImage();
                imag = imag.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                //imag = CircleImage.getCircleImage(imag);
                avatar.setImage(imag);
            } catch (MalformedURLException ex) {
                System.getLogger(Jugador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }*/
    }
}
