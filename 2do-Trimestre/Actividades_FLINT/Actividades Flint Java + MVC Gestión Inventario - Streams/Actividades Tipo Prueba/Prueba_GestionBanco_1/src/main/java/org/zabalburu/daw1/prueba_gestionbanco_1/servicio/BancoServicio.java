/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.prueba_gestionbanco_1.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.prueba_gestionbanco_1.dao.ClienteDAO;
import org.zabalburu.daw1.prueba_gestionbanco_1.dao.CuentaBancariaDAO;
import org.zabalburu.daw1.prueba_gestionbanco_1.dao.impl.ClienteDAOImpl;
import org.zabalburu.daw1.prueba_gestionbanco_1.dao.impl.CuentaBancariaDAOImpl;
import org.zabalburu.daw1.prueba_gestionbanco_1.modelo.Cliente;
import org.zabalburu.daw1.prueba_gestionbanco_1.modelo.CuentaBancaria;
import org.zabalburu.daw1.prueba_gestionbanco_1.util.TipoCuentaBancaria;

/**
 *
 * @author Iker Navarro Pérez
 */
public class BancoServicio {

    private static BancoServicio instancia;
    private ClienteDAO clienteDAO;
    private CuentaBancariaDAO cuentaBancariaDAO;

    private BancoServicio() {
        this.clienteDAO = new ClienteDAOImpl();
        this.cuentaBancariaDAO = new CuentaBancariaDAOImpl();
        inicializarDatos();
    }

    public static BancoServicio getInstance() {
        if (instancia == null) {
            instancia = new BancoServicio();
        }
        return instancia;
    }

    private void inicializarDatos() {
        // CLIENTES
        Cliente c1 = new Cliente(null, "Juan García López", "juan.garcia@email.com", "612345678",
                LocalDate.of(1985, 5, 15), LocalDate.of(2020, 1, 10), new ArrayList<>());
        Cliente c2 = new Cliente(null, "María Rodríguez Pérez", "maria.rodriguez@email.com", "623456789",
                LocalDate.of(1990, 8, 22), LocalDate.of(2019, 3, 15), new ArrayList<>());
        Cliente c3 = new Cliente(null, "Carlos Martínez García", "carlos.martinez@email.com", "634567890",
                LocalDate.of(1988, 3, 10), LocalDate.of(2021, 6, 20), new ArrayList<>());
        Cliente c4 = new Cliente(null, "Ana López Sánchez", "ana.lopez@email.com", "645678901",
                LocalDate.of(1992, 11, 5), LocalDate.of(2018, 9, 5), new ArrayList<>());
        Cliente c5 = new Cliente(null, "Pedro Fernández Ruiz", "pedro.fernandez@email.com", "656789012",
                LocalDate.of(1987, 7, 18), LocalDate.of(2020, 2, 14), new ArrayList<>());
        Cliente c6 = new Cliente(null, "Laura Jiménez Moreno", "laura.jimenez@email.com", "667890123",
                LocalDate.of(1995, 2, 28), LocalDate.of(2022, 4, 8), new ArrayList<>());
        Cliente c7 = new Cliente(null, "Miguel Sánchez Díaz", "miguel.sanchez@email.com", "678901234",
                LocalDate.of(1989, 9, 12), LocalDate.of(2019, 11, 22), new ArrayList<>());
        Cliente c8 = new Cliente(null, "Isabel Gómez Navarro", "isabel.gomez@email.com", "689012345",
                LocalDate.of(1991, 4, 30), LocalDate.of(2021, 1, 18), new ArrayList<>());
        Cliente c9 = new Cliente(null, "Francisco Domínguez Ramos", "francisco.dominguez@email.com", "690123456",
                LocalDate.of(1986, 12, 8), LocalDate.of(2017, 7, 3), new ArrayList<>());
        Cliente c10 = new Cliente(null, "Rosa Castillo Vega", "rosa.castillo@email.com", "701234567",
                LocalDate.of(1993, 6, 19), LocalDate.of(2020, 5, 25), new ArrayList<>());

        // Agregar clientes al DAO
        clienteDAO.addCliente(c1);
        clienteDAO.addCliente(c2);
        clienteDAO.addCliente(c3);
        clienteDAO.addCliente(c4);
        clienteDAO.addCliente(c5);
        clienteDAO.addCliente(c6);
        clienteDAO.addCliente(c7);
        clienteDAO.addCliente(c8);
        clienteDAO.addCliente(c9);
        clienteDAO.addCliente(c10);

        // CUENTAS BANCARIAS (30 en total, distribuidas entre los 10 clientes)
        // C1: 3 cuentas
        // CAMBIO REALIZADO: Primero la Fecha, luego el Cliente (c1)
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051332", 5000.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2020, 1, 15), c1));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051333", 12500.50, TipoCuentaBancaria.AHORROS, LocalDate.of(2020, 6, 20), c1));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051334", 25000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2021, 3, 10), c1));

        // C2: 4 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051335", 3200.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2019, 3, 20), c2));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051336", 8900.75, TipoCuentaBancaria.AHORROS, LocalDate.of(2019, 9, 15), c2));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051337", 15000.00, TipoCuentaBancaria.JOVEN, LocalDate.of(2020, 1, 5), c2));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051338", 50000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2021, 6, 12), c2));

        // C3: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051339", 4500.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2021, 6, 25), c3));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051340", 7200.25, TipoCuentaBancaria.AHORROS, LocalDate.of(2021, 8, 10), c3));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051341", 18000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2022, 2, 14), c3));

        // C4: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051342", 2800.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2018, 9, 10), c4));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051343", 6500.50, TipoCuentaBancaria.JOVEN, LocalDate.of(2019, 4, 22), c4));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051344", 35000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2020, 11, 8), c4));

        // C5: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051345", 5100.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2020, 2, 20), c5));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051346", 9800.75, TipoCuentaBancaria.AHORROS, LocalDate.of(2020, 7, 15), c5));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051347", 22000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2021, 5, 3), c5));

        // C6: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051348", 1500.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2022, 4, 12), c6));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051349", 4200.25, TipoCuentaBancaria.JOVEN, LocalDate.of(2022, 5, 8), c6));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051350", 12000.00, TipoCuentaBancaria.AHORROS, LocalDate.of(2022, 6, 1), c6));

        // C7: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051351", 6000.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2019, 11, 28), c7));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051352", 11500.50, TipoCuentaBancaria.AHORROS, LocalDate.of(2020, 3, 14), c7));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051353", 30000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2021, 9, 20), c7));

        // C8: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051354", 3600.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2021, 1, 22), c8));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051355", 7800.75, TipoCuentaBancaria.JOVEN, LocalDate.of(2021, 2, 10), c8));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051356", 20000.00, TipoCuentaBancaria.AHORROS, LocalDate.of(2021, 8, 5), c8));

        // C9: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051357", 7200.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2017, 7, 10), c9));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051358", 14000.50, TipoCuentaBancaria.AHORROS, LocalDate.of(2018, 2, 18), c9));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051359", 40000.00, TipoCuentaBancaria.INVERSIONES, LocalDate.of(2019, 10, 25), c9));

        // C10: 3 cuentas
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051360", 2900.00, TipoCuentaBancaria.CORRIENTE, LocalDate.of(2020, 5, 30), c10));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051361", 5600.25, TipoCuentaBancaria.JOVEN, LocalDate.of(2020, 8, 12), c10));
        cuentaBancariaDAO.addCuentaBancaria(new CuentaBancaria(null, "ES9121000418450200051362", 16000.00, TipoCuentaBancaria.AHORROS, LocalDate.of(2021, 4, 7), c10));
    }

    // ================== MÉTODOS CLIENTE ==================
    public void addCliente(Cliente nuevo) {
        clienteDAO.addCliente(nuevo);
    }

    public Optional<Cliente> getCliente(Integer id) {
        return clienteDAO.getCliente(id);
    }

    public List<Cliente> getClientes() {
        return clienteDAO.getClientes();
    }

    public void modifyCliente(Cliente modificar) {
        clienteDAO.modifyCliente(modificar);
    }

    public void removeCliente(Integer id) throws Exception {
        clienteDAO.removeCliente(id);
    }

    // ================== MÉTODOS CUENTA BANCARIA ==================
    public void addCuentaBancaria(CuentaBancaria nueva) {
        cuentaBancariaDAO.addCuentaBancaria(nueva);
    }

    public Optional<CuentaBancaria> getCuentaBancaria(Integer id) {
        return cuentaBancariaDAO.getCuentaBancaria(id);

    }

    public List<CuentaBancaria> getCuentasBancarias() {
        return cuentaBancariaDAO.getCuentasBancarias();

    }

    public void modifyCuentaBancaria(CuentaBancaria modificar) {
        cuentaBancariaDAO.modifyCuentaBancaria(modificar);
    }

    public void removeCuentaBancaria(Integer id) {
        cuentaBancariaDAO.removeCuentaBancaria(id);
    }

    // ================== MÉTODOS EXTRA ==================
    public double getSaldoTotalCliente(Integer idCliente) {
        return getCliente(idCliente) //Devuelve un optional
                .map(c -> c.getCuentasBancarias().stream()
                    .mapToDouble(CuentaBancaria::getSaldo)
                    .sum())
                .orElse(0.0);
    }

    public List<CuentaBancaria> getCuentasBancariasTipo(TipoCuentaBancaria tipo) {
        return cuentaBancariaDAO.getCuentasBancarias().stream()
                .filter(cuenta -> cuenta.getTipo() == tipo)
                .toList();
    }

    public List<CuentaBancaria> getCuentasBancariasOrdenadasFechaApertura() {
        return cuentaBancariaDAO.getCuentasBancarias().stream()
                .sorted((c1,c2) -> c1.getFechaApertura().compareTo(c2.getFechaApertura()))
                .toList();
    }
}
