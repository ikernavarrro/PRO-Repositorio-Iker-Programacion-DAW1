/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.tpv_gestion_ventas.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.zabalburu.daw1.tpv_gestion_ventas.dao.VentaDAO;
import org.zabalburu.daw1.tpv_gestion_ventas.modelo.Venta;

/**
 *
 * @author Iker Navarro Pérez
 */
public class VentaDAOImpl implements VentaDAO {

    private List<Venta> ventas;

    public VentaDAOImpl() {
        this.ventas = new ArrayList<>();
    }

    @Override
    public Venta addVenta(Venta v) {
        if (v != null) {
            Integer id = 1;
            if (!ventas.isEmpty()) {
                id = ventas.get(ventas.size() - 1).getId() + 1;
            }
            v.setId(id);
            ventas.add(v);
            return v;
        } else {
            return null;
        }
    }

    @Override
    public Venta getVenta(Integer id) {
        int i;
        for (i = 0; i < ventas.size() && ventas.get(i).getId() != id; i++);
        if (i < ventas.size()) {
            return ventas.get(i);
        } else {
            return null;
        }
    }

    @Override
    public List<Venta> getVentas() {
        return ventas;
    }

    @Override
    public Venta modifyVenta(Venta v) {
        int pos = ventas.indexOf(v);
        if (pos != -1) {
            ventas.set(pos, v);
            return v;
        } else {
            return null;
        }
    }

    @Override
    public Venta deleteVenta(Integer id) {
        Venta v = new Venta();
        v.setId(id);
        int pos = ventas.indexOf(v);
        if (pos != -1) {
            ventas.remove(v);
            return v;  // DEVUELVE LA VENTA VACIA, SOLO CON ID PARA MOSTRAR MENSAJE INFORMATIVO
        } else {
            return null;
        }
    }

}
