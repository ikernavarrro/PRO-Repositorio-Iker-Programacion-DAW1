/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintescuelaalumno_1.servicio;

import java.util.List;
import org.zabalburu.daw1.flintescuelaalumno_1.dao.AlumnoDAO;
import org.zabalburu.daw1.flintescuelaalumno_1.dao.EscuelaDAO;
import org.zabalburu.daw1.flintescuelaalumno_1.dao.impl.AlumnoIMPL;
import org.zabalburu.daw1.flintescuelaalumno_1.dao.impl.EscuelaIMPL;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Alumno;
import org.zabalburu.daw1.flintescuelaalumno_1.modelo.Escuela;

/**
 *
 * @author Iker Navarro Pérez
 */
public class EscuelaServicio {

    private EscuelaDAO escuelaDAO;
    private AlumnoDAO alumnoDAO;

    public EscuelaServicio() {
        this.escuelaDAO = new EscuelaIMPL();
        this.alumnoDAO = new AlumnoIMPL();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Crear 3 escuelas
        Escuela escuela1 = new Escuela(null, "IES Zabalburu", "Calle Principal 123, Bilbao", "944123456");
        Escuela escuela2 = new Escuela(null, "Colegio San José", "Avenida Libertad 456, Getxo", "944654321");
        Escuela escuela3 = new Escuela(null, "Instituto Tecnológico", "Plaza Mayor 789, Barakaldo", "944987654");

        escuelaDAO.addEscuela(escuela1);
        escuelaDAO.addEscuela(escuela2);
        escuelaDAO.addEscuela(escuela3);

        // 10 alumnos para Escuela 1
        alumnoDAO.addAlumno(new Alumno(null, "Jon", "García López", "jon.garcia@email.com", 8.5, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "María", "Rodríguez Pérez", "maria.rodriguez@email.com", 9.2, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Carlos", "Martínez Ruiz", "carlos.martinez@email.com", 7.8, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Laura", "Fernández Gómez", "laura.fernandez@email.com", 8.9, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "David", "López Sánchez", "david.lopez@email.com", 7.5, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Ana", "González Torres", "ana.gonzalez@email.com", 9.1, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Pedro", "Jiménez Díaz", "pedro.jimenez@email.com", 8.3, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Sofia", "Moreno Castillo", "sofia.moreno@email.com", 8.7, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Miguel", "Ramírez Flores", "miguel.ramirez@email.com", 7.9, escuela1));
        alumnoDAO.addAlumno(new Alumno(null, "Elena", "Vargas Medina", "elena.vargas@email.com", 9.0, escuela1));

        // 10 alumnos para Escuela 2
        alumnoDAO.addAlumno(new Alumno(null, "Iker", "Navarro Pérez", "iker.navarro@email.com", 8.6, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Ainhoa", "Sáenz Ibáñez", "ainhoa.saenz@email.com", 8.8, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Xabier", "Etxebarria Landa", "xabier.etxebarria@email.com", 7.7, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Leire", "Urquiza Goikoetxea", "leire.urquiza@email.com", 9.3, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Eneko", "Azpeitia Arteaga", "eneko.azpeitia@email.com", 8.1, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Marta", "Irujo Mendoza", "marta.irujo@email.com", 8.4, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Gorka", "Zubieta Ortiz", "gorka.zubieta@email.com", 7.6, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Nerea", "Azcona Ruiz", "nerea.azcona@email.com", 9.2, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Mikel", "Otegi Gómez", "mikel.otegi@email.com", 8.0, escuela2));
        alumnoDAO.addAlumno(new Alumno(null, "Izaskun", "Larrañaga Iglesias", "izaskun.larranaga@email.com", 8.9, escuela2));

        // 10 alumnos para Escuela 3
        alumnoDAO.addAlumno(new Alumno(null, "Javier", "Sánchez Blanco", "javier.sanchez@email.com", 8.2, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Cristina", "Álvarez Núñez", "cristina.alvarez@email.com", 9.1, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Roberto", "Domínguez Vega", "roberto.dominguez@email.com", 7.4, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Beatriz", "Molina Ramos", "beatriz.molina@email.com", 8.8, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Francisco", "Herrera Campos", "francisco.herrera@email.com", 7.9, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Verónica", "Navarro Soto", "veronica.navarro@email.com", 9.0, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Antonio", "Guerrero Ponce", "antonio.guerrero@email.com", 8.5, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Susana", "Cabrera Fuentes", "susana.cabrera@email.com", 8.6, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Ángel", "Delgado Romero", "angel.delgado@email.com", 7.8, escuela3));
        alumnoDAO.addAlumno(new Alumno(null, "Raquel", "Serrano Iglesias", "raquel.serrano@email.com", 9.2, escuela3));
    }

    // Gestión Escuela
    public Escuela addEscuela(String nombre, String direccion, String telefono) {
        Escuela e = new Escuela(null, nombre, direccion, telefono);
        return escuelaDAO.addEscuela(e);
    }

    public Escuela getEscuela(Integer id) {
        return escuelaDAO.getEscuela(id);
    }

    public List<Escuela> getEscuelas() {
        return escuelaDAO.getEscuelas();
    }

    public Escuela modifyEscuela(Escuela modificar) {
        return escuelaDAO.modifyEscuela(modificar);
    }

    public Escuela deleteEscuela(Integer id) {
        return escuelaDAO.deleteEscuela(id);
    }

    // Gestión Alumno
    public Alumno addAlumno(String nombre, String apellido, String email, Double calificacion, Escuela escuela) {
        if (escuela != null) {
            Alumno a = new Alumno(null, nombre, apellido, email, calificacion, escuela);
            return alumnoDAO.addAlumno(a);
        } else {
            return null;
        }
    }

    public Alumno getAlumno(Integer id) {
        return alumnoDAO.getAlumno(id);
    }

    public List<Alumno> getAlumnos() {
        return alumnoDAO.getAlumnos();
    }

    public Alumno modifyAlumno(Alumno modificar) {
        return alumnoDAO.modifyAlumno(modificar);
    }

    public Alumno deleteAlumno(Integer id) {
        return alumnoDAO.deleteAlumno(id);
    }

    public List<Alumno> getAlumnosEscuela(Integer idEscuela) {
        return alumnoDAO.getAlumnosEscuela(idEscuela);
    }
}
