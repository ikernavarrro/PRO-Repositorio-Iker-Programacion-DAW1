/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.flintescuelaalumno_1.dao;

import java.util.List;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Escuela;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface EscuelaDAO {
    public Escuela addEscuela(Escuela nueva);
    public Escuela getEscuela(Integer id);
    public List<Escuela> getEscuelas();
    public Escuela modifyEscuela(Escuela modificar);
    public Escuela deleteEscuela(Integer id);
    
}
