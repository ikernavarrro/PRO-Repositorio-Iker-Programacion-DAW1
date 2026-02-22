/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.gestock;

import org.zabalburu.daw1.gestock.excepciones.AlmacenNoEncontradoException;
import org.zabalburu.daw1.gestock.modelo.Almacen;
import org.zabalburu.daw1.gestock.servicio.GestockServicio;
import org.zabalburu.daw1.gestock.util.ConexionBBDD;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Gestock {

    public static void main(String[] args) {
        System.out.println("Hello Gestock!");
        System.out.println(ConexionBBDD.getConnection());
        GestockServicio servicio = GestockServicio.getInstance();
        System.out.println("======ALMACENES=======");
        System.out.println(servicio.getAlmacenes());
        System.out.println("======PRODUCTOS======");
        System.out.println(servicio.getProductos());
        try {
            servicio.modifyAlmacen(new Almacen(1, "Almacen Central", "Calle Rivera del Duero, Nro 19, Sector 3", 1000));
            servicio.modifyAlmacen(new Almacen(2, "Almacen Norte", "Avenida de los Fusilados, Nro 45", 500));
        } catch (AlmacenNoEncontradoException ex) {
            System.getLogger(Gestock.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        /* servicio.addAlmacen(new Almacen(null, "Almacen Norte", "Avenida de los Fusilados, nro 45", 500));
        System.out.println("______________________________________");
        System.out.println("======ALMACENES=======");
        System.out.println(servicio.getAlmacenes());
        System.out.println("======PRODUCTOS======");
        System.out.println(servicio.getProductos());*/
    }
}
