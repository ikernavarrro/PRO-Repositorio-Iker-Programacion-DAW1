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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "MAQUINAS")
@SequenceGenerator(name = "seq_maquina", sequenceName = "SEQ_MAQUINA", allocationSize = 1)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Maquina implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_maquina")
    @Column(name = "idMaquina")
    @EqualsAndHashCode.Include
    private Integer id;
    
    private String nombre;
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "idTecnico")
    private Tecnico tecnico;

    @OneToMany(mappedBy = "maquina") 
    @ToString.Exclude
    private List<Revision> revisiones;

    @Override
    public String toString() {
        return nombre + " - " + ubicacion;
    }
}
