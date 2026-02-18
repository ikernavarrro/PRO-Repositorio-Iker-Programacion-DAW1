/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.pruebajdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import javax.swing.JOptionPane;
import org.zabalburu.daw1.pruebajdbc.util.Conexion;

/**
 *
 * @author ichueca
 */
public class ConsultarDatos {
    public static void main(String[] args) {
        DateFormat df = DateFormat.getDateInstance();
        Connection cnn = Conexion.getConnection();
        try {
            Statement stmt = cnn.createStatement();
            ResultSet rstCursos = stmt.executeQuery(
            """
            Select * From Cursos
            Order By nombre
            """);
            String listado = 
                """
                <html>
                  <body>
                    <h2>Listado de Cursos</h2>
                    
                """;
            int numCursos = 0;
            while (rstCursos.next()){ // Accede al siguiente registro (true)
                // Estamos en un curso
                listado +=
                    """
                    <h3>%s (%s)</h3>
                    """.formatted(
                       rstCursos.getString("nombre"),
                       rstCursos.getString("tutora")
                    );
                // Añadir los alumnos del curso
                ResultSet rstAlumnosCurso = cnn
                        .createStatement()
                        .executeQuery(
                    """
                    Select * 
                    From Alumnos
                    Where idcurso = %d
                    Order By nombre
                    """.formatted(rstCursos.getInt("id"))
                    );
                
                int numAlumnos = 0;
                numCursos++;
                if (rstAlumnosCurso.next()){
                    listado += """
                    <table border=1 bgcolor='white'>
                       <tr><td>Id</td><td>Nombre</td><td>Fecha Nac.</td></tr>
                    """;
                    //while (rstAlumnosCurso.next()){
                    do {
                        // Tenemos un alumno del curso
                        listado +=
                            """
                            <tr><td>%d</td>%s</td>%s</td></tr>
                            """
                            .formatted(
                                rstAlumnosCurso.getInt("id"),
                                rstAlumnosCurso.getString("nombre"),
                                //rstAlumnosCurso.getDate("fechaNacimiento").toString()
                                df.format(rstAlumnosCurso.getDate("fechaNacimiento"))
                            );
                    } while (rstAlumnosCurso.next());
                    numAlumnos++;
                    listado += 
                        """
                        </table>
                        <h5>Total Alumnos : %d</h5>
                        """.formatted(numAlumnos);
       
                } else {
                    listado += "No hay alumnos en este curso";
                }
                rstAlumnosCurso.close();
            }
            listado += 
                """
                <h3>Total Cursos: %d</h3>
                </body>
                </html>
                """.formatted(numCursos);
            JOptionPane.showMessageDialog(null, 
                    listado,
                    "Listado de Cursos",
                    JOptionPane.PLAIN_MESSAGE);
            rstCursos.close();
            stmt.close();
            Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.getLogger(ConsultarDatos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
