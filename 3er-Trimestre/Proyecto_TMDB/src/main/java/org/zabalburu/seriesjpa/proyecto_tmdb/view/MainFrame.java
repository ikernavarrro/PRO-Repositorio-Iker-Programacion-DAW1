/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.seriesjpa.proyecto_tmdb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.zabalburu.seriesjpa.proyecto_tmdb.dao.impl.UsuarioDAOImpl;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Usuario;
import org.zabalburu.seriesjpa.proyecto_tmdb.model.Valoracion;
import org.zabalburu.seriesjpa.proyecto_tmdb.service.AuthService;
import org.zabalburu.seriesjpa.proyecto_tmdb.util.JPAUtil;

/**
 *
 * @author DAW1
 */
public class MainFrame extends JFrame {

    private final Usuario usuario;

    private JLabel lblBienvenida;
    private JLabel lblCorreo;

    private JLabel lblTotalValoraciones;
    private JLabel lblMediaValoraciones;
    private JLabel lblSerieDestacada;

    private JTable tblValoraciones;
    private DefaultTableModel tableModel;

    private JButton btnBuscarSeries;
    private JButton btnActualizar;
    private JButton btnCerrarSesion;

    public MainFrame(Usuario usuario) {
        this.usuario = usuario;

        setTitle("SeriesJPA - Panel principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        initComponents();
        cargarResumen();
        cargarTablaValoraciones();
    }

    private void initComponents() {
        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        root.add(crearCabecera(), BorderLayout.NORTH);
        root.add(crearCentro(), BorderLayout.CENTER);
    }

    private JPanel crearCabecera() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JPanel panelTextos = new JPanel();
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("SeriesJPA");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 30));

        lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombreUsuario());
        lblBienvenida.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lblCorreo = new JLabel(usuario.getEmail());
        lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblCorreo.setForeground(new Color(110, 110, 110));

        panelTextos.add(lblTitulo);
        panelTextos.add(Box.createVerticalStrut(8));
        panelTextos.add(lblBienvenida);
        panelTextos.add(Box.createVerticalStrut(4));
        panelTextos.add(lblCorreo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        btnBuscarSeries = new JButton("Buscar series");
        btnActualizar = new JButton("Actualizar");
        btnCerrarSesion = new JButton("Cerrar sesión");

        panelBotones.add(btnBuscarSeries);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrarSesion);

        btnBuscarSeries.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Funcionalidad pendiente del siguiente paso.")
        );

        btnActualizar.addActionListener(e -> {
            cargarResumen();
            cargarTablaValoraciones();
        });

        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        panel.add(panelTextos, BorderLayout.WEST);
        panel.add(panelBotones, BorderLayout.EAST);

        return panel;
    }

    private JPanel crearCentro() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));

        JPanel panelCards = new JPanel(new GridLayout(1, 3, 20, 0));
        panelCards.add(crearCardTotalValoraciones());
        panelCards.add(crearCardMediaValoraciones());
        panelCards.add(crearCardSerieDestacada());

        panel.add(panelCards, BorderLayout.NORTH);
        panel.add(crearPanelTabla(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearCardTotalValoraciones() {
        JPanel card = crearCardBase(new Color(52, 152, 219));

        JLabel lblTitulo = new JLabel("Mis valoraciones");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblTitulo.setForeground(Color.WHITE);

        lblTotalValoraciones = new JLabel("0");
        lblTotalValoraciones.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTotalValoraciones.setForeground(Color.WHITE);

        JLabel lblInfo = new JLabel("Total registradas");
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInfo.setForeground(Color.WHITE);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblTotalValoraciones);
        card.add(Box.createVerticalStrut(8));
        card.add(lblInfo);

        return card;
    }

    private JPanel crearCardMediaValoraciones() {
        JPanel card = crearCardBase(new Color(46, 204, 113));

        JLabel lblTitulo = new JLabel("Puntuación media");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblTitulo.setForeground(Color.WHITE);

        lblMediaValoraciones = new JLabel("0.00");
        lblMediaValoraciones.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblMediaValoraciones.setForeground(Color.WHITE);

        JLabel lblInfo = new JLabel("Media de estrellas");
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInfo.setForeground(Color.WHITE);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblMediaValoraciones);
        card.add(Box.createVerticalStrut(8));
        card.add(lblInfo);

        return card;
    }

    private JPanel crearCardSerieDestacada() {
        JPanel card = crearCardBase(new Color(155, 89, 182));

        JLabel lblTitulo = new JLabel("Serie destacada");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblTitulo.setForeground(Color.WHITE);

        lblSerieDestacada = new JLabel("-");
        lblSerieDestacada.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblSerieDestacada.setForeground(Color.WHITE);

        JLabel lblInfo = new JLabel("Mejor valorada por ti");
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblInfo.setForeground(Color.WHITE);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblSerieDestacada);
        card.add(Box.createVerticalStrut(8));
        card.add(lblInfo);

        return card;
    }

    private JPanel crearCardBase(Color colorFondo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(colorFondo);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        return card;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTabla = new JLabel("Mis valoraciones");
        lblTabla.setFont(new Font("SansSerif", Font.BOLD, 20));

        tableModel = new DefaultTableModel(
                new String[]{"Serie", "Estrellas", "Comentario", "Fecha"},
                0
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> String.class;
                    case 1 -> Integer.class;
                    case 2 -> String.class;
                    case 3 -> String.class;
                    default -> Object.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblValoraciones = new JTable(tableModel);
        tblValoraciones.setRowHeight(28);
        tblValoraciones.getTableHeader().setReorderingAllowed(false);
        tblValoraciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblValoraciones.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(tblValoraciones);

        panel.add(lblTabla, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void cargarResumen() {
        List<Valoracion> valoraciones = usuario.getValoraciones();

        int total = valoraciones.size();
        double media = calcularMedia(valoraciones);
        String serieDestacada = calcularSerieDestacada(valoraciones);

        lblTotalValoraciones.setText(String.valueOf(total));
        lblMediaValoraciones.setText(String.format("%.2f", media));
        lblSerieDestacada.setText(serieDestacada);
    }

    private void cargarTablaValoraciones() {
        tableModel.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        usuario.getValoraciones().stream()
                .sorted(Comparator.comparing(Valoracion::getFecha).reversed())
                .forEach(v -> tableModel.addRow(new Object[]{
                        v.getSerie().getTitulo(),
                        v.getEstrellas(),
                        v.getComentario(),
                        v.getFecha().format(formatter)
                }));
    }

    private double calcularMedia(List<Valoracion> valoraciones) {
        if (valoraciones.isEmpty()) {
            return 0.0;
        }

        return valoraciones.stream()
                .mapToInt(Valoracion::getEstrellas)
                .average()
                .orElse(0.0);
    }

    private String calcularSerieDestacada(List<Valoracion> valoraciones) {
        return valoraciones.stream()
                .max(Comparator.comparingInt(Valoracion::getEstrellas))
                .map(v -> v.getSerie().getTitulo())
                .orElse("-");
    }

    private void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas cerrar sesión?",
                "Confirmar cierre de sesión",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                LoginFrame loginFrame = new LoginFrame(
                        new AuthService(
                                JPAUtil.getEntityManagerFactory(),
                                new UsuarioDAOImpl()
                        )
                );
                loginFrame.setVisible(true);
            });

            dispose();
        }
    }
}
