/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.modelo;

import java.util.Date;
import java.util.Objects;
import org.zabalburu.daw1.flintbibliotecalibro_1.util.Categoria;

/**
 *
 * @author Iker Navarro Pérez
 */
public class Libro {
    private Integer id;
    private Categoria categoria;
    private String titulo;
    private String descripcion;
    private String autor;
    private String editorial;
    private Date fechaPublicacion;
    private String codigoISBN;
    private Biblioteca biblioteca;

    public Libro() {
    }

    public Libro(Integer id, Categoria categoria, String titulo, String descripcion, String autor, String editorial, Date fechaPublicacion, String codigoISBN, Biblioteca biblioteca) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.codigoISBN = codigoISBN;
        this.biblioteca = biblioteca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCodigoISBN() {
        return codigoISBN;
    }

    public void setCodigoISBN(String codigoISBN) {
        this.codigoISBN = codigoISBN;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + titulo;
    }
}
