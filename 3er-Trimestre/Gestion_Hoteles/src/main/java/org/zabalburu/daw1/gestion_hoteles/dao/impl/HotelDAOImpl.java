/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestion_hoteles.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.zabalburu.daw1.gestion_hoteles.dao.HotelDAO;
import org.zabalburu.daw1.gestion_hoteles.model.Hotel;
import org.zabalburu.daw1.gestion_hoteles.util.CategoriaHotel;

/**
 *
 * @author Iker Navarro Pérez
 */
public class HotelDAOImpl implements HotelDAO {

    @Override
    public void addHotel(EntityManager em, Hotel nuevo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Hotel getHotel(EntityManager em, Integer idHotel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Hotel getHotelConHabitaciones(EntityManager em, Integer idHotel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Hotel> getHoteles(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Hotel> getHotelesCategoria(EntityManager em, CategoriaHotel categoria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifyHotel(EntityManager em, Hotel modificar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeHotel(EntityManager em, Integer idHotel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
