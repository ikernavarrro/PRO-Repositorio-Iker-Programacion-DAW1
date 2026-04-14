/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "seriesTMDB")
@SequenceGenerator(name = "seq_serie", sequenceName = "SEQ_SERIE", allocationSize = 1)
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_serie")
    private Long id;

    @Column(name = "tmdb_id", nullable = false, unique = true)
    private Integer tmdbId;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(length = 2000)
    private String sinopsis;

    @Column(name = "fecha_primer_aire")
    private LocalDate fechaPrimerAire;

    @Column(name = "poster_url", length = 500)
    private String posterUrl;

    @ManyToMany
    @JoinTable(
            name = "serie_genero",
            joinColumns = @JoinColumn(name = "serie_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private Set<Genero> generos = new HashSet<>();

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Valoracion> valoraciones = new ArrayList<>();

    public Serie() {
    }

    public Serie(Integer tmdbId, String titulo) {
        this.tmdbId = tmdbId;
        this.titulo = titulo;
    }

    public void addGenero(Genero genero) {
        generos.add(genero);
        genero.getSeries().add(this);
    }

    public void removeGenero(Genero genero) {
        generos.remove(genero);
        genero.getSeries().remove(this);
    }

    public void addValoracion(Valoracion valoracion) {
        valoraciones.add(valoracion);
        valoracion.setSerie(this);
    }

    public void removeValoracion(Valoracion valoracion) {
        valoraciones.remove(valoracion);
        valoracion.setSerie(null);
    }

    public Double getMediaValoraciones() {
        if (valoraciones.isEmpty()) {
            return 0.0;
        }
        return valoraciones.stream()
                .mapToInt(Valoracion::getEstrellas)
                .average()
                .orElse(0.0);
    }

    public Long getId() {
        return id;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public LocalDate getFechaPrimerAire() {
        return fechaPrimerAire;
    }

    public void setFechaPrimerAire(LocalDate fechaPrimerAire) {
        this.fechaPrimerAire = fechaPrimerAire;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Serie serie)) {
            return false;
        }
        return Objects.equals(id, serie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
