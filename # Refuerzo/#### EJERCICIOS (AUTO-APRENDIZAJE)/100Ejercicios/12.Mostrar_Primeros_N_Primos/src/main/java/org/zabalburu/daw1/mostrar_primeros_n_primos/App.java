/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.mostrar_primeros_n_primos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 12.Mostrar los primeros N números primos. 01/02/2026
 *
 * @author Iker Navarro Pérez
 */
public class App {

    public static void main(String[] args) {
        boolean ok = false;
        do {
            try {
                String numero = JOptionPane.showInputDialog(null, "Introduzca cuantos primos quiere ver:", "Muestra N Números Primos", JOptionPane.INFORMATION_MESSAGE);
                if (numero == null) {
                    ok = true; // Salimos
                } else {
                    long numeroParsed = Long.parseLong(numero);
                    long contadorPrimos = 0;
                    List<Integer> listaPrimos = new ArrayList<>();
                    if (numeroParsed > 0) {
                        for (int j = 2; contadorPrimos < numeroParsed; j++) {
                            boolean esPrimo = true;
                            for (int i = 2; i < j && esPrimo != false; i++) { 
                                if (j % i == 0) {
                                    esPrimo = false; 
                                }
                            }
                            if (esPrimo) {
                                contadorPrimos++;
                                listaPrimos.add(j);
                            }
                        }
                    }
                    System.out.println(listaPrimos);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "¡El valor introducido es incorrecto!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!ok);
    }
}
