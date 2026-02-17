/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.excepciones;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EliminarAlmacenConProductosException extends Exception {

    public EliminarAlmacenConProductosException() {
        super("No se puede eliminar el almacén porque todavía contiene productos.");
    }

    public EliminarAlmacenConProductosException(String mensaje) {
        super(mensaje);
    }
}
