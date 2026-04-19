/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.gestion_hoteles.model.Hotel;
import org.zabalburu.daw1.gestion_hoteles.util.CategoriaHotel;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface HotelDAO {

    public void addHotel(EntityManager em, Hotel nuevo);

    public Hotel getHotel(EntityManager em, Integer idHotel);

    public Hotel getHotelConHabitaciones(EntityManager em, Integer idHotel);

    public List<Hotel> getHoteles(EntityManager em);
    
    public List<Hotel> getHotelesCategoria(EntityManager em, CategoriaHotel categoria);

    public void modifyHotel(EntityManager em, Hotel modificar);

    public void removeHotel(EntityManager em, Integer idHotel);
    
}
