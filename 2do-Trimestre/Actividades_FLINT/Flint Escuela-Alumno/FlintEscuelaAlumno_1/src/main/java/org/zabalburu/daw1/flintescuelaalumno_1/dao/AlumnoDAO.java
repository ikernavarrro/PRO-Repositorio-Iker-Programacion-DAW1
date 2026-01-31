/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.flintescuelaalumno_1.dao;

import java.util.List;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Alumno;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface AlumnoDAO {
    public Alumno addAlumno(Alumno nuevo);
    public Alumno getAlumno(Integer id);
    public List<Alumno> getAlumnos();
    public Alumno modifyAlumno(Alumno modificar);
    public Alumno deleteAlumno(Integer id);
    public List<Alumno> getAlumnosEscuela(Integer idEscuela); 
}
