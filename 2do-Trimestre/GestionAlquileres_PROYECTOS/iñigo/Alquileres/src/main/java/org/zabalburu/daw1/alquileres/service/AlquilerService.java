/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.service;

import org.zabalburu.daw1.alquileres.dao.AlquilerDao;
import org.zabalburu.daw1.alquileres.dao.AlquilerList;
import org.zabalburu.daw1.alquileres.dao.CocheDao;
import org.zabalburu.daw1.alquileres.dao.CocheList;
import org.zabalburu.daw1.alquileres.modelo.Alquiler;
import org.zabalburu.daw1.alquileres.modelo.Coche;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.alquileres.dao.ClienteDAO;
import org.zabalburu.daw1.alquileres.modelo.Cliente;

/**
 *
 * @author ichueca
 */
public class AlquilerService {

    private static AlquilerService service = new AlquilerService();
    
    public static AlquilerService getService(){
        return service;
    }
    
    private static AlquilerDao alquilerDao;
    private static CocheDao cocheDao;
    private static ClienteDAO clienteDao;

    private AlquilerService(){
        cocheDao = new CocheList();
        alquilerDao = new AlquilerList();
        clienteDao = new ClienteDAO();
        cargarDatos();
    }
    
    public static void cargarDatos() {

        LocalDate hoy = LocalDate.of(2026, 1, 26);
        clienteDao.getClientes().add(new Cliente(1,"XYZ, S.A"));
        clienteDao.getClientes().add(new Cliente(2,"ABC, S.A"));
        clienteDao.getClientes().add(new Cliente(3,"EHG, S.A"));
        clienteDao.getClientes().add(new Cliente(4,"QRT, S.A"));
        // ===== COCHES (3 MARCAS) =====
        List<Coche> coches = List.of(
            new Coche(1, "Toyota", "Corolla", "1234-ABC", "Blanco", 45.0, new ArrayList<>()),
            new Coche(2, "Toyota", "Yaris", "2345-BCD", "Gris", 38.0, new ArrayList<>()),
            new Coche(3, "Toyota", "RAV4", "3456-CDE", "Negro", 65.0, new ArrayList<>()),

            new Coche(4, "Volkswagen", "Golf", "4567-DEF", "Azul", 50.0, new ArrayList<>()),
            new Coche(5, "Volkswagen", "Polo", "5678-EFG", "Rojo", 42.0, new ArrayList<>()),
            new Coche(6, "Volkswagen", "Tiguan", "6789-FGH", "Blanco", 68.0, new ArrayList<>()),

            new Coche(7, "BMW", "Serie 1", "7890-GHI", "Negro", 72.0, new ArrayList<>()),
            new Coche(8, "BMW", "Serie 3", "8901-HIJ", "Gris", 85.0, new ArrayList<>()),
            new Coche(9, "BMW", "X1", "9012-IJK", "Blanco", 90.0, new ArrayList<>()),
            new Coche(10, "BMW", "X3", "0123-JKL", "Azul", 110.0, new ArrayList<>())
        );

        coches.forEach(cocheDao::nuevoCoche);

        int idAlquiler = 1;

        // ===== ALQUILERES =====
        for (Coche coche : coches) {

            // 5 alquileres fijos por coche (control total)
            for (int i = 0; i < 5; i++) {

                LocalDate fechaAlquiler;
                int dias;
                LocalDate fechaDevolucion = null;

                if (i == 0) {
                    // DEVUELTO NORMAL
                    fechaAlquiler = hoy.minusDays(40);
                    dias = 5;
                    fechaDevolucion = fechaAlquiler.plusDays(dias);
                } 
                else if (i == 1) {
                    // DEVUELTO CON RETRASO (pero ya devuelto)
                    fechaAlquiler = hoy.minusDays(25);
                    dias = 5;
                    fechaDevolucion = fechaAlquiler.plusDays(dias + 2);
                } 
                else if (i == 2) {
                    // SIN DEVOLVER - AÚN EN PLAZO
                    fechaAlquiler = hoy.minusDays(2);
                    dias = 7;
                } 
                else if (i == 3) {
                    // SIN DEVOLVER - RECLAMABLE
                    fechaAlquiler = hoy.minusDays(15);
                    dias = 5;
                } 
                else {
                    // DEVUELTO ANTIGUO
                    fechaAlquiler = hoy.minusDays(70);
                    dias = 3;
                    fechaDevolucion = fechaAlquiler.plusDays(dias);
                }

                Alquiler alquiler = new Alquiler(
                        idAlquiler++,
                        fechaAlquiler,
                        fechaDevolucion,
                        coche,
                        dias,
                        coche.getCosteDia(),
                        clienteDao.getCliente(1 + (int) (Math.random() * 4))
                );

                //coche.getAlquileres().add(alquiler);
                alquilerDao.nuevoAlquiler(alquiler);
            }
        }
    }
    
    public Coche nuevoCoche(Coche nuevo){
        return cocheDao.nuevoCoche(nuevo);
    }
    
    public void modificarCoche(Coche modificar){
        cocheDao.modificarCoche(modificar);
    }
    
    public void eliminarCoche(Integer id){
        cocheDao.eliminarCoche(id);
    }
    
    public Coche getCoche(Integer id){
        return cocheDao.getCoche(id);
    }
    
    public List<Coche> getCoches(){
        return cocheDao.getCoches();
    }
    
    public List<Coche> getCochesModelo(String modelo){
        return cocheDao.getCochesModelo(modelo);
    }
    
    public Alquiler nuevoAlquiler(Alquiler nuevo){
        return alquilerDao.nuevoAlquiler(nuevo);
    }
    
    public void modificarAlquiler(Alquiler modificar){
        alquilerDao.modificarAlquiler(modificar);
    }
    
    public void eliminarAlquiler(Integer id){
        alquilerDao.eliminarAlquiler(id);
    }
    
    public Alquiler getAlquiler(Integer id){
        return alquilerDao.getAlquiler(id);
    }
    
    public List<Alquiler> getAlquileres(){
        return alquilerDao.getAlquileres();
    }
    
    public List<Cliente> getClientes(){
        return clienteDao.getClientes();
    }
    
    public Cliente getCliente(Integer id){
        return clienteDao.getCliente(id);
    }
    
    public static void main(String[] args) {
        AlquilerService service = AlquilerService.getService();
        for(Coche c : service.getCoches()){
            System.out.println(c);
            for (Alquiler a : c.getAlquileres()){
                System.out.println("\t" + a);
            }
            System.out.println("============");
        }
    }
}
