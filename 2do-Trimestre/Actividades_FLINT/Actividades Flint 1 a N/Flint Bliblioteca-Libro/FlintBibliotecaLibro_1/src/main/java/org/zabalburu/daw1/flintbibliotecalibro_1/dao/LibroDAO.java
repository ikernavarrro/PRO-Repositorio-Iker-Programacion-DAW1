/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.dao;

import java.util.List;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Libro;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface LibroDAO {
    public Libro addLibro(Libro nuevo);
    public Libro getLibro(Integer id);
    public List<Libro> getLibros();
    public List<Libro> getLibrosBiblioteca(Integer idBiblioteca);
    public Libro modifyLibro(Libro modificar);
    public Libro deleteLibro(Integer id);
    
}
