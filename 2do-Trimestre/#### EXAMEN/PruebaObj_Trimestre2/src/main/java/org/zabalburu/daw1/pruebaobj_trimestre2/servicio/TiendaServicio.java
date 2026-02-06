/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebaobj_trimestre2.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.pruebaobj_trimestre2.dao.ClienteDAO;
import org.zabalburu.daw1.pruebaobj_trimestre2.dao.PedidoDAO;
import org.zabalburu.daw1.pruebaobj_trimestre2.dao.impl.ClienteDAOImpl;
import org.zabalburu.daw1.pruebaobj_trimestre2.dao.impl.PedidoDAOImpl;
import org.zabalburu.daw1.pruebaobj_trimestre2.modelo.Cliente;
import org.zabalburu.daw1.pruebaobj_trimestre2.modelo.Pedido;

/**
 *
 * @author Iker Navarro Pérez
 */
public class TiendaServicio {

    private static TiendaServicio instancia;
    private ClienteDAO clienteDAO;
    private PedidoDAO pedidoDAO;

    private TiendaServicio() {
        this.clienteDAO = new ClienteDAOImpl();
        this.pedidoDAO = new PedidoDAOImpl();
        inicializarDatos();
    }

    public static TiendaServicio getInstance() {
        if (instancia == null) {
            instancia = new TiendaServicio();
        }
        return instancia;
    }

    private void inicializarDatos() {
        Cliente c1 = new Cliente(null, "Iker", "iker.navarro.perez@zabalburu.org", "634984456", new ArrayList<>());
        Cliente c2 = new Cliente(null, "Iñigo", "iñico.chueca@zabalburu.org", "954584456", new ArrayList<>());
        Cliente c3 = new Cliente(null, "Aaron", "aarondavid.marrero.vargas@zabalburu.org", "674434567", new ArrayList<>());
        Cliente c4 = new Cliente(null, "Marta", "marta.torre@zabalburu.org", "694438023", new ArrayList<>());
        clienteDAO.addCliente(c1);
        clienteDAO.addCliente(c2);
        clienteDAO.addCliente(c3);
        clienteDAO.addCliente(c4);

        Pedido p1 = new Pedido(null, "Sillas Plegables", 5, 14.99, LocalDate.of(2025, 12, 28), c1, false);
        Pedido p2 = new Pedido(null, "Portatil Sobremesa", 10, 249.99, LocalDate.of(2026, 1, 20), c2, true);
        Pedido p3 = new Pedido(null, "Lámpara de Techo", 3, 39.95, LocalDate.of(2026, 1, 4), c3, true);
        Pedido p4 = new Pedido(null, "Móvil Inteligente", 2, 439.49, LocalDate.of(2025, 11, 15), c4, false);
        pedidoDAO.addPedido(p1);
        pedidoDAO.addPedido(p2);
        pedidoDAO.addPedido(p3);
        pedidoDAO.addPedido(p4);
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

    // ================== MÉTODOS PEDIDO ==================
    public void addPedido(Pedido nuevo) {
        pedidoDAO.addPedido(nuevo);
    }
    
    public List<Pedido> getPedidos() {
        return pedidoDAO.getPedidos();
    }

    public void pagarPedido(Integer id) {
        pedidoDAO.pagarPedido(id);
    }

    // ================== MÉTODOS EXTRA ==================
    
    public List<Pedido> getPendientes() {
        return pedidoDAO.getPedidos().stream()
                .filter(p -> !p.isPagado())
                .sorted((p1,p2) -> p1.getFechaPedido().compareTo(p2.getFechaPedido()))
                .toList();
    }
    
    public List<Cliente> getClientesSinPedidos() {
        return clienteDAO.getClientes().stream()
                .filter(c -> c.getPedidos().isEmpty())
                .sorted((c1,c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()))
                .toList();
    }
    
}
