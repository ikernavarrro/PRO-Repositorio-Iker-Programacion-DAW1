/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.gestock.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Iker Navarro Pérez
 */
public class RestaurarBBDD {
    public static void main(String[] args) {
        Connection cnn = null;
        try {
            cnn = ConexionBBDD.getConnection();
            Statement stmt = cnn.createStatement();
            try {
                stmt.executeUpdate("DROP TABLE PRODUCTO");
            } catch (SQLException ex) {
            }
            try {
                stmt.executeUpdate("DROP TABLE ALMACEN");
            } catch (SQLException ex) {
            }
            cnn.setAutoCommit(false);
            stmt.executeUpdate("""
                               CREATE TABLE ALMACEN (
                               id_almacen NUMBER(3) CONSTRAINT PK_ALMACEN PRIMARY KEY,
                               nombre VARCHAR2(100),
                               direccion VARCHAR2(200),
                               capacidad_maxima NUMBER(5) CONSTRAINT CK_capacidad_maxima CHECK(capacidad_maxima > 0))
                               """);
            stmt.executeUpdate("""
                               CREATE TABLE PRODUCTO(
                               id_producto NUMBER(4) CONSTRAINT PK_PRODUCTO PRIMARY KEY,
                               codigo_barras VARCHAR2(13) CONSTRAINT NN_codigo_barras NOT NULL CONSTRAINT U_codigo_barras UNIQUE,
                               descripcion VARCHAR2(200),
                               marca VARCHAR2(25),
                               categoria VARCHAR2(25) CONSTRAINT CK_categoria CHECK(LOWER(categoria) IN ('electronica', 'hogar', 'ropa',
                                                                                                       'juguetes', 'deportes', 'oficina', 
                                                                                                       'jardin', 'mascotas', 'salud', 'belleza',
                                                                                                       'comida', 'bebida', 'otros')),
                               precio NUMBER(7,2) CONSTRAINT CK_precio CHECK(precio > 0),
                               stock NUMBER(5) CONSTRAINT CK_stock CHECK(stock >= 0),
                               fecha_entrada DATE,
                               id_almacen NUMBER(3) CONSTRAINT NN_id_almacen NOT NULL CONSTRAINT FK_PRODUCTO_ALMACEN REFERENCES ALMACEN(id_almacen)
                               )
                               """);
            cnn.commit();
            cnn.setAutoCommit(true);
            System.out.println("Base de datos Inicializada con Éxito!");
        } catch (SQLException ex) {
            System.getLogger(RestaurarBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            try {
                cnn.rollback();
            } catch (SQLException ex1) {
            }
        }
    }
}
