/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.pruebajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.zabalburu.daw1.pruebajdbc.util.Conexion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PruebaJDBC {

    public static void main(String[] args) {
        try {
            /*Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection cnn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "daw1", "tiger");*/
            Connection cnn = Conexion.getConnection();
            Statement stmt = cnn.createStatement();
            
            try {
                stmt.executeUpdate("""
                               DROP TABLE ALUMNOS
                               """);
            } catch (SQLException s) {
            }
            try {
                stmt.executeUpdate("""
                               DROP TABLE CURSOS
                               """);
            } catch (SQLException s) {
            }
            stmt.executeUpdate("""
                               CREATE TABLE CURSOS(
                               id NUMBER(5) CONSTRAINT PK_CURSOS PRIMARY KEY,
                               nombreCurso VARCHAR2(100),
                               tutora VARCHAR2(100)
                               )
                               """);
            stmt.executeUpdate("""
                               CREATE TABLE ALUMNOS(
                               id NUMBER(5) CONSTRAINT PK_ALUMNOS PRIMARY KEY,
                               nombre VARCHAR2(100),
                               apellidos VARCHAR2(100),
                               fechaNacimiento DATE,
                               idCurso NUMBER(5) CONSTRAINT FK_CURSOS_ALUMNOS REFERENCES CURSOS(id)
                               )
                               """);
            stmt.executeUpdate("""
                               INSERT INTO CURSOS VALUES (1,'DAW1','Marta Torre')
                               """);
            stmt.executeUpdate("""
                               INSERT INTO CURSOS VALUES (2,'DAW2','Lorena Moya')
                               """);
            stmt.executeUpdate("""
                               INSERT INTO ALUMNOS VALUES (1,'Iker','Navarro Pérez','12-11-2003', 1)
                               """);
            stmt.executeUpdate("""
                               INSERT INTO ALUMNOS VALUES (2,'Unax','Carazo Bergaetxea','08-06-2005', 1)
                               """);
            stmt.executeUpdate("""
                               INSERT INTO ALUMNOS VALUES (3,'Iñigo','Chueca','03-11-2000', 2)
                               """);
            stmt.close();
            cnn.close();
        } /*catch (ClassNotFoundException ex) {
            System.getLogger(PruebaJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }*/ catch (SQLException ex) {
            System.getLogger(PruebaJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
