/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Iker Navarro Pérez
 */
@Entity
@Table(name = "valoracionesTMDB")
@SequenceGenerator(name = "seq_valoracion", sequenceName = "SEQ_VALORACION", allocationSize = 1)
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_valoracion")
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private int estrellas;

    @Column(length = 1000)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    public Valoracion() {
    }

    // Constructor completo
    public Valoracion(Long id, LocalDate fecha, int estrellas, String comentario, Usuario usuario, Serie serie) {
        this.id = id;
        this.fecha = fecha;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.usuario = usuario;
        this.serie = serie;
    }

    // Constructor recomendado para usar en DataInitializer
    public Valoracion(int estrellas, String comentario, LocalDate fecha) {
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Constructor simplificado (asume fecha actual)
    public Valoracion(int estrellas, String comentario) {
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.fecha = LocalDate.now();
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        if (estrellas < 1 || estrellas > 5) {
            throw new IllegalArgumentException("Las estrellas deben ser un valor entre 1 y 5");
        }
        this.estrellas = estrellas;
    }

    public void actualizar(int estrellas, String comentario) {
        setEstrellas(estrellas);
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Valoracion other = (Valoracion) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Valoracion{" + "id=" + id + ", fecha=" + fecha + ", estrellas=" + estrellas + ", comentario=" + comentario + ", usuario=" + usuario + ", serie=" + serie + '}';
    }

}
