/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.model;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DAW1
 */
public class ValoracionTest {

    public ValoracionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    
    /**
     * Test of setEstrellas method, of class Valoracion.
     */
    @org.junit.jupiter.api.Test
    public void testSetEstrellas() {
        System.out.println("setEstrellas");
        int estrellas = 2;
        Valoracion instance = new Valoracion();
        instance.setEstrellas(estrellas);
        assertEquals(2, instance.getEstrellas());
    }

   
}
