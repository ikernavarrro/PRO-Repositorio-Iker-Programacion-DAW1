/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.flintbibliotecalibro_1.dao.BibliotecaDAO;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Biblioteca;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Libro;

/**
 *
 * @author Iker Navarro Pérez
 */
public class BibliotecaIMPL implements BibliotecaDAO {

    private static List<Biblioteca> bibliotecas;

    public BibliotecaIMPL() {
        this.bibliotecas = new ArrayList<>();
    }

    /**
     * Para agregar una nueva biblioteca. Lógica ID: Primero de todo declaramos
     * una avariable local denominada id con valor 1 miramos si la lista de
     * bibliotecas NO(!) está vacía y si es así asignamos a la variable local id
     * el id del último en la lista +1. Y después lo añadimos a la lista.
     *
     * @param nuevo
     * @return Devuelve null si nuevo es null y si no devuelve nuevo.
     */
    @Override
    public Biblioteca addBiblioteca(Biblioteca nuevo) {
        if (nuevo != null) {
            Integer id = 1;
            if (!bibliotecas.isEmpty()) {
                id = bibliotecas.get(bibliotecas.size() - 1).getId() + 1;
            }
            nuevo.setId(id);
            bibliotecas.add(nuevo);
            return nuevo;
        } else {
            return null;
        }
    }

    /**
     * Para recuperar una biblioteca. En base al id de la biblioteca realizamos
     * una busqueda sin saltos incondicionales. Si la biblioteca NO está el
     * valor de la i será mayor a bibliotecas.size() y devolverá null. Y si está
     * el valor de la i será menor a size y devolverá la biblioteca encontrada.
     *
     * @param id
     * @return Devuelve null si nuevo es null y si no devuelve nuevo.
     */
    @Override
    public Biblioteca getBiblioteca(Integer id) {
        int i;
        for (i = 0; i < bibliotecas.size() && bibliotecas.get(i).getId() != id; i++);
        if (i < bibliotecas.size()) {
            return bibliotecas.get(i);
        } else {
            return null;
        }
    }

    /**
     * Para recuperar la lsita de bibliotecas.
     *
     * @return Devuelve la lista de bibliotecas.
     */
    @Override
    public List<Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    /**
     * Para modificar una biblioteca.
     *
     * @param modificar
     * @return Devuelve null si la biblioteca que quieres modificar no está en
     * la lista.
     */
    @Override
    public Biblioteca modifyBiblioteca(Biblioteca modificar) {
        int pos = bibliotecas.indexOf(modificar);
        if (pos != -1) {
            bibliotecas.set(pos, modificar);
            return modificar;
        } else {
            return null;
        }
    }

    /**
     * Para eliminar una biblioteca.
     * Cuando se elimine la biblioteca se pondrán todos sus libros en null(La biblioteca a la que pertenece)
     * @param id
     * @return Devuelve la biblioteca borrada(solo con id).
     */
    @Override
    public Biblioteca deleteBiblioteca(Integer id) {
        Biblioteca b = new Biblioteca();
        b.setId(id);
        int pos = bibliotecas.indexOf(b);
        if (pos != -1) {
            if (bibliotecas.get(pos).getLibros() != null) {
                for (Libro libro : bibliotecas.get(pos).getLibros()) {
                    libro.setBiblioteca(null);
                }
            }
            bibliotecas.remove(pos);
            return b;
        } else {
            return null;
        }
    }
}
