/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad25.corriente_datos.servicio;

import java.util.List;
import org.zabalburu.daw1.actividad25.corriente_datos.dao.EmpleadoDAO;
import org.zabalburu.daw1.actividad25.corriente_datos.dao.EnvioDAO;
import org.zabalburu.daw1.actividad25.corriente_datos.dao.impl.EmpleadoDAOImpl;
import org.zabalburu.daw1.actividad25.corriente_datos.dao.impl.EnvioDAOImpl;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Empleado;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Envio;

/**
 *
 * @author Iker Navarro Pérez
 */
public class GestionEnviosServicio {

    private static GestionEnviosServicio instancia;
    private EmpleadoDAO empleadoDAO;
    private EnvioDAO envioDAO;

    private GestionEnviosServicio() {
        this.empleadoDAO = EmpleadoDAOImpl.getInstancia();
        this.envioDAO = EnvioDAOImpl.getInstancia();
    }

    public static GestionEnviosServicio getInstancia() {
        if (instancia == null) {
            instancia = new GestionEnviosServicio();
        }
        return instancia;
    }

    // ====== ENVIOS
    public void addEnvio(Envio nuevo) {
        envioDAO.addEnvio(nuevo);
    }

    public Envio getEnvio(Integer idEnvio) {
        return envioDAO.getEnvio(idEnvio);
    }

    public List<Envio> getEnvios() {
        return envioDAO.getEnvios();
    }

    public void modifyEnvio(Envio modificar) {
        envioDAO.modifyEnvio(modificar);
    }

    public void removeEnvio(Integer idEnvio) {
        envioDAO.removeEnvio(idEnvio);
    }

    // ====== EMPLEADOS
    public void addEmpleado(Empleado nuevo) {
        empleadoDAO.addEmpleado(nuevo);
    }

    public Empleado getEmpleado(Integer idEmpleado) {
        return empleadoDAO.getEmpleado(idEmpleado);
    }

    public List<Empleado> getEmpleados() {
        return empleadoDAO.getEmpleados();
    }

    public void modifyEmpleado(Empleado modificar) {
        empleadoDAO.modifyEmpleado(modificar);
    }

    public void removeEmpleado(Integer idEmpleado) {
        empleadoDAO.removeEmpleado(idEmpleado);
    }
}
