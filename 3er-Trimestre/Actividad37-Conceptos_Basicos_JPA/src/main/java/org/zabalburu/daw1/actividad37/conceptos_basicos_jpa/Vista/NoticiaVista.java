/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.Vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.modelo.Noticia;
import org.zabalburu.daw1.actividad37.conceptos_basicos_jpa.servicio.EditorialServicio;

/**
 *
 * 
 * 
 * 
 * @author Iker Navarro Pérez
 */
public class NoticiaVista extends JFrame {

    private EditorialServicio servicio = EditorialServicio.getServicio();

    //=== TÍTULO ===
    JLabel lblTitulo = new JLabel("Gestor de Noticias");

    //=== TABLA NOTICIAS ===
    JPanel pnlTabla = new JPanel(new FlowLayout());
    JScrollPane jspNoticias = new JScrollPane();
    JTable tblNoticias = new JTable();
    DefaultTableModel dtm = new DefaultTableModel();

    // === NOTICIA SELECCIONADA ===
    GridBagConstraints gbcNoticia;
    JPanel pnlNoticiaSeleccionada = new JPanel(new GridBagLayout());
    JLabel lblIdNoticia = new JLabel("ID:");
    JTextField txtIdNoticia = new JTextField();
    JLabel lblFecha = new JLabel("Fecha:");
    DatePicker datePicker;
    JLabel lblTitular = new JLabel("Titular:");
    JTextField txtTitular = new JTextField();
    JLabel lblSinopsis = new JLabel("Sinopsis:");
    JTextArea txaSinopsis = new JTextArea();
    JLabel lblUrl = new JLabel("URL:");
    JTextField txtUrl = new JTextField();

    //=== BOTONES ===
    JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    JButton btnAñadir = new JButton("Añadir");
    JButton btnModificar = new JButton("Modificar");
    JButton btnEliminar = new JButton("Eliminar");
    JButton btnLimpiar = new JButton("Limpiar");

    public NoticiaVista() {
        this.setTitle("Gestión Noticias - Actividad37 - Iker Navarro Pérez");
        this.setSize(new Dimension(1200, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // === TÍTULO (Norte) ===
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        this.add(lblTitulo, BorderLayout.NORTH);

        // === TABLA (Centro) ===
        iniciarTabla();
        cargarTabla();
        this.add(jspNoticias, BorderLayout.CENTER);

        // === NOTICIA SELECCIONADA (Oeste) ===
        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 0;
        gbcNoticia.gridy = 0;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.anchor = GridBagConstraints.LINE_END;
        pnlNoticiaSeleccionada.add(lblIdNoticia, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 1;
        gbcNoticia.gridy = 0;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.fill = GridBagConstraints.HORIZONTAL;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        txtIdNoticia.setPreferredSize(new Dimension(300, 25));
        pnlNoticiaSeleccionada.add(txtIdNoticia, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 0;
        gbcNoticia.gridy = 1;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.anchor = GridBagConstraints.LINE_END;
        pnlNoticiaSeleccionada.add(lblFecha, gbcNoticia);

        Locale lopesp = new Locale("es", "ES");
        DatePickerSettings dateSettings = new DatePickerSettings(lopesp);
        datePicker = new DatePicker(dateSettings);
        datePicker.setDateToToday();
        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 1;
        gbcNoticia.gridy = 1;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlNoticiaSeleccionada.add(datePicker, gbcNoticia);

        /*
        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 1;
        gbcNoticia.gridy = 1;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.fill = GridBagConstraints.HORIZONTAL;
        pnlNoticiaSeleccionada.add(spiFecha, gbcNoticia);
         */
        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 0;
        gbcNoticia.gridy = 2;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.anchor = GridBagConstraints.LINE_END;
        pnlNoticiaSeleccionada.add(lblTitular, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 1;
        gbcNoticia.gridy = 2;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.fill = GridBagConstraints.HORIZONTAL;
        pnlNoticiaSeleccionada.add(txtTitular, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 0;
        gbcNoticia.gridy = 3;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.anchor = GridBagConstraints.LINE_END;
        pnlNoticiaSeleccionada.add(lblSinopsis, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 1;
        gbcNoticia.gridy = 3;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.fill = GridBagConstraints.HORIZONTAL;
        txaSinopsis.setPreferredSize(new Dimension(300, 80));
        pnlNoticiaSeleccionada.add(txaSinopsis, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 0;
        gbcNoticia.gridy = 4;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.anchor = GridBagConstraints.LINE_END;
        pnlNoticiaSeleccionada.add(lblUrl, gbcNoticia);

        gbcNoticia = new GridBagConstraints();
        gbcNoticia.gridx = 1;
        gbcNoticia.gridy = 4;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.insets = new Insets(5, 5, 5, 10);
        gbcNoticia.fill = GridBagConstraints.HORIZONTAL;
        pnlNoticiaSeleccionada.add(txtUrl, gbcNoticia);

        gbcNoticia = new GridBagConstraints(); // SEPARADOR PARA ALINEAR TODO AL NORTE
        gbcNoticia.gridx = 0;
        gbcNoticia.gridy = 5;
        gbcNoticia.gridwidth = 2;
        gbcNoticia.weighty = 1.0;
        pnlNoticiaSeleccionada.add(new JLabel(), gbcNoticia);

        pnlNoticiaSeleccionada.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        this.add(pnlNoticiaSeleccionada, BorderLayout.EAST);

        // === BOTONES (Sur) ===
        btnAñadir.addActionListener(e -> {
            evtBotonAñadir();
        });
        pnlBotones.add(btnAñadir);

        btnModificar.addActionListener(e -> {
            evtBotonModificar();
        });
        pnlBotones.add(btnModificar);

        btnEliminar.addActionListener(e -> {
            evtBotonEliminar();
        });
        pnlBotones.add(btnEliminar);

        btnLimpiar.addActionListener(e -> {
            evtBotonLimpiar();
        });
        pnlBotones.add(btnLimpiar);

        this.add(pnlBotones, BorderLayout.SOUTH);

    }

    private void iniciarTabla() {
        Vector<String> vctColumnas = new Vector<>();
        vctColumnas.add("ID Noticia");
        vctColumnas.add("Fecha");
        vctColumnas.add("Titular");
        vctColumnas.add("Sinopsis");
        vctColumnas.add("URL");
        dtm.setColumnIdentifiers(vctColumnas);
        tblNoticias.setModel(dtm);
        tblNoticias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblNoticias.getTableHeader().setReorderingAllowed(false);
        tblNoticias.getTableHeader().setResizingAllowed(false);
        tblNoticias.setSize(400, 600);
        jspNoticias.setViewportView(tblNoticias);
        jspNoticias.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    }

    private void cargarTabla() {
        dtm.setRowCount(0);
        for (Noticia noticia : servicio.getNoticias()) {
            Vector vctFila = new Vector();
            vctFila.add(noticia.getId());
            vctFila.add(noticia.getFecha());
            vctFila.add(noticia.getTitular());
            vctFila.add(noticia.getSinopsis());
            vctFila.add(noticia.getUrl());
            dtm.addRow(vctFila);
        }
    }

    private void evtBotonAñadir() {
        Noticia n = new Noticia();
        n.setFecha(datePicker.getDate());
        n.setTitular(txtTitular.getText());
        n.setSinopsis(txaSinopsis.getText());
        n.setUrl(txtUrl.getText());
        servicio.addNoticia(n);
        cargarTabla();
    }

    private void evtBotonModificar() {
        Noticia n = new Noticia();
        n.setId(Integer.valueOf(txtIdNoticia.getText()));
        n.setFecha(datePicker.getDate());
        n.setTitular(txtTitular.getText());
        n.setSinopsis(txaSinopsis.getText());
        n.setUrl(txtUrl.getText());
        servicio.modifyNoticia(n);
        cargarTabla();
    }

    private void evtBotonEliminar() {
        servicio.removeNoticia(Integer.valueOf(txtIdNoticia.getText()));
        cargarTabla();
    }

    private void evtBotonLimpiar() {
        txtIdNoticia.setText("");
        datePicker.setDateToToday();
        txtTitular.setText("");
        txaSinopsis.setText("");
        txtUrl.setText("");
    }
}
