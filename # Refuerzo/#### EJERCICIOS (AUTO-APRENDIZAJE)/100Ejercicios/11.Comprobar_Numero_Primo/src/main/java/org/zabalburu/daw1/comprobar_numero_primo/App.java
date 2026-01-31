/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.comprobar_numero_primo;

import javax.swing.JOptionPane;

/**
 * 11. Comprobar si un número es primo. 31/01/2026
 *
 * @author Iker Navarro Pérez
 */
public class App {

    public static void main(String[] args) {
        boolean ok = false;
        do {
            try {
                String numero = JOptionPane.showInputDialog(null, "Introduzca un número para saber si es primo.", "Comprueba si un número es Primo - Iker", JOptionPane.INFORMATION_MESSAGE);
                if (numero == null) {
                    ok = true; // Salimos
                } else {
                    long numeroParsed = Long.parseLong(numero);
                    boolean esPrimo = true;
                    if (numeroParsed > 1) {
                        for (long i = 2; i < numeroParsed && esPrimo != false; i++) {
                            if (numeroParsed % i == 0) {
                                esPrimo = false;
                            }
                        }
                    } else {
                        esPrimo = false;
                    }
                    JOptionPane.showMessageDialog(null, "El número %d %s es primo.".formatted(numeroParsed, esPrimo ? "SI" : "NO"));
                    // Volvemos a pedir otro número hasta que el usuario le de a la X o a cancelar.
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡El valor introducido es incorrecto!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!ok);
    }
}
