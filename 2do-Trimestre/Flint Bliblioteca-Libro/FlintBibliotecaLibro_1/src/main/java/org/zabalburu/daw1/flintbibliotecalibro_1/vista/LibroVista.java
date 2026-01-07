/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.flintbibliotecalibro_1.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Biblioteca;
import org.zabalburu.daw1.flintbibliotecalibro_1.modelo.Libro;
import org.zabalburu.daw1.flintbibliotecalibro_1.servicio.BibliotecaServicio;
import org.zabalburu.daw1.flintbibliotecalibro_1.util.Categoria;
import org.zabalburu.daw1.flintbibliotecalibro_1.util.Estado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class LibroVista extends JDialog {

    private BibliotecaServicio servicio;
    private Biblioteca biblioteca;
    private Estado estado;
    private Libro libroActual;

    // Componentes
    private JList<Libro> listaLibros;
    private JComboBox<Categoria> cmbCategoria;
    private JTextField txtTitulo, txtDescripcion, txtAutor, txtEditorial, txtISBN;
    private JLabel lblFecha;
    private JButton btnAnadir, btnEditar, btnEliminar, btnGuardar, btnCancelar;

    public LibroVista(BibliotecaServicio servicio, Biblioteca biblioteca) {
        this.servicio = servicio;
        this.biblioteca = biblioteca;
        this.estado = Estado.CONSULTA;

        setTitle("Libros de: " + biblioteca.getNombre());
        setModal(true);
        setSize(700, 700);
        setLocationRelativeTo(null);

        crearComponentes();
        configurarLayout();
        cargarLibros();
        establecerEstado(Estado.CONSULTA);
        configurarListeners();

        setVisible(true);
    }

    private void crearComponentes() {
        listaLibros = new JList<>();
        cmbCategoria = new JComboBox<>(Categoria.values());
        txtTitulo = new JTextField(20);
        txtDescripcion = new JTextField(20);
        txtAutor = new JTextField(20);
        txtEditorial = new JTextField(20);
        txtISBN = new JTextField(20);
        lblFecha = new JLabel("Fecha: ");
        btnAnadir = new JButton("Añadir");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
    }

    private void configurarLayout() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollLista = new JScrollPane(listaLibros);
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.add(new JLabel("Libros:"), BorderLayout.NORTH);
        panelLista.add(scrollLista, BorderLayout.CENTER);

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
        panelCampos.add(new JLabel("Categoría:"));
        panelCampos.add(cmbCategoria);
        panelCampos.add(new JLabel("Título:"));
        panelCampos.add(txtTitulo);
        panelCampos.add(new JLabel("Descripción:"));
        panelCampos.add(txtDescripcion);
        panelCampos.add(new JLabel("Autor:"));
        panelCampos.add(txtAutor);
        panelCampos.add(new JLabel("Editorial:"));
        panelCampos.add(txtEditorial);
        panelCampos.add(new JLabel("ISBN:"));
        panelCampos.add(txtISBN);

        JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFecha.add(lblFecha);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(panelCampos, BorderLayout.CENTER);
        panelCentro.add(panelFecha, BorderLayout.SOUTH);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnAnadir.setIcon(new ImageIcon("iconos/nuevo.png"));
        panelBotones.add(btnAnadir);
        btnEditar.setIcon(new ImageIcon("iconos/editar.png"));
        panelBotones.add(btnEditar);
        btnEliminar.setIcon(new ImageIcon("iconos/eliminar.png"));
        panelBotones.add(btnEliminar);
        btnGuardar.setIcon(new ImageIcon("iconos/guardar.png"));
        panelBotones.add(btnGuardar);
        btnCancelar.setIcon(new ImageIcon("iconos/cancelar.png"));
        panelBotones.add(btnCancelar);
        

        panelPrincipal.add(panelCentro, BorderLayout.NORTH);
        panelPrincipal.add(panelLista, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        this.add(panelPrincipal);
    }

    private void cargarLibros() {
        DefaultListModel<Libro> modelo = new DefaultListModel<>();
        for (Libro l : servicio.getLibrosBiblioteca(biblioteca.getId())) {
            modelo.addElement(l);
        }
        listaLibros.setModel(modelo);
    }

    private void establecerEstado(Estado nuevoEstado) {
        estado = nuevoEstado;

        switch (estado) {
            case CONSULTA:
                cmbCategoria.setEnabled(false);
                txtTitulo.setEnabled(false);
                txtDescripcion.setEnabled(false);
                txtAutor.setEnabled(false);
                txtEditorial.setEnabled(false);
                txtISBN.setEnabled(false);
                btnAnadir.setEnabled(true);
                btnEditar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnGuardar.setEnabled(false);
                btnCancelar.setEnabled(false);
                break;
            case ALTA:
                cmbCategoria.setEnabled(true);
                txtTitulo.setEnabled(true);
                txtDescripcion.setEnabled(true);
                txtAutor.setEnabled(true);
                txtEditorial.setEnabled(true);
                txtISBN.setEnabled(true);
                btnAnadir.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(true);
                btnCancelar.setEnabled(true);
                break;
            case MODIFICACION:
                cmbCategoria.setEnabled(true);
                txtTitulo.setEnabled(true);
                txtDescripcion.setEnabled(true);
                txtAutor.setEnabled(true);
                txtEditorial.setEnabled(true);
                txtISBN.setEnabled(true);
                btnAnadir.setEnabled(false);
                btnEditar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(true);
                btnCancelar.setEnabled(true);
                break;
        }
    }

    private void configurarListeners() {
        listaLibros.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Libro seleccionado = listaLibros.getSelectedValue();
                if (seleccionado != null) {
                    libroActual = seleccionado;
                    cmbCategoria.setSelectedItem(seleccionado.getCategoria());
                    txtTitulo.setText(seleccionado.getTitulo());
                    txtDescripcion.setText(seleccionado.getDescripcion());
                    txtAutor.setText(seleccionado.getAutor());
                    txtEditorial.setText(seleccionado.getEditorial());
                    txtISBN.setText(seleccionado.getCodigoISBN());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    lblFecha.setText("Fecha: " + sdf.format(seleccionado.getFechaPublicacion()));
                }
            }
        });

        btnAnadir.addActionListener(e -> {
            cmbCategoria.setSelectedIndex(0);
            txtTitulo.setText("");
            txtDescripcion.setText("");
            txtAutor.setText("");
            txtEditorial.setText("");
            txtISBN.setText("");
            lblFecha.setText("Fecha: ");
            libroActual = null;
            establecerEstado(Estado.ALTA);
        });

        btnEditar.addActionListener(e -> {
            if (libroActual != null) {
                establecerEstado(Estado.MODIFICACION);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un libro");
            }
        });

        btnGuardar.addActionListener(e -> {
            Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
            String titulo = txtTitulo.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String autor = txtAutor.getText().trim();
            String editorial = txtEditorial.getText().trim();
            String isbn = txtISBN.getText().trim();

            if (titulo.isEmpty() || descripcion.isEmpty() || autor.isEmpty()
                    || editorial.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos");
                return;
            }

            if (estado == Estado.ALTA) {
                servicio.addLibro(categoria, titulo, descripcion, autor, editorial,
                        new java.util.Date(), isbn, biblioteca);
            } else if (estado == Estado.MODIFICACION) {
                libroActual.setCategoria(categoria);
                libroActual.setTitulo(titulo);
                libroActual.setDescripcion(descripcion);
                libroActual.setAutor(autor);
                libroActual.setEditorial(editorial);
                libroActual.setCodigoISBN(isbn);
                servicio.modifyLibro(libroActual);
            }

            cargarLibros();
            establecerEstado(Estado.CONSULTA);
            JOptionPane.showMessageDialog(this, "Operación completada");
        });

        btnCancelar.addActionListener(e -> {
            establecerEstado(Estado.CONSULTA);
            cmbCategoria.setSelectedIndex(0);
            txtTitulo.setText("");
            txtDescripcion.setText("");
            txtAutor.setText("");
            txtEditorial.setText("");
            txtISBN.setText("");
            lblFecha.setText("Fecha: ");
        });

        btnEliminar.addActionListener(e -> {
            if (libroActual != null) {
                int respuesta = JOptionPane.showConfirmDialog(this,
                        "¿Eliminar este libro?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    servicio.deleteLibro(libroActual.getId());
                    cargarLibros();
                    establecerEstado(Estado.CONSULTA);
                    cmbCategoria.setSelectedIndex(0);
                    txtTitulo.setText("");
                    txtDescripcion.setText("");
                    txtAutor.setText("");
                    txtEditorial.setText("");
                    txtISBN.setText("");
                    lblFecha.setText("Fecha: ");
                    JOptionPane.showMessageDialog(this, "Libro eliminado");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un libro");
            }
        });
    }
}
