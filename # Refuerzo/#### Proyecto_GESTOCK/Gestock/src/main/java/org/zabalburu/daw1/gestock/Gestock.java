/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.gestock;

import org.zabalburu.daw1.gestock.modelo.Almacen;
import org.zabalburu.daw1.gestock.util.ConexionBBDD;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Gestock {

    public static void main(String[] args) {
        System.out.println("Hello Gestock!");
        System.out.println(ConexionBBDD.getConnection());
       
    }
}
