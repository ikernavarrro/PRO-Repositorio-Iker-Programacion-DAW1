/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.util;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.datasource.impl.OracleDataSource;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ConexionBBDD {

    private ConexionBBDD() {
    }

    private static Connection cnn;

    public static Connection getConnection() {
        try {
            if (cnn == null || cnn.isClosed()) {
                OracleDataSource ods = new OracleDataSource();
                ods.setServerName("localHost");
                ods.setPortNumber(1521);
                ods.setServiceName("xe");
                ods.setUser("gestock");
                ods.setPassword("gestock");
                ods.setDriverType("thin");
                cnn = ods.getConnection();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al conectarse con la Base de Datos.", ex);
        }
        return cnn;
    }
    
    public static void closeConnection() {
        try {
            if (cnn != null && !cnn.isClosed()) {
                cnn.close();
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        cnn = null;
    }
}
