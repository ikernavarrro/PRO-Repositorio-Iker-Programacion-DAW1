/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.pedir_numero_contar_digitos;

import javax.swing.JOptionPane;

/**
 * 10. Pedir un número y contar sus dígitos. 31/01/2026
 *
 * @author Iker Navarro Pérez
 */
public class App {

    public static void main(String[] args) {
        boolean ok = false;
        do {
            try {
                String numero = JOptionPane.showInputDialog(null, "Introduzca un número para contar sus dígitos.", "Contador de Dígitos - Iker", JOptionPane.INFORMATION_MESSAGE);
                if (numero == null) {
                    ok = true; // Salimos
                } else {
                    long numeroParsed = Long.parseLong(numero);
                    JOptionPane.showMessageDialog(null, "El número %d tiene %s dígitos.".formatted(numeroParsed, numero.length()));
                    // Volvemos a pedir otro número hasta que el usuario le de a la X o a cancelar.
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡El valor introducido es incorrecto!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!ok);
        
    }
}
