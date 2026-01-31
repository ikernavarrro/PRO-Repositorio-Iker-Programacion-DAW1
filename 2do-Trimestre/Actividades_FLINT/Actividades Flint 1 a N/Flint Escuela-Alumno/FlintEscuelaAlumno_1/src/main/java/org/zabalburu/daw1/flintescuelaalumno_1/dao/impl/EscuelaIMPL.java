/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintescuelaalumno_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flintescuelaalumno_1.dao.EscuelaDAO;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Alumno;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Escuela;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EscuelaIMPL implements EscuelaDAO {

    private static List<Escuela> escuelas;

    public EscuelaIMPL() {
        this.escuelas = new ArrayList<>();
    }
    
    @Override
    public Escuela addEscuela(Escuela nueva) {
        if (nueva != null) {
            Integer id = 1;
            if (!escuelas.isEmpty()) {
                id = escuelas.get(escuelas.size() - 1).getId() + 1;
            }
            nueva.setId(id);
            escuelas.add(nueva);
            return nueva;
        } else {
            return null;
        }
    }

    @Override
    public Escuela getEscuela(Integer id) {
        int i;
        for (i = 0; i < escuelas.size() && escuelas.get(i).getId() != id; i++);
        if (i < escuelas.size()) {
            return escuelas.get(i);
        } else {
            return null;
        }
    }

    @Override
    public List<Escuela> getEscuelas() {
        return escuelas;
    }

    @Override
    public Escuela modifyEscuela(Escuela modificar) {
        int pos = escuelas.indexOf(modificar);
        if (pos != -1) {
            escuelas.set(pos, modificar);
            return modificar;
        } else {
            return null;
        }
    }

    @Override
    public Escuela deleteEscuela(Integer id) {
        Escuela e = new Escuela();
        e.setId(id);
        int pos = escuelas.indexOf(e);
        if (pos != -1) {
            if (escuelas.get(pos).getAlumnos() != null) {
                for (Alumno alumno : escuelas.get(pos).getAlumnos()) {
                    alumno.setEscuela(null);
                }
            }
            escuelas.remove(pos);
            return e;
        } else {
            return null;
        }
    }
    
}
