/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flintbibliotecalibro_1.dao.LibroDAO;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Libro;

/**
 *
 * @author Iker Navarro Pérez
 */
public class LibroIMPL implements LibroDAO {

    private static List<Libro> libros;

    public LibroIMPL() {
        this.libros = new ArrayList<>();
    }

    @Override
    public Libro addLibro(Libro nuevo) {
        if (nuevo != null) {
            Integer id = 1;
            if (!libros.isEmpty()) {
                id = libros.get(libros.size() - 1).getId() + 1;
            }
            nuevo.setId(id);
            libros.add(nuevo);
            return nuevo;
        } else {
            return null;
        }
    }

    @Override
    public Libro getLibro(Integer id) {
        int i;
        for (i = 0; i < libros.size() && libros.get(i).getId() != id; i++);
        if (i < libros.size()) {
            return libros.get(i);
        } else {
            return null;
        }
    }

    @Override
    public List<Libro> getLibros() {
        return libros;
    }

    @Override
    public List<Libro> getLibrosBiblioteca(Integer idBiblioteca) {
        List<Libro> resultado = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getBiblioteca() != null && libro.getBiblioteca().getId().equals(idBiblioteca)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    @Override
    public Libro modifyLibro(Libro modificar) {
        int pos = libros.indexOf(modificar);
        if (pos != -1) {
            libros.set(pos, modificar);
            return modificar;
        } else {
            return null;
        }
    }

    @Override
    public Libro deleteLibro(Integer id) {
        Libro b = new Libro();
        b.setId(id);
        int pos = libros.indexOf(b);
        if (pos != -1) {
            libros.remove(pos);
            return b;
        } else {
            return null;
        }
    }

}
