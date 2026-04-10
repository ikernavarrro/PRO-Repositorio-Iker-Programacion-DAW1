/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.zabalburu.gestion_streamers.view.panels;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.zabalburu.gestion_streamers.model.Sesion;
import org.zabalburu.gestion_streamers.model.Streamer;
import org.zabalburu.gestion_streamers.service.GestionService;
import org.zabalburu.gestion_streamers.util.Categoria;
import org.zabalburu.gestion_streamers.util.Estado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PnlSesiones extends javax.swing.JPanel {

    private GestionService service = GestionService.getService();
    private Estado estado = Estado.CONSULTA;

    private DatePicker datePicker;
    private DefaultTableModel dtm;

    /**
     * Creates new form PnlDashboard
     */
    public PnlSesiones() {
        initComponents();
        iniciarDatePicker();
        inicializarTabla();
        iniciarComboCategoria();
        iniciarComboStreamer();
        actualizarTabla();
        mostrar();
    }

    private void iniciarDatePicker() {
        Locale lopesp = new Locale("es", "ES");
        DatePickerSettings dateSettings = new DatePickerSettings(lopesp);
        datePicker = new DatePicker(dateSettings);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(datePicker, gridBagConstraints);
    }

    private void inicializarTabla() {
        Vector<String> vctColumnas = new Vector<>();
        vctColumnas.add("ID");
        vctColumnas.add("Streamer");
        vctColumnas.add("Fecha");
        vctColumnas.add("Duración");
        vctColumnas.add("Categoría");

        tblSesiones.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                return lbl;
            }
        });

        tblSesiones.setDefaultRenderer(Streamer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Streamer streamer = (Streamer) value;
                lbl.setText(streamer.getNick());
                return lbl;
            }
        });

        tblSesiones.setDefaultRenderer(LocalDate.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                return lbl;
            }
        });

        tblSesiones.setDefaultRenderer(Categoria.class, new DefaultTableCellRenderer() {
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
                    case 0, 3 ->
                        Integer.class;
                    case 1 ->
                        Streamer.class;
                    case 2 ->
                        LocalDate.class;
                    default ->
                        Categoria.class;
                };
            }
        };

        tblSesiones.setModel(dtm);
        tblSesiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblSesiones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSesionSeleccionada();
            }
        });
    }

    private void cargarSesionSeleccionada() {
        int fila = tblSesiones.getSelectedRow();
        if (fila != -1) {
            lblIdSesion.setText(tblSesiones.getValueAt(fila, 0).toString());
            cbxStreamer.setSelectedItem(((Streamer) tblSesiones.getValueAt(fila, 1)));
            datePicker.setDate((LocalDate) tblSesiones.getValueAt(fila, 2));
            spiDuracion.setValue((Integer) tblSesiones.getValueAt(fila, 3));
            cbxCategoria.setSelectedItem((Categoria) tblSesiones.getValueAt(fila, 4));
        }
    }

    private void iniciarComboCategoria() {
        for (Categoria c : Categoria.values()) {
            cbxCategoria.addItem(c);
        }
    }

    private void iniciarComboStreamer() {
        cbxStreamer.removeAllItems();
        for (Streamer streamer : service.getStreamers()) {
            cbxStreamer.addItem(streamer);
        }
    }

    private void actualizarTabla() {
        dtm.setNumRows(0);
        for (Sesion sesion : service.getSesiones()) {
            Vector vctFila = new Vector();
            vctFila.add(sesion.getId());
            vctFila.add(sesion.getStreamer());
            vctFila.add(sesion.getFecha());
            vctFila.add(sesion.getDuracion());
            vctFila.add(sesion.getCategoria());
            dtm.addRow(vctFila);
        }

        if (dtm.getRowCount() > 0) {
            tblSesiones.setRowSelectionInterval(0, 0);
            cargarSesionSeleccionada();
        }

    }

    private void mostrar() {
        if (service.getSesiones().isEmpty()) {
            estado = Estado.ALTA;
        }

        if (estado == Estado.ALTA) {
            lblIdSesion.setText("AUTO");
            cbxStreamer.setSelectedIndex(0);
            datePicker.setDateToToday();
            spiDuracion.setValue(0);
            cbxCategoria.setSelectedIndex(0);
        } else {
            cargarSesionSeleccionada();
        }

        btnAñadir.setEnabled(estado == Estado.CONSULTA);
        btnModificar.setEnabled(estado == Estado.CONSULTA);
        btnEliminar.setEnabled(estado == Estado.CONSULTA);
        btnGuardar.setEnabled(estado != Estado.CONSULTA);
        btnCancelar.setEnabled(estado != Estado.CONSULTA && !service.getStreamers().isEmpty());
        tblSesiones.setEnabled(estado == Estado.CONSULTA);

        cbxStreamer.setEnabled(estado != Estado.CONSULTA);
        datePicker.setEnabled(estado != Estado.CONSULTA);
        spiDuracion.setEnabled(estado != Estado.CONSULTA);
        cbxCategoria.setEnabled(estado != Estado.CONSULTA);
        
        iniciarComboStreamer();

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

        pnlSuperior = new javax.swing.JPanel();
        lblSesiones = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        jspSesiones = new javax.swing.JScrollPane();
        tblSesiones = new javax.swing.JTable();
        lblId = new javax.swing.JLabel();
        lblIdSesion = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        spiDuracion = new javax.swing.JSpinner();
        lblFecha = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        cbxCategoria = new javax.swing.JComboBox<>();
        lblStreamer = new javax.swing.JLabel();
        cbxStreamer = new javax.swing.JComboBox<>();
        pnlInferior = new javax.swing.JPanel();
        btnAñadir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(620, 600));
        setLayout(new java.awt.BorderLayout());

        pnlSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlSuperior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblSesiones.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblSesiones.setText("Sesiones");
        pnlSuperior.add(lblSesiones);

        add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        pnlCentro.setLayout(new java.awt.GridBagLayout());

        jspSesiones.setViewportView(tblSesiones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.8;
        pnlCentro.add(jspSesiones, gridBagConstraints);

        lblId.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblId, gridBagConstraints);

        lblIdSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblIdSesion, gridBagConstraints);

        lblDuracion.setText("Duración (Mins)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblDuracion, gridBagConstraints);

        spiDuracion.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(spiDuracion, gridBagConstraints);

        lblFecha.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblFecha, gridBagConstraints);

        lblCategoria.setText("Categoria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblCategoria, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(cbxCategoria, gridBagConstraints);

        lblStreamer.setText("Streamer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(lblStreamer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlCentro.add(cbxStreamer, gridBagConstraints);

        add(pnlCentro, java.awt.BorderLayout.CENTER);

        pnlInferior.setLayout(new java.awt.GridBagLayout());

        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnAñadir, gridBagConstraints);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnModificar, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnEliminar, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnGuardar, gridBagConstraints);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlInferior.add(btnCancelar, gridBagConstraints);

        add(pnlInferior, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        estado = Estado.ALTA;
        mostrar();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        estado = Estado.MODIFICACION;
        mostrar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        estado = Estado.CONSULTA;
        mostrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tblSesiones.getSelectedRow() != -1) { // ¿Hay alguna Sesión seleccionada?
            if (JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la Sesión seleccionada?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                int idSesion = Integer.valueOf(lblIdSesion.getText());
                service.removeSesion(idSesion);
                actualizarTabla();
                mostrar(); // Recargamos los datos de la vista.
                JOptionPane.showMessageDialog(this, "¡Sesión ELIMINADA correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else { // Aquí sabemos que no hay nada seleccionado.
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Sesión para poder ELIMINARLA", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Sesion s = new Sesion();
        s.setStreamer((Streamer) cbxStreamer.getSelectedItem());
        s.setFecha(datePicker.getDate());
        s.setDuracion((Integer) spiDuracion.getValue());
        s.setCategoria((Categoria) cbxCategoria.getSelectedItem());

        if (estado == Estado.ALTA) {
            service.addSesion(s);
            JOptionPane.showMessageDialog(this, "¡Sesión añadida EXITOSAMENTE!", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            s.setId(Integer.valueOf(lblIdSesion.getText()));
            service.modifySesion(s);
            JOptionPane.showMessageDialog(this, "¡Sesión modificada EXITOSAMENTE!", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        estado = Estado.CONSULTA;
        actualizarTabla();
        mostrar();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<Categoria> cbxCategoria;
    private javax.swing.JComboBox<Streamer> cbxStreamer;
    private javax.swing.JScrollPane jspSesiones;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdSesion;
    private javax.swing.JLabel lblSesiones;
    private javax.swing.JLabel lblStreamer;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JSpinner spiDuracion;
    private javax.swing.JTable tblSesiones;
    // End of variables declaration//GEN-END:variables

}
