/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_gestionbanco_1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_gestionbanco_1.dao.CuentaBancariaDAO;
import org.zabalburu.daw1.prueba_gestionbanco_1.modelo.CuentaBancaria;

/**
 *
 * @author Iker Navarro Pérez
 */
public class CuentaBancariaDAOImpl implements CuentaBancariaDAO {

    private static List<CuentaBancaria> cuentasBancarias = new ArrayList<>();

    @Override
    public void addCuentaBancaria(CuentaBancaria nueva) {
        Integer idMax = cuentasBancarias.stream()
                .mapToInt(c -> c.getId())
                .max()
                .orElse(0);
        nueva.setId(idMax + 1);
        nueva.getTitular().getCuentasBancarias().add(nueva);
        cuentasBancarias.add(nueva);
    }

    @Override
    public Optional<CuentaBancaria> getCuentaBancaria(Integer id) {
        return cuentasBancarias.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    @Override
    public void modifyCuentaBancaria(CuentaBancaria modificar) {
        int pos = cuentasBancarias.indexOf(modificar);
        if (pos != -1) {
            cuentasBancarias.set(pos, modificar);
        }
    }

    @Override
    public void removeCuentaBancaria(Integer id) {
        CuentaBancaria c = getCuentaBancaria(id).orElse(null);
        if (c != null) {
            c.getTitular().getCuentasBancarias().remove(c);
            cuentasBancarias.remove(c);
        }
    }

}
