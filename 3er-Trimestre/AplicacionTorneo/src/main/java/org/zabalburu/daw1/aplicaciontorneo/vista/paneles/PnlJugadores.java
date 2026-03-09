/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.vista.paneles;

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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.zabalburu.daw1.aplicaciontorneo.exception.JugadorConPartidasException;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Partida;
import org.zabalburu.daw1.aplicaciontorneo.servicio.TorneoServicio;
import org.zabalburu.daw1.aplicaciontorneo.vista.vista.dialogs.DlgJuego;
import org.zabalburu.daw1.aplicaciontorneo.vista.vista.dialogs.DlgJugador;

/**
 *
 * @author DAW1
 */
public class PnlJugadores extends javax.swing.JPanel {

    private TorneoServicio servicio = TorneoServicio.getServicio();

    /**
     * Creates new form PnlJuegos
     */
    public PnlJugadores() {
        initComponents();
        inicializarListaJugadores();
        inicializarTabla();
        inicializarCuadroBusqueda();
    }

    private void inicializarListaJugadores() {
        lstJugadores.setListData(new Vector<>(servicio.getJugadores()));
        lstJugadores.setFixedCellHeight(40);
        lstJugadores.setFixedCellWidth(120);
        lstJugadores.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                Jugador j = (Jugador) value;
                lbl.setText(j.getNick());
                lbl.setIcon(j.getAvatar());
                lbl.setFont(lbl.getFont().deriveFont(16f));
                lbl.setToolTipText("%s, %s [id:%d]".formatted(j.getApellidos(), j.getNombre(), j.getId()));
                return lbl;
            }

        });
    }

    private void inicializarTabla() {
        tblPartidasJugador.setModel(new DefaultTableModel(
                new String[]{
                    "ID",
                    "Fecha",
                    "Juego",
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
                    case 0, 6 ->
                        Integer.class;
                    case 1 ->
                        LocalDateTime.class;
                    case 2 ->
                        Juego.class;
                    case 4, 5 ->
                        Jugador.class;
                    default ->
                        String.class;
                };
            }
        });

        tblPartidasJugador.setDefaultRenderer(LocalDateTime.class, new TableCellRenderer() {
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

        tblPartidasJugador.setDefaultRenderer(Juego.class, new DefaultTableCellRenderer() {
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

        tblPartidasJugador.setDefaultRenderer(Jugador.class, new DefaultTableCellRenderer() {
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

        tblPartidasJugador.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                lbl.setHorizontalAlignment(JLabel.CENTER);
                return lbl;
            }
        });
        tblPartidasJugador.setAutoCreateRowSorter(true);
        tblPartidasJugador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void actualizarTabla(Jugador j) {
        DefaultTableModel dtm = (DefaultTableModel) tblPartidasJugador.getModel();
        dtm.setRowCount(0);
        if (j != null) {
            for (Partida p : j.getPartidas()) {
                Vector vctFila = new Vector();
                vctFila.add(p.getId());
                vctFila.add(p.getFecha());
                vctFila.add(p.getJuego());
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
        lblJugadores = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btnRefrescar = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        pnlListaJugadores = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        btNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jspJugadores = new javax.swing.JScrollPane();
        lstJugadores = new javax.swing.JList<>();
        pnlPartidas = new javax.swing.JPanel();
        lblPartidas = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jspPartidasJugador = new javax.swing.JScrollPane();
        tblPartidasJugador = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        pnlSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 5, 0));
        pnlSuperior.setLayout(new javax.swing.BoxLayout(pnlSuperior, javax.swing.BoxLayout.LINE_AXIS));

        lblJugadores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblJugadores.setText("Jugadores");
        pnlSuperior.add(lblJugadores);
        pnlSuperior.add(filler1);

        btnRefrescar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        btnRefrescar.setText("Refrescar");
        pnlSuperior.add(btnRefrescar);

        add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        pnlCentro.setLayout(new java.awt.BorderLayout(10, 0));

        pnlListaJugadores.setLayout(new java.awt.BorderLayout(0, 10));

        txtBuscar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        pnlListaJugadores.add(txtBuscar, java.awt.BorderLayout.NORTH);

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

        pnlListaJugadores.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        lstJugadores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstJugadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstJugadores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstJugadoresValueChanged(evt);
            }
        });
        jspJugadores.setViewportView(lstJugadores);

        pnlListaJugadores.add(jspJugadores, java.awt.BorderLayout.CENTER);

        pnlCentro.add(pnlListaJugadores, java.awt.BorderLayout.LINE_START);

        pnlPartidas.setLayout(new java.awt.BorderLayout(0, 10));

        lblPartidas.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblPartidas.setText("Partidas del Jugador Seleccionado");
        pnlPartidas.add(lblPartidas, java.awt.BorderLayout.PAGE_START);

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRegistrar.setText("Registrar Partida");
        pnlPartidas.add(btnRegistrar, java.awt.BorderLayout.PAGE_END);

        jspPartidasJugador.setViewportView(tblPartidasJugador);

        pnlPartidas.add(jspPartidasJugador, java.awt.BorderLayout.CENTER);

        pnlCentro.add(pnlPartidas, java.awt.BorderLayout.CENTER);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lstJugadoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstJugadoresValueChanged
        if (!evt.getValueIsAdjusting()) {
            Jugador j = lstJugadores.getSelectedValue();
            actualizarTabla(j);
        }
    }//GEN-LAST:event_lstJugadoresValueChanged

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        new DlgJugador(null, true, null).setVisible(true);
        lstJugadores.setListData(new Vector<>(servicio.getJugadores()));
        lstJugadores.setSelectedIndex(lstJugadores.getModel().getSize() - 1);
        lstJugadores.ensureIndexIsVisible(lstJugadores.getModel().getSize() - 1);
        lstJugadores.requestFocus();
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Jugador j = lstJugadores.getSelectedValue();
        if (j == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el jugador a Modificar", "Seleccione el jugador", JOptionPane.WARNING_MESSAGE);
        } else {
            new DlgJugador(null, true, j).setVisible(true);
            int pos = lstJugadores.getSelectedIndex();
            lstJugadores.setListData(new Vector<>(servicio.getJugadores()));
            lstJugadores.setSelectedIndex(pos);
            lstJugadores.ensureIndexIsVisible(pos);
            lstJugadores.requestFocus();

        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Jugador j = lstJugadores.getSelectedValue();
        if (j == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el jugador a Eliminar", "Seleccione el jugador", JOptionPane.WARNING_MESSAGE);
        } else if (!j.getPartidas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡NO es posible ELIMINAR un JUGADOR con Partidas Registradas!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(this, "¿Está seguro de ELIMINAR el jugador %s?".formatted(j.getNick()), "Eliminar Jugador", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int pos = lstJugadores.getSelectedIndex();
            try {
                servicio.removeJugador(j.getId());
            } catch (JugadorConPartidasException ex) {
                System.getLogger(PnlJugadores.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            JOptionPane.showMessageDialog(null, "¡Jugador eliminado correctamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
            lstJugadores.setListData(new Vector<>(servicio.getJugadores()));
            if (pos == lstJugadores.getModel().getSize()) {
                pos--;
            }
            lstJugadores.setSelectedIndex(pos);
            lstJugadores.ensureIndexIsVisible(pos);
            lstJugadores.requestFocus();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jspJugadores;
    private javax.swing.JScrollPane jspPartidasJugador;
    private javax.swing.JLabel lblJugadores;
    private javax.swing.JLabel lblPartidas;
    private javax.swing.JList<Jugador> lstJugadores;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlListaJugadores;
    private javax.swing.JPanel pnlPartidas;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTable tblPartidasJugador;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void inicializarCuadroBusqueda() {
        txtBuscar.putClientProperty("JTextField.placeholderText", "🔍 Buscar Jugador...");
        txtBuscar.putClientProperty("JTextField.showClearButton", true);
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarJugadores();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarJugadores();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarJugadores();
            }
        });
    }

    private void buscarJugadores() {
        String busqueda = txtBuscar.getText();
        List<Jugador> jugadores = servicio.getJugadores()
                .stream()
                .filter(j -> j.getNick().toLowerCase().contains(busqueda.toLowerCase()))
                .toList();
        lstJugadores.setListData(new Vector<>(jugadores));
        if (!jugadores.isEmpty()) {
            lstJugadores.setSelectedIndex(0);
        } else {
            lstJugadores.setSelectedIndex(-1);
        }
    }
}
