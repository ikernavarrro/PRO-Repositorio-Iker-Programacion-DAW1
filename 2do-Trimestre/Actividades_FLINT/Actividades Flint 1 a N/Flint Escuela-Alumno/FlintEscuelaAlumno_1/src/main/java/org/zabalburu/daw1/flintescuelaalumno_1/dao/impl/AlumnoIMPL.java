/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintescuelaalumno_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flintescuelaalumno_1.dao.AlumnoDAO;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Alumno;

/**
 *
 * @author Iker Navarro Pérez
 */
public class AlumnoIMPL implements AlumnoDAO {

    private static List<Alumno> alumnos;

    public AlumnoIMPL() {
        this.alumnos = new ArrayList<>();
    }
    
    @Override
    public Alumno addAlumno(Alumno nuevo) {
        if (nuevo != null) {
            Integer id = 1;
            if (!alumnos.isEmpty()) {
                id = alumnos.get(alumnos.size() - 1).getId() + 1;
            }
            nuevo.setId(id);
            alumnos.add(nuevo);
            return nuevo;
        } else {
            return null;
        }
    }

    @Override
    public Alumno getAlumno(Integer id) {
        int i;
        for (i = 0; i < alumnos.size() && alumnos.get(i).getId() != id; i++);
        if (i < alumnos.size()) {
            return alumnos.get(i);
        } else {
            return null;
        }
    }

    @Override
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    @Override
    public Alumno modifyAlumno(Alumno modificar) {
        int pos = alumnos.indexOf(modificar);
        if (pos != -1) {
            alumnos.set(pos, modificar);
            return modificar;
        } else {
            return null;
        }
    }

    @Override
    public Alumno deleteAlumno(Integer id) {
        Alumno a = new Alumno();
        a.setId(id);
        int pos = alumnos.indexOf(a);
        if (pos != -1) {
            alumnos.remove(pos);
            return a;
        } else {
            return null;
        }
    }

    @Override
    public List<Alumno> getAlumnosEscuela(Integer idEscuela) {
        List<Alumno> resultado = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            if (alumno.getEscuela() != null && alumno.getEscuela().getId().equals(idEscuela)) {
                resultado.add(alumno);
            }
        }
        return resultado;
    }
    
}
