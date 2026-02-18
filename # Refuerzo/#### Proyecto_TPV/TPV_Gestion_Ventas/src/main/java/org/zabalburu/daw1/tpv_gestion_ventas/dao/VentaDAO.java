/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.dao;

import java.util.List;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Venta;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface VentaDAO {
    public Venta addVenta(Venta v);
    public Venta getVenta(Integer id);
    public List<Venta> getVentas();
    public Venta modifyVenta(Venta v);
    public Venta deleteVenta(Integer id);
}
