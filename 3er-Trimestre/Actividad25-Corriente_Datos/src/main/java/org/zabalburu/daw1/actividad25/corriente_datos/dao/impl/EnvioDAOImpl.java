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
import org.zabalburu.daw1.actividad25.corriente_datos.dao.EnvioDAO;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Empleado;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Envio;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EnvioDAOImpl implements EnvioDAO {

    private static EnvioDAOImpl instancia;
    private static List<Envio> envios;

    private EnvioDAOImpl() {
        cargarDatos();
    }

    public static EnvioDAOImpl getInstancia() {
        if (instancia == null) {
            instancia = new EnvioDAOImpl();
        }
        return instancia;
    }

    @Override
    public void addEnvio(Envio nuevo) {
        Integer idMax = envios.stream()
                .mapToInt(e -> e.getIdEnvio())
                .max()
                .orElse(0);
        nuevo.setIdEnvio(idMax + 1);
        nuevo.getEmpleado().getEnvios().add(nuevo);
        envios.add(nuevo);
        actualizarDatos();
    }

    @Override
    public Envio getEnvio(Integer idEnvio) {
        return envios.stream()
                .filter(e -> e.getIdEnvio().equals(idEnvio))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Envio> getEnvios() {
        return envios;
    }

    @Override
    public void modifyEnvio(Envio modificar) {
        int pos = envios.indexOf(modificar);
        if (pos != -1) {
            envios.set(pos, modificar);
            actualizarDatos();
        }
    }

    @Override
    public void removeEnvio(Integer idEnvio) {
        Envio eliminar = getEnvio(idEnvio);
        if (eliminar != null) {
            eliminar.getEmpleado().getEnvios().remove(eliminar);
            envios.remove(eliminar);
            actualizarDatos();
        }
    }

    private void cargarDatos() {
        try {
            File f = new File("envios.obj");
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));

            this.envios = new ArrayList<>();
            try {
                while (true) {
                    Envio e = (Envio) ois.readObject();
                    envios.add(e);
                }
            } catch (EOFException ex) {
                // Fin de fichero
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            ois.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarDatos() {
        File f = new File("envios.obj");
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)))) {
            envios.forEach(e -> {
                try {
                    oos.writeObject(e);
                } catch (IOException ex) {
                    System.getLogger(EnvioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            });
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
