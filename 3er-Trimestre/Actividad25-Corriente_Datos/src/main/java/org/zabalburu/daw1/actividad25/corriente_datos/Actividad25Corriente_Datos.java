/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.zabalburu.daw1.actividad25.corriente_datos;

import org.zabalburu.daw1.actividad25.corriente_datos.servicio.GestionEnviosServicio;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Actividad25Corriente_Datos {

    public static void main(String[] args) {
        GestionEnviosServicio servicio = GestionEnviosServicio.getInstancia();
        System.out.println(servicio.getEmpleados());
    }
}
