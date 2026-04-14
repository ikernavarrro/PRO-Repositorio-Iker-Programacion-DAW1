/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "generosTMDB")
@SequenceGenerator(name = "seq_genero", sequenceName = "SEQ_GENERO", allocationSize = 1)
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_genero")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @ManyToMany(mappedBy = "generos")
    private Set<Serie> series = new HashSet<>();

    public Genero() {
    }

    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Serie> getSeries() {
        return series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Genero genero)) {
            return false;
        }
        return Objects.equals(nombre, genero.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
