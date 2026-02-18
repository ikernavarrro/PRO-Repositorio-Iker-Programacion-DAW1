/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.aplicaciontorneo.util;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.datasource.impl.OracleDataSource;

/**
 *
 * @author ichueca
 */
public class Conexion {

    private static Connection cnn;

    public static Connection getConnection() {
        try {
            if (cnn == null || cnn.isClosed()) {
                try {
                    OracleDataSource ods = new OracleDataSource();
                    ods.setServerName("localhost");
                    ods.setPortNumber(1521);
                    ods.setServiceName("xe");
                    ods.setUser("daw1");
                    ods.setPassword("tiger");
                    ods.setDriverType("thin");
                    cnn = ods.getConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return cnn;
    }

    private Conexion() {
    }

    public static void cerrarConexion() {
        try {
            if (cnn != null && !cnn.isClosed()) {
                cnn.close();
            }
        } catch (SQLException ex) {
            
        }
        cnn = null;
    }

    public static void main(String[] args) {
        System.out.println(Conexion.getConnection());
    }
}
