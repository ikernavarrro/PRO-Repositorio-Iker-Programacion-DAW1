/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author ichueca
 */
@Entity
@Table(name = "usuariosJPA")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(
    {
        @NamedQuery(name = "usuario.buscarTodos", query = "Select u From Usuario u")
    }
)
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Integer id;
    
    private String nombre;
    
    //@Transient
    //private transient tipo campo;
    
    @Column(columnDefinition = "VARCHAR2(100) NOT NULL")
    private String apellidos;
    
    private String email;
    
    private LocalDate fechaAlta; // fecha_alta
    
    private Double sueldo;
    
    private String hash;
    
    public void addTarea(Tarea t){
        if (t == null){
            return;
        }
        if (!tareas.contains(t)){
            tareas.add(t);
        }
        t.setUsuario(this);
    }
    
    public void removeTarea(Tarea t){
        if (t == null){
            return;
        }
        tareas.remove(t);
        if (t.getUsuario().equals(this)){
            t.setUsuario(null);
        }
    }
    
    @OneToMany(mappedBy = "usuario") // ENTIDAD Tarea
    @ToString.Exclude
    private List<Tarea> tareas;
    
}
