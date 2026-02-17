/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.excepciones;

/**
 *
 * @author Iker Navarro Pérez
 */
public class StockInsuficienteException extends Exception {

    public StockInsuficienteException() {
        super("No hay suficiente stock disponible para realizar la operación.");
    }

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
