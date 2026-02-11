/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad25.corriente_datos.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Empleado;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Envio;

/**
 *
 * @author Iker Navarro Pérez
 */
public class CargarFile {
    
        static Empleado emp1 = new Empleado(1, "Iker", "Navarro Pérez", new ArrayList<>());
        static Empleado emp2 = new Empleado(2, "Aaron", "Marrero Vargas", new ArrayList<>());
        static Empleado emp3 =  new Empleado(3, "Carlos", "Cifuentes Sanchez", new ArrayList<>());
        static Empleado emp4 = new Empleado(4, "Marta", "Torre Suarez", new ArrayList<>());
        static Empleado emp5 = new Empleado(5, "Iñigo", "Chueca López", new ArrayList<>());
        
        static Envio env1 = new Envio(1, "Calle Gran Vía, 28, Ático A Exterior", "Bilbao", emp1, LocalDate.now(), LocalDate.now().plusDays(3), 24.99);
        static Envio env2 = new Envio(2, "Calle Libertad, 8, Sector C, Parcela 57", "Madrid", emp2, LocalDate.now().minusDays(5), LocalDate.now(), 54.99);
        static Envio env3 = new Envio(3, "Avenida Constitución, 3, Oficina 20", "Barcelona", emp3, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), 14.99);
        
    
    private static Empleado[] empleados = {
        emp1,emp2,emp3,emp4,emp5
    };
    
    private static Envio[] envios = {
        env1,env2,env3
    };
    

    public static void main(String[] args) {
        try {
            File f = new File("empleados.obj");
            final ObjectOutputStream oosEmpleados = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            Stream.of(empleados).forEach(e -> {
                try {
                    oosEmpleados.writeObject(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            oosEmpleados.flush();
            oosEmpleados.close();
            // ===
            f = new File("envios.obj");
            final ObjectOutputStream oosEnvios = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            Stream.of(envios).forEach(e -> {
                try {
                    oosEnvios.writeObject(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            oosEnvios.flush();
            oosEnvios.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
