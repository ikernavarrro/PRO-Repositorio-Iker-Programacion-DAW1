/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    private JLabel lblImagen;

    // Constructor
    public BibliotecaVista() {
        this.servicio = new BibliotecaServicio();
        this.estado = Estado.CONSULTA;
        this.setTitle("Gestión de Bibliotecas");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);

        this.crearComponentes();
        this.configurarLayout();
        this.cargarBibliotecas();
        this.establecerEstado(Estado.CONSULTA);
        this.configurarListeners();

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
        
        // JLabel
        lblImagen = new JLabel(new ImageIcon("iconos/biblioteca.png"));
    }

    private void configurarLayout() {
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // NORTE: Imagen
        JPanel pnlNorte = new JPanel();
        pnlNorte.add(lblImagen);
        
        // CENTRO: Campos de texto y Lista de bibliotecas
        JPanel pnlCentro = new JPanel(new BorderLayout());
        
        JPanel panelCampos = new JPanel(new GridLayout(6, 1));
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Ubicación:"));
        panelCampos.add(txtUbicacion);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);
        pnlCentro.add(panelCampos,BorderLayout.NORTH);
        
        JScrollPane scrollLista = new JScrollPane(listaBibliotecas);
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.add(new JLabel("Bibliotecas:"), BorderLayout.NORTH);
        JPanel scrollListaPanel = new JPanel(new BorderLayout());
        scrollListaPanel.add(scrollLista);
        scrollListaPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        panelLista.add(scrollListaPanel, BorderLayout.CENTER);
        pnlCentro.add(panelLista,BorderLayout.SOUTH);
        
        pnlCentro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // SUR: Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnNuevo.setIcon(new ImageIcon("iconos/nuevo.png"));
        panelBotones.add(btnNuevo);
        btnEditar.setIcon(new ImageIcon("iconos/editar.png"));
        panelBotones.add(btnEditar);
        btnEliminar.setIcon(new ImageIcon("iconos/eliminar.png"));
        panelBotones.add(btnEliminar);
        btnGuardar.setIcon(new ImageIcon("iconos/guardar.png"));
        panelBotones.add(btnGuardar);
        btnCancelar.setIcon(new ImageIcon("iconos/cancelar.png"));
        panelBotones.add(btnCancelar);
        btnVerLibros.setIcon(new ImageIcon("iconos/ver-libros.png"));
        panelBotones.add(btnVerLibros);

        // Añadir paneles al principal
        panelPrincipal.add(pnlNorte, BorderLayout.NORTH);
        panelPrincipal.add(pnlCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Añadir panel principal al JFrame
        this.add(panelPrincipal);
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
        // Listener para la lista: cuando selecciones una biblioteca, mostrar sus datos
        listaBibliotecas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Biblioteca seleccionada = listaBibliotecas.getSelectedValue();
                if (seleccionada != null) {
                    bibliotecaActual = seleccionada;
                    txtNombre.setText(seleccionada.getNombre());
                    txtUbicacion.setText(seleccionada.getUbicacion());
                    txtTelefono.setText(seleccionada.getTelefono());
                }
            }
        });

        // Botón Nuevo
        btnNuevo.addActionListener(e -> {
            txtNombre.setText("");
            txtUbicacion.setText("");
            txtTelefono.setText("");
            bibliotecaActual = null;
            establecerEstado(Estado.ALTA);
        });

        // Botón Editar
        btnEditar.addActionListener(e -> {
            if (bibliotecaActual != null) {
                establecerEstado(Estado.MODIFICACION);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una biblioteca");
            }
        });

        // Botón Guardar
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (nombre.isEmpty() || ubicacion.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos");
                return;
            }

            if (estado == Estado.ALTA) {
                servicio.addBiblioteca(nombre, ubicacion, telefono);
            } else if (estado == Estado.MODIFICACION) {
                bibliotecaActual.setNombre(nombre);
                bibliotecaActual.setUbicacion(ubicacion);
                bibliotecaActual.setTelefono(telefono);
                servicio.modifyBiblioteca(bibliotecaActual);
            }

            cargarBibliotecas();
            establecerEstado(Estado.CONSULTA);
            JOptionPane.showMessageDialog(this, "Operación completada");
        });

        // Botón Cancelar
        btnCancelar.addActionListener(e -> {
            establecerEstado(Estado.CONSULTA);
            txtNombre.setText("");
            txtUbicacion.setText("");
            txtTelefono.setText("");
        });

        // Botón Eliminar
        btnEliminar.addActionListener(e -> {
            if (bibliotecaActual != null) {
                int respuesta = JOptionPane.showConfirmDialog(this,
                        "¿Eliminar biblioteca y desasignar sus libros?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    servicio.deleteBiblioteca(bibliotecaActual.getId());
                    cargarBibliotecas();
                    establecerEstado(Estado.CONSULTA);
                    txtNombre.setText("");
                    txtUbicacion.setText("");
                    txtTelefono.setText("");
                    JOptionPane.showMessageDialog(this, "Biblioteca eliminada");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una biblioteca");
            }
        });

        // Botón Ver Libros
        btnVerLibros.addActionListener(e -> {
            if (bibliotecaActual != null) {
                new LibroVista(servicio, bibliotecaActual);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una biblioteca");
            }
        });
    }
}
