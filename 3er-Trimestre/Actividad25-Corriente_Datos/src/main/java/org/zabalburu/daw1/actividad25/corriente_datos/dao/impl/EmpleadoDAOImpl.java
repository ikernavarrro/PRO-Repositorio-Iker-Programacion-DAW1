/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad25.corriente_datos.dao.impl;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.actividad25.corriente_datos.dao.EmpleadoDAO;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Empleado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {

    private static List<Empleado> empleados;

    public EmpleadoDAOImpl() {
        try {
            File f = new File("empleados.obj");
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));

            this.empleados = new ArrayList<>();
            try {
                while (true) {
                    Empleado e = (Empleado) ois.readObject();
                    empleados.add(e);
                }
            } catch (EOFException ex) {
                // Fin de fichero
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addEmpleado(Empleado nuevo) {
        Integer idMax = empleados.stream()
                .mapToInt(e -> e.getIdEmpleado())
                .max()
                .orElse(0);
        nuevo.setIdEmpleado(idMax + 1);
        empleados.add(nuevo);
    }

    @Override
    public Empleado getEmpleado(Integer idEmpleado) {
        return empleados.stream()
                .filter(e -> e.getIdEmpleado().equals(idEmpleado))
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public void modifyEmpleado(Empleado modificar) {
        int pos = empleados.indexOf(modificar);
        if (pos != -1) {
            empleados.set(pos, modificar);
        }
    }

    @Override
    public void removeEmpleado(Integer idEmpleado) {
        Empleado eliminar = getEmpleado(idEmpleado);
        if (eliminar != null) {
            empleados.remove(eliminar);
        }
    }

}
