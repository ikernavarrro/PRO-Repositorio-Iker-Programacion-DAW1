/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.zabalburu.daw1.pruebajdbc.util.Conexion;

/**
 *
 * @author Iker Navarro Pérez
 */
public class ConsultarDatos {

    public static void main(String[] args) {
        Connection cnn = Conexion.getConnection();
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rstCursos;
            rstCursos = stmt.executeQuery("""
                                          SELECT * FROM CURSOS
                                          ORDER BY nombreCurso
                                          """);
            String listado = """
                     <html>
                        <body>
                            <h2>Listado de Cursos</h2>
                     """;
            int numCursos = 0;
            while (rstCursos.next()) {
                listado += """
                                       <h3>%s (%s)</h3>
                                       """.formatted(
                        rstCursos.getString("nombrecurso"),
                        rstCursos.getString("tutora"));
                //Añadimos los alumnos
                ResultSet rstAlumnosCurso = cnn
                        .createStatement()
                        .executeQuery("""
                                      SELECT * 
                                      FROM ALUMNOS
                                      WHERE idcurso = %d
                                      ORDER BY nombre
                                      """.formatted(rstCursos.getInt("id")));
                listado += """
                           <table border= 1 bgcolor='white'>
                                <tr><th>ID</th><th>Nombre</th><th>Fecha Nacimiento</th></tr>
                           """;
                int numAlumnos = 0;
                while (rstAlumnosCurso.next()) {                    
                    listado +="""
                              <tr><td>%d</td><td>%s</td><td>%s</td></tr>
                              """.formatted(rstAlumnosCurso.getInt("id"),
                                      rstAlumnosCurso.getString("nombre"),
                                      rstAlumnosCurso.getDate("fechanacimiento").toString());
                    numAlumnos++;                          
                }
                numCursos++;
                listado += """
                           </table>
                           <h4>Total Alumnos: %d</h4>
                           """.formatted(numAlumnos);
                rstAlumnosCurso.close();
            }
            listado += """
                       <h3>Total Cursos: %d</h3>
                       </body>
                       </html>
                       """.formatted(numCursos);
            JOptionPane.showMessageDialog(null, listado, "Listado de Cursos", JOptionPane.PLAIN_MESSAGE);
            rstCursos.close();
            stmt.close();
            Conexion.closeConnection();
        } catch (SQLException ex) {
            System.getLogger(ConsultarDatos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
/*<table border= 1 bgcolor='white'>
                                    <tr><th>ID</th><th>Nombre</th><th>Tutor/a</th></tr>*/
