/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.excepciones;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ProductoNoEncontradoException extends Exception {

    public ProductoNoEncontradoException() {
        super("No se ha encontrado el producto solicitado.");
    }

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
