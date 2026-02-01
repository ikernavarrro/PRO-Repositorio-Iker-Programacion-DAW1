/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.guiada_almacenproductos_1.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import static java.awt.image.ImageObserver.HEIGHT;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.zabalburu.daw1.guiada_almacenproductos_1.config.AppConfig;
import org.zabalburu.daw1.guiada_almacenproductos_1.servicio.InventarioServicio;
import org.zabalburu.daw1.guiada_almacenproductos_1.util.Estado;

/**
 *
 * @author Iker Navarro Pérez
 */
public class VentanaInventarioFrame extends JFrame{

    private static InventarioServicio servicio = InventarioServicio.getInstance();
    Estado actual = Estado.CONSULTA;
    int pos = 0;
    
     //Componentes
    //pnlContenido zona izquierda
    private JTable tblProductos = new JTable();
    private JScrollPane jspProductos = new JScrollPane(tblProductos);
    private JPanel pnlTabla = new JPanel();
    //pnlContenido zona derecha
    private JLabel lblId = new JLabel("ID");
    private JTextField txtId = new JTextField();
    private JPanel pnlId = new JPanel(new BorderLayout(HEIGHT, 5));
    private JLabel lblNombre = new JLabel("Nombre");
    private JTextField txtNombre = new JTextField();
    private JPanel pnlNombre = new JPanel(new BorderLayout(HEIGHT, 5));
    private JLabel lblUbicacion = new JLabel("Ubicacion");
    private JTextField txtUbicacion = new JTextField();
    private JPanel pnlUbicacion = new JPanel(new BorderLayout(HEIGHT, 5));
    private JLabel lblCapacidadMaxima = new JLabel("Capacidad Máxima");
    private JTextField txtCapacidadMaxima = new JTextField();
    private JPanel pnlCapacidadMaxima = new JPanel(new BorderLayout(HEIGHT, 5));
    private JPanel pnlDatos = new JPanel(new GridLayout(4, 1, 10, 10));
    //pnlContenido
    private JPanel pnlContenido = new JPanel(new BorderLayout(10, 5));
    
    //pnlGeneral
    private JPanel pnlGeneral = new JPanel(new BorderLayout(10, 5));
    // == PRE-CONSTRUCTOR ==
    
    
    // == CONSTRUCTOR ==
    public VentanaInventarioFrame() {
        this.setSize(AppConfig.DM_VENTANA_INVENTARIO);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // == Configuración ==
        pnlTabla.add(jspProductos);
        pnlContenido.add(pnlTabla, BorderLayout.WEST);
        pnlId.add(lblId,BorderLayout.CENTER);
        pnlId.add(txtId,BorderLayout.SOUTH);
        pnlDatos.add(pnlId);
        pnlNombre.add(lblNombre);
        pnlNombre.add(txtNombre);
        pnlDatos.add(pnlNombre);
        pnlUbicacion.add(lblUbicacion);
        pnlUbicacion.add(txtUbicacion);
        pnlDatos.add(pnlUbicacion);
        pnlCapacidadMaxima.add(lblCapacidadMaxima);
        pnlCapacidadMaxima.add(txtCapacidadMaxima);
        pnlDatos.add(pnlCapacidadMaxima);
        pnlContenido.add(pnlDatos, BorderLayout.CENTER);
        
        pnlGeneral.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        pnlGeneral.add(pnlContenido, BorderLayout.CENTER);
        
        this.add(pnlGeneral);
        
        this.setVisible(true);
    }
}
