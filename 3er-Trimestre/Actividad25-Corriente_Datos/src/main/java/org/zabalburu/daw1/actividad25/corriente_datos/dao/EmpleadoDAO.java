/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.actividad25.corriente_datos.dao;

import java.util.List;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Empleado;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface EmpleadoDAO {
    public void addEmpleado(Empleado nuevo);
    public Empleado getEmpleado(Integer idEmpleado);
    public List<Empleado> getEmpleados();
    public void modifyEmpleado(Empleado modificar);
    public void removeEmpleado(Integer idEmpleado);
    
}
