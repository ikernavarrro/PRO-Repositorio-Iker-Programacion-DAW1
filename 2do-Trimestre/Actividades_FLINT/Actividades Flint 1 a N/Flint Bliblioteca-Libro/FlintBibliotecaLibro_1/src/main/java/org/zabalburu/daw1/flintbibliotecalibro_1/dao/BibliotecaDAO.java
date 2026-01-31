/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.dao;

import java.util.List;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Biblioteca;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface BibliotecaDAO {
    public Biblioteca addBiblioteca(Biblioteca nuevo);
    public Biblioteca getBiblioteca(Integer id);
    public List<Biblioteca> getBibliotecas();
    public Biblioteca modifyBiblioteca(Biblioteca modificar);
    public Biblioteca deleteBiblioteca(Integer id);
}
