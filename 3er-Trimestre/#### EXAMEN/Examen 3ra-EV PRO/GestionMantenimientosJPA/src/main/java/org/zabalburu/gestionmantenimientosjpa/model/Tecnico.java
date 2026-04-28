/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.gestionmantenimientosjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Iker Navarro Pérez
 */
@Entity
@Table(name = "TECNICOS")
@SequenceGenerator(name = "seq_tecnico", sequenceName = "SEQ_TECNICO", allocationSize = 1)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tecnico")
    @Column(name = "idTecnico")
    @EqualsAndHashCode.Include
    private Integer id;
    
    private String nombre;
    private String especialidad;

    @OneToMany(mappedBy = "tecnico")
    private List<Maquina> maquinas;

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ") ";
    }
    
    
}
