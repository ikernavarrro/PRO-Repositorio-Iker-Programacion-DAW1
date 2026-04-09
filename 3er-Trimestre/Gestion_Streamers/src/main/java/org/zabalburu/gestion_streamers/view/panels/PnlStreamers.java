/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.zabalburu.gestion_streamers.view.panels;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.zabalburu.gestion_streamers.model.Streamer;
import org.zabalburu.gestion_streamers.service.GestionService;
import org.zabalburu.gestion_streamers.util.Estado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PnlStreamers extends javax.swing.JPanel {

    private GestionService service = GestionService.getService();
    private Estado estado = Estado.CONSULTA;

    private DefaultTableModel dtm;

    /**
     * Creates new form PnlDashboard
     */
    public PnlStreamers() {
        initComponents();
        inicializarTabla();
        actualizarTabla();
        mostrar();
    }

    private void inicializarTabla() {
        Vector<String> vctColumnas = new Vector<>();
        vctColumnas.add("ID");
        vctColumnas.add("Nombre");
        vctColumnas.add("Apellidos");
        vctColumnas.add("Nick");
        vctColumnas.add("Seguidores");

        tblStreamers.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                return lbl;
            }
        });

        tblStreamers.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                return lbl;
            }
        });

        dtm = new DefaultTableModel(vctColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0, 4 ->
                        Integer.class;
                    default ->
                        String.class;
                };
            }
        };

        tblStreamers.setModel(dtm);
        tblStreamers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblStreamers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarStreamerSeleccionado();
            }
        });
    }

    private void cargarStreamerSeleccionado() {
        int fila = tblStreamers.getSelectedRow();
        if (fila != -1) {
            lblIdStreamer.setText(tblStreamers.getValueAt(fila, 0).toString());
            txtNombre.setText(tblStreamers.getValueAt(fila, 1).toString());
            txtApellidos.setText(tblStreamers.getValueAt(fila, 2).toString());
            txtNick.setText(tblStreamers.getValueAt(fila, 3).toString());
            ftxSeguidores.setValue(tblStreamers.getValueAt(fila, 4).toString());
        }
    }

    private void actualizarTabla() {
        dtm.setNumRows(0);
        for (Streamer streamer : service.getStreamers()) {
            Vector vctFila = new Vector();
            vctFila.add(streamer.getId());
            vctFila.add(streamer.getNombre());
            vctFila.add(streamer.getApellidos());
            vctFila.add(streamer.getNick());
            vctFila.add(streamer.getSeguidores());
            dtm.addRow(vctFila);
        }

        if (dtm.getRowCount() > 0) {
            tblStreamers.setRowSelectionInterval(0, 0);
            cargarStreamerSeleccionado();
        }

    }

    private void mostrar() {
        if (service.getStreamers().isEmpty()) {
            estado = Estado.ALTA;
        }

        if (estado == Estado.ALTA) {
            lblIdStreamer.setText("AUTO");
            txtNombre.setText("");
            txtApellidos.setText("");
            txtNick.setText("");
            ftxSeguidores.setValue(0);
        } else {
            cargarStreamerSeleccionado();
        }
        
        btnAñadir.setEnabled(estado == Estado.CONSULTA);
        btnModificar.setEnabled(estado == Estado.CONSULTA);
        btnEliminar.setEnabled(estado == Estado.CONSULTA);
        btnGuardar.setEnabled(estado != Estado.CONSULTA);
        btnCancelar.setEnabled(estado != Estado.CONSULTA && !service.getStreamers().isEmpty());
        
        txtNombre.setEnabled(estado != Estado.CONSULTA);
        txtApellidos.setEnabled(estado != Estado.CONSULTA);
        txtNick.setEnabled(estado != Estado.CONSULTA);
        ftxSeguidores.setEnabled(estado != Estado.CONSULTA);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlStreamers = new javax.swing.JPanel();
        lblDashboard = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        jspStreamers = new javax.swing.JScrollPane();
        tblStreamers = new javax.swing.JTable();
        lblId = new javax.swing.JLabel();
        lblIdStreamer = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblNick = new javax.swing.JLabel();
        txtNick = new javax.swing.JTextField();
        lblSeguidores = new javax.swing.JLabel();
        ftxSeguidores = new javax.swing.JFormattedTextField();
        btnGestionarSesiones = new javax.swing.JButton();
        pnlInferior = new javax.swing.JPanel();
        btnAñadir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(620, 600));
        setLayout(new java.awt.BorderLayout());

        pnlStreamers.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlStreamers.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblDashboard.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblDashboard.setText("Streamers");
        pnlStreamers.add(lblDashboard);

        add(pnlStreamers, java.awt.BorderLayout.PAGE_START);

        pnlCentro.setLayout(new java.awt.GridBagLayout());

        jspStreamers.setViewportView(tblStreamers);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.8;
        pnlCentro.add(jspStreamers, gridBagConstraints);

        lblId.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblId, gridBagConstraints);

        lblIdStreamer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblIdStreamer, gridBagConstraints);

        lblNombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblNombre, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(txtNombre, gridBagConstraints);

        lblApellidos.setText("Apellidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblApellidos, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(txtApellidos, gridBagConstraints);

        lblNick.setText("Nick");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblNick, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(txtNick, gridBagConstraints);

        lblSeguidores.setText("Seguidores");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblSeguidores, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(ftxSeguidores, gridBagConstraints);

        btnGestionarSesiones.setText("Gestionar Sesiones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(btnGestionarSesiones, gridBagConstraints);

        add(pnlCentro, java.awt.BorderLayout.CENTER);

        pnlInferior.setLayout(new java.awt.GridBagLayout());

        btnAñadir.setText("Añadir");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnAñadir, gridBagConstraints);

        btnModificar.setText("Modificar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnModificar, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnEliminar, gridBagConstraints);

        btnGuardar.setText("Guardar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnGuardar, gridBagConstraints);

        btnCancelar.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnCancelar, gridBagConstraints);

        add(pnlInferior, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGestionarSesiones;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JFormattedTextField ftxSeguidores;
    private javax.swing.JScrollPane jspStreamers;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdStreamer;
    private javax.swing.JLabel lblNick;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSeguidores;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlStreamers;
    private javax.swing.JTable tblStreamers;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtNick;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
