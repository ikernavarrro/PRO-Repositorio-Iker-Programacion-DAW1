/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.zabalburu.daw1.gestion_hoteles.util.EstadoHabitacion;
import org.zabalburu.daw1.gestion_hoteles.util.TipoHabitacion;

/**
 *
 * @author Iker Navarro Pérez
 */
@Entity
@Table(name = "F_Habitaciones")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_F_Habitaciones")
    @Column(name = "id_Habitacion")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_Hotel")
    private Hotel hotel;

    private String descripcion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_Habitacion")
    private TipoHabitacion tipoHabitacion;
    
    private Integer piso;
    
    @Column(name = "precio_Noche")
    private Double precioNoche;
    
    private Integer capacidad;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_Habitacion")
    private EstadoHabitacion estado;

}
