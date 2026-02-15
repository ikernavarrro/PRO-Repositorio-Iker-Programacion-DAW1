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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.zabalburu.daw1.actividad25.corriente_datos.dao.EmpleadoDAO;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Empleado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {

    private static EmpleadoDAOImpl instancia;
    private static List<Empleado> empleados;

    private EmpleadoDAOImpl() {
        cargarDatos();
    }

    public static EmpleadoDAOImpl getInstancia() {
        if (instancia == null) {
            instancia = new EmpleadoDAOImpl();
        }
        return instancia;
    }

    @Override
    public void addEmpleado(Empleado nuevo) {
        Integer idMax = empleados.stream()
                .mapToInt(e -> e.getIdEmpleado())
                .max()
                .orElse(0);
        nuevo.setIdEmpleado(idMax + 1);
        empleados.add(nuevo);
        actualizarDatos();
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
            actualizarDatos();
        }
    }

    @Override
    public void removeEmpleado(Integer idEmpleado) {
        Empleado eliminar = getEmpleado(idEmpleado);
        if (eliminar != null) {
            empleados.remove(eliminar);
            actualizarDatos();
        }
    }

    private void cargarDatos() {
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

    private void actualizarDatos() {
        File f = new File("empleados.obj");
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))) {
            empleados.forEach(e -> {
                try {
                    oos.writeObject(e);
                } catch (IOException ex) {
                    System.getLogger(EmpleadoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            });
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
