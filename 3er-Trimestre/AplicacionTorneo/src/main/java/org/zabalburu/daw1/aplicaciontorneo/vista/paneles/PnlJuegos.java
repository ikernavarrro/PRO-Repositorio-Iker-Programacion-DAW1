/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.vista.paneles;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.zabalburu.daw1.aplicaciontorneo.exception.JuegoConPartidasException;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Partida;
import org.zabalburu.daw1.aplicaciontorneo.servicio.TorneoServicio;
import org.zabalburu.daw1.aplicaciontorneo.vista.vista.dialogs.DlgJuego;

/**
 *
 * @author DAW1
 */
public class PnlJuegos extends javax.swing.JPanel {

    private TorneoServicio servicio = TorneoServicio.getServicio();

    /**
     * Creates new form PnlJuegos
     */
    public PnlJuegos() {
        initComponents();
        inicializarListaJuegos();
        inicializarTabla();
        inicializarCuadroBusqueda();
    }

    private void inicializarListaJuegos() {
        lstJuegos.setListData(new Vector<>(servicio.getJuegos()));
        lstJuegos.setFixedCellHeight(40);
        lstJuegos.setFixedCellWidth(120);
        lstJuegos.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Juego j = (Juego) value;
                lbl.setText(j.getTitulo());
                lbl.setFont(lbl.getFont().deriveFont(16f));
                return lbl;
            }

        });
    }

    private void inicializarTabla() {
        tblPartidasJuego.setModel(new DefaultTableModel(
                new String[]{
                    "ID",
                    "Fecha",
                    "Duración",
                    "Ganador",
                    "Perdedor",
                    "Puntos"
                }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0, 5 ->
                        Integer.class;
                    case 1 ->
                        LocalDateTime.class;
                    case 3, 4 ->
                        Jugador.class;
                    default ->
                        String.class;
                };
            }
        });

        tblPartidasJuego.setDefaultRenderer(LocalDateTime.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {
                LocalDateTime time = (LocalDateTime) value;
                JLabel lbl = new JLabel();
                lbl.setOpaque(true);
                lbl.setText(time.format(DateTimeFormatter.ofPattern("dd MMM y HH:mm")));
                lbl.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lbl.setBackground(table.getSelectionBackground());
                    lbl.setForeground(table.getSelectionForeground());
                } else {
                    lbl.setBackground(table.getBackground());
                    lbl.setForeground(table.getForeground());
                }
                return lbl;
            }
        });

        tblPartidasJuego.setDefaultRenderer(Juego.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Juego j = (Juego) value;
                lbl.setText("%s [ %s ]".formatted(j.getTitulo(), j.getTipo()));
                lbl.setToolTipText("""
                                   Título: %s
                                   Tipo: %s
                                   Descripción: %s
                                   """.formatted(j.getTitulo(), j.getTipo(), j.getDescripcion()));
                return lbl;
            }

        });

        tblPartidasJuego.setDefaultRenderer(Jugador.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Jugador j = (Jugador) value;
                lbl.setText(j.getNick());
                lbl.setToolTipText("%s, %s [id:%d]".formatted(j.getApellidos(), j.getNombre(), j.getId()));
                /*try {
                    ImageIcon imag = new ImageIcon(URI.create(j.getImagen()).toURL());
                    int max = Math.max(imag.getIconWidth(), imag.getIconHeight());
                    double factor = 40.0 / max;
                    int alto = (int)(imag.getIconHeight() * factor);
                    int ancho = (int)(imag.getIconWidth()* factor);
                    imag.getImage().getScaledInstance(alto, ancho, Image.SCALE_SMOOTH);
                    lbl.setIcon(imag);
                } catch (MalformedURLException ex) {
                    System.getLogger(PnlDashboard.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }*/
                lbl.setIcon(j.getImagenAvatar());
                return lbl;
            }
        });

        tblPartidasJuego.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                lbl.setHorizontalAlignment(JLabel.CENTER);
                return lbl;
            }
        });
        tblPartidasJuego.setAutoCreateRowSorter(true);
        tblPartidasJuego.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void actualizarTabla(Juego j) {
        DefaultTableModel dtm = (DefaultTableModel) tblPartidasJuego.getModel();
        dtm.setRowCount(0);
        if (j != null) {
            for (Partida p : j.getPartidas()) {
                Vector vctFila = new Vector();
                vctFila.add(p.getId());
                vctFila.add(p.getFecha());
                vctFila.add(p.getDuracion());
                vctFila.add(p.getGana());
                vctFila.add(p.getPierde());
                vctFila.add(p.getPuntos());
                dtm.addRow(vctFila);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSuperior = new javax.swing.JPanel();
        lblJuegos = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btnRefrescar = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        pnlListaJuegos = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        btNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jspJuegos = new javax.swing.JScrollPane();
        lstJuegos = new javax.swing.JList<>();
        pnlPartidas = new javax.swing.JPanel();
        lblPartidas = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jspPartidasJuego = new javax.swing.JScrollPane();
        tblPartidasJuego = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        pnlSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 5, 0));
        pnlSuperior.setLayout(new javax.swing.BoxLayout(pnlSuperior, javax.swing.BoxLayout.LINE_AXIS));

        lblJuegos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblJuegos.setText("Juegos");
        pnlSuperior.add(lblJuegos);
        pnlSuperior.add(filler1);

        btnRefrescar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });
        pnlSuperior.add(btnRefrescar);

        add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        pnlCentro.setLayout(new java.awt.BorderLayout(10, 0));

        pnlListaJuegos.setLayout(new java.awt.BorderLayout(0, 10));

        txtBuscar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        pnlListaJuegos.add(txtBuscar, java.awt.BorderLayout.NORTH);

        pnlBotones.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        btNuevo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btNuevo.setText("Nuevo");
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });
        pnlBotones.add(btNuevo);

        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlBotones.add(btnEditar);

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlBotones.add(btnEliminar);

        pnlListaJuegos.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        lstJuegos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstJuegos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstJuegos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstJuegosValueChanged(evt);
            }
        });
        jspJuegos.setViewportView(lstJuegos);

        pnlListaJuegos.add(jspJuegos, java.awt.BorderLayout.CENTER);

        pnlCentro.add(pnlListaJuegos, java.awt.BorderLayout.LINE_START);

        pnlPartidas.setLayout(new java.awt.BorderLayout(0, 10));

        lblPartidas.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblPartidas.setText("Partidas del Juego Seleccionado");
        pnlPartidas.add(lblPartidas, java.awt.BorderLayout.PAGE_START);

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRegistrar.setText("Registrar Partida");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlPartidas.add(btnRegistrar, java.awt.BorderLayout.PAGE_END);

        jspPartidasJuego.setViewportView(tblPartidasJuego);

        pnlPartidas.add(jspPartidasJuego, java.awt.BorderLayout.CENTER);

        pnlCentro.add(pnlPartidas, java.awt.BorderLayout.CENTER);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lstJuegosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstJuegosValueChanged
        if (!evt.getValueIsAdjusting()) {
            Juego j = lstJuegos.getSelectedValue();
            actualizarTabla(j);
        }
    }//GEN-LAST:event_lstJuegosValueChanged

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        new DlgJuego(null, true, null).setVisible(true);
        lstJuegos.setListData(new Vector<>(servicio.getJuegos()));
        lstJuegos.setSelectedIndex(lstJuegos.getModel().getSize() - 1);
        lstJuegos.ensureIndexIsVisible(lstJuegos.getModel().getSize() - 1);
        lstJuegos.requestFocus();
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Juego j = lstJuegos.getSelectedValue();
        if (j == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el juego a Modificar", "Seleccione el juego", JOptionPane.WARNING_MESSAGE);
        } else {
            new DlgJuego(null, true, j).setVisible(true);
            int pos = lstJuegos.getSelectedIndex();
            lstJuegos.setListData(new Vector<>(servicio.getJuegos()));
            lstJuegos.setSelectedIndex(pos);
            lstJuegos.ensureIndexIsVisible(pos);
            lstJuegos.requestFocus();

        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Juego j = lstJuegos.getSelectedValue();
        if (j == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el juego a Eliminar", "Seleccione el juego", JOptionPane.WARNING_MESSAGE);
        } else if (!j.getPartidas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡NO es posible ELIMINAR un JUEGO con Partidas Registradas!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(this, "¿Está seguro de ELIMINAR el juego %s?".formatted(j.getTitulo()), "Eliminar Juego", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int pos = lstJuegos.getSelectedIndex();
            try {
                servicio.removeJuego(j.getId());
            } catch (JuegoConPartidasException ex) {
                System.getLogger(PnlJuegos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            JOptionPane.showMessageDialog(null, "¡Juego eliminado correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
            lstJuegos.setListData(new Vector<>(servicio.getJuegos()));
            if (pos == lstJuegos.getModel().getSize()) {
                pos--;
            }
            lstJuegos.setSelectedIndex(pos);
            lstJuegos.ensureIndexIsVisible(pos);
            lstJuegos.requestFocus();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        servicio.cargarBBDD();
        actualizarTabla(lstJuegos.getSelectedValue());
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        JPanel pnl = (JPanel) this.getParent();
        CardLayout cl = (CardLayout) pnl.getLayout();
        cl.show(pnl, "REGISTRAR");
    }//GEN-LAST:event_btnRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jspJuegos;
    private javax.swing.JScrollPane jspPartidasJuego;
    private javax.swing.JLabel lblJuegos;
    private javax.swing.JLabel lblPartidas;
    private javax.swing.JList<Juego> lstJuegos;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlListaJuegos;
    private javax.swing.JPanel pnlPartidas;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTable tblPartidasJuego;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void inicializarCuadroBusqueda() {
        txtBuscar.putClientProperty("JTextField.placeholderText", "🔍 Buscar Juego...");
        txtBuscar.putClientProperty("JTextField.showClearButton", true);
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarJuegos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarJuegos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarJuegos();
            }
        });
    }

    private void buscarJuegos() {
        String busqueda = txtBuscar.getText();
        List<Juego> juegos = servicio.getJuegos()
                .stream()
                .filter(j -> j.getTitulo().toLowerCase().contains(busqueda.toLowerCase()))
                .toList();
        lstJuegos.setListData(new Vector<>(juegos));
        if (!juegos.isEmpty()) {
            lstJuegos.setSelectedIndex(0);
        } else {
            lstJuegos.setSelectedIndex(-1);
        }
    }
}
