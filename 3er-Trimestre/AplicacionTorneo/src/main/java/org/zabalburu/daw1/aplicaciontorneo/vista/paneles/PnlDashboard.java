/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.vista.paneles;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import oracle.jdbc.driver.utils.WalletLocation;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Juego;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Jugador;
import org.zabalburu.daw1.aplicaciontorneo.modelo.Partida;
import org.zabalburu.daw1.aplicaciontorneo.servicio.TorneoServicio;

/**
 *
 * @author DAW1
 */
public class PnlDashboard extends javax.swing.JPanel {

    private PnlCard pnlJuegos = new PnlCard("Juegos");
    private PnlCard pnlJugadores = new PnlCard("Jugadores");
    private PnlCard pnlPartidas = new PnlCard("Partidas");

    private TorneoServicio servicio = TorneoServicio.getServicio();
    private DefaultTableModel dtm;

    /**
     * Creates new form pnlDashboard
     */
    public PnlDashboard() {
        initComponents();
        this.setPreferredSize(new Dimension(0, 50));
        pnlCards.add(pnlJuegos);
        pnlCards.add(pnlJugadores);
        pnlCards.add(pnlPartidas);
        inicializarTabla();
        actualizarPanel();
        this.setVisible(true);
    }

    private void inicializarTabla() {
        Vector<String> vctColumnas = new Vector<>();
        vctColumnas.add("ID");
        vctColumnas.add("Fecha");
        vctColumnas.add("Título");
        vctColumnas.add("Duración");
        vctColumnas.add("Ganador");
        vctColumnas.add("Perdedor");
        vctColumnas.add("Puntos");

        tblUltimas.setDefaultRenderer(LocalDateTime.class, new TableCellRenderer() {
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

        tblUltimas.setDefaultRenderer(Juego.class, new DefaultTableCellRenderer() {
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

        tblUltimas.setDefaultRenderer(Jugador.class, new DefaultTableCellRenderer() {
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
        
        tblUltimas.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                lbl.setHorizontalAlignment(JLabel.CENTER);
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
        };

        tblUltimas.setModel(dtm);
        tblUltimas.setAutoCreateRowSorter(true);
        tblUltimas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void actualizarPanel() {
        pnlJuegos.setValor(servicio.getJuegos().size());
        pnlJugadores.setValor(servicio.getJugadores().size());
        pnlPartidas.setValor(servicio.getPartidas().size());
        dtm.setNumRows(0);
        for (Partida p : servicio.getPartidas()
                .stream()
                .sorted((p1, p2) -> p2.getFecha().compareTo(p1.getFecha()))
                .limit(390)
                .toList()) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSuperior = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButton1 = new javax.swing.JButton();
        pnlCards = new javax.swing.JPanel();
        pnl3 = new javax.swing.JPanel();
        lblUltimasPartidas = new javax.swing.JLabel();
        jspUltimas = new javax.swing.JScrollPane();
        tblUltimas = new javax.swing.JTable();
        pnlRegistrarPartida = new javax.swing.JPanel();
        btnRegistrarPartida = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        pnlSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 0));
        pnlSuperior.setLayout(new java.awt.BorderLayout());

        pnl1.setLayout(new javax.swing.BoxLayout(pnl1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Dashboard");
        pnl1.add(jLabel1);
        pnl1.add(filler1);

        jButton1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jButton1.setText("Refrescar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnl1.add(jButton1);

        pnlSuperior.add(pnl1, java.awt.BorderLayout.NORTH);

        pnlCards.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 1, 1));
        pnlCards.setMinimumSize(new java.awt.Dimension(0, 70));
        pnlCards.setPreferredSize(new java.awt.Dimension(0, 70));
        pnlCards.setLayout(new java.awt.GridLayout(1, 3, 15, 0));
        pnlSuperior.add(pnlCards, java.awt.BorderLayout.CENTER);

        pnl3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblUltimasPartidas.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblUltimasPartidas.setText("Últimas Partidas");
        pnl3.add(lblUltimasPartidas);

        pnlSuperior.add(pnl3, java.awt.BorderLayout.SOUTH);

        add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        jspUltimas.setViewportView(tblUltimas);

        add(jspUltimas, java.awt.BorderLayout.CENTER);

        pnlRegistrarPartida.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnlRegistrarPartida.setLayout(new java.awt.GridLayout(1, 1));

        btnRegistrarPartida.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        btnRegistrarPartida.setText("Registrar Partida");
        btnRegistrarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPartidaActionPerformed(evt);
            }
        });
        pnlRegistrarPartida.add(btnRegistrarPartida);

        add(pnlRegistrarPartida, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPartidaActionPerformed
        JPanel pnl = (JPanel) this.getParent();
        CardLayout cl = (CardLayout) pnl.getLayout();
        cl.show(pnl, "REGISTRAR");
        
    }//GEN-LAST:event_btnRegistrarPartidaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        servicio.cargarBBDD();
        actualizarPanel();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    class PnlCard extends JPanel {

        private JLabel lblDescripcion = new JLabel();
        private JLabel lblValor = new JLabel();

        public PnlCard(String descripcion) {
            lblDescripcion.setText(descripcion);
            this.setLayout(new BorderLayout(0, 5));
            //lblDescripcion.setFont(lblDescripcion.getFont().deriveFont(14f));
            lblValor.setFont(lblValor.getFont().deriveFont(24f));
            this.setPreferredSize(new Dimension(0, 50));
            this.putClientProperty("FlatLaf.style",
                    """
                    arc:20;
                    background: lighten(@background, 10%);
                    border:5,15,5,5,darken(@background, 10%),1,20;
                    """);
            //lblDescripcion.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
            //lblValor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
            this.add(lblDescripcion, BorderLayout.NORTH);
            this.add(lblValor);
            this.setVisible(true);
        }

        public void setValor(Integer valor) {
            lblValor.setText(String.valueOf(valor));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarPartida;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jspUltimas;
    private javax.swing.JLabel lblUltimasPartidas;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlRegistrarPartida;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTable tblUltimas;
    // End of variables declaration//GEN-END:variables
}
