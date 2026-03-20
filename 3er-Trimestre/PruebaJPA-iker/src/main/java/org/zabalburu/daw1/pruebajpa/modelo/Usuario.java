/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajpa.modelo;

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
import java.time.LocalDate;
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
@Table(name = "usuariosJPA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/*@NamedQueries({
    @NamedQuery(name = "usuario.buscarTodos" , query = "SELECT u FROM Usuario u")
})*/
public class Usuario {

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
    
    private String hash;
    
    private Double sueldo;
    
    private LocalDate fechaAlta; //fecha_alta
    
    @OneToMany(mappedBy = "usuario" ,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Tarea> tareas;
    
}
