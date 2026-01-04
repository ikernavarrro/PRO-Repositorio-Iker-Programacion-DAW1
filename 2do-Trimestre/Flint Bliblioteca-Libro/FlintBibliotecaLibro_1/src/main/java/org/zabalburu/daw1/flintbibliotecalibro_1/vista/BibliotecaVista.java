/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Biblioteca;
import org.zabalburu.daw1.flintbibliotecalibro_1.servicio.BibliotecaServicio;
import org.zabalburu.daw1.flintbibliotecalibro_1.util.Estado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class BibliotecaVista extends JFrame {

    private BibliotecaServicio servicio;
    private Estado estado;
    private Biblioteca bibliotecaActual;

    // Componentes 
    private JList<Biblioteca> listaBibliotecas;
    private JTextField txtNombre, txtUbicacion, txtTelefono;
    private JButton btnNuevo, btnEditar, btnEliminar, btnGuardar, btnCancelar, btnVerLibros;

    // Constructor
    public BibliotecaVista() {
        this.servicio = new BibliotecaServicio();
        this.estado = Estado.CONSULTA;
        setTitle("Gestión de Bibliotecas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        crearComponentes();
        configurarLayout();
        cargarBibliotecas();
        establecerEstado(Estado.CONSULTA);
        configurarListeners();

        this.setVisible(true);
    }

    // Métodos 
    private void crearComponentes() {
        // JList
        listaBibliotecas = new JList<>();

        // JTextField
        txtNombre = new JTextField(20);
        txtUbicacion = new JTextField(20);
        txtTelefono = new JTextField(20);

        // JButton
        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        btnVerLibros = new JButton("Ver Libros");
    }

    private void configurarLayout() {

    }

    private void cargarBibliotecas() {
        DefaultListModel<Biblioteca> modelo = new DefaultListModel<>();
        for (Biblioteca b : servicio.getBibliotecas()) {
            modelo.addElement(b);
        }
        listaBibliotecas.setModel(modelo);
    }

    private void establecerEstado(Estado nuevoEstado) {
        estado = nuevoEstado;

        switch (estado) {
            case CONSULTA:
                txtNombre.setEnabled(false);
                txtUbicacion.setEnabled(false);
                txtTelefono.setEnabled(false);
                btnNuevo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnGuardar.setEnabled(false);
                btnCancelar.setEnabled(false);
                btnVerLibros.setEnabled(true);
                break;
            case ALTA:
                txtNombre.setEnabled(true);
                txtUbicacion.setEnabled(true);
                txtTelefono.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(true);
                btnCancelar.setEnabled(true);
                btnVerLibros.setEnabled(false);
                break;
            case MODIFICACION:
                txtNombre.setEnabled(true);
                txtUbicacion.setEnabled(true);
                txtTelefono.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(true);
                btnCancelar.setEnabled(true);
                btnVerLibros.setEnabled(false);
                break;
        }
    }

    private void habilitarControles(boolean habilitar) {

    }

    // Métodos de eventos (listeners)
    private void configurarListeners() {

    }
}
