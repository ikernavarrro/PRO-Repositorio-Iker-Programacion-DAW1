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
 * @author ichueca
 */
public class PruebaJDBC {

    public static void main(String[] args) {
        try {
            /*Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection cnn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                "daw1","tiger");*/
            Connection cnn = Conexion.getConnection();
            Statement stmt = cnn.createStatement();
            try {
                stmt.executeUpdate(
                """
                drop table alumnos
                """);
            } catch (SQLException ex){}
            try {
                stmt.executeUpdate(
                """
                drop table cursos
                """);
            } catch (SQLException ex){}
            
            stmt.executeUpdate(
                """
                create table cursos (
                    id number(5) CONSTRAINT PK_CURSOS PRIMARY KEY,
                    nombre varchar2(100),
                    tutora varchar2(100)
                )
                """);
            stmt.executeUpdate(
                """
                create table alumnos (
                    id number(5) CONSTRAINT PK_ALUMNOS PRIMARY KEY,
                    nombre varchar2(100),
                    apellidos varchar2(100),
                    fechaNacimiento date,
                    idCurso number(5) CONSTRAINT FK_CURSOS_ALUMNOS REFERENCES cursos
                )
                """);
            stmt.executeUpdate("""
                insert into cursos values(1,'DAW1','Marta Torre')               
                """);
            stmt.executeUpdate("""
                insert into cursos values(2,'DAW2','Lorena Moya')               
                """);
            stmt.executeUpdate("""
                insert into alumnos values(1,'Luis','López','28-07-2006',1)               
                """);
            stmt.executeUpdate("""
                insert into alumnos values(2,'Juan','Sanz','02-01-2006',1)               
                """);
            stmt.executeUpdate("""
                insert into alumnos values(3,'Luisa','Miguel','08-05-2005',2)               
                """);
            stmt.executeUpdate("""
                insert into alumnos values(4,'Ana','Pérez','30-09-2006',2)               
                """);
            stmt.close();
            cnn.close();
        } catch (SQLException ex) {
            System.getLogger(PruebaJDBC.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
