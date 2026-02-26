/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.modelo;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
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
        }
    }
}
