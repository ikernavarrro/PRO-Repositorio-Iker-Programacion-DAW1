/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zabalburu.daw1.gestion_hoteles.util.CategoriaHotel;

/**
 *
 * @author Iker Navarro Pérez
 */
@Entity
@Table(name = "F_Hoteles")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_F_Hoteles")
    @Column(name = "id_Hotel")
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String direccion;
    private String ciudad;

    @Enumerated(EnumType.STRING)
    private CategoriaHotel categoria;

    @CreationTimestamp
    @Column(name = "fecha_Creacion")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            mappedBy = "hotel")
    @ToString.Exclude
    private List<Habitacion> habitaciones;
}
