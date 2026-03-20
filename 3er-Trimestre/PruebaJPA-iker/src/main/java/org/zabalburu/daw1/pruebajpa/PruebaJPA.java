/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.zabalburu.daw1.pruebajpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import org.zabalburu.daw1.pruebajpa.modelo.Tarea;
import org.zabalburu.daw1.pruebajpa.modelo.Usuario;

/**
 *
 * @author Iker Navarro Pérez
 */
public class PruebaJPA {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("UsuariosPU").createEntityManager();
        em.getTransaction().begin();
        /* CREAR (Persist)
        Usuario u = new Usuario();
        u.setNombre("Iñigo");
        u.setApellidos("Chueca");
        u.setEmail("ichueca@zabalburu.org");
        u.setFechaAlta(LocalDate.now());
        u.setSueldo(5400.0);
        em.persist(u);
        em.getTransaction().commit();*/

 /*RECUPERAR POR ID (find)
        Usuario u = em.find(Usuario.class, 1);
        // u está GESTIONADO (Mientras siga en la transacción)
        System.out.println(u);
        u.setSueldo(1500.0);
        em.merge(u); //Modifica el usuario en base al ID
        em.getTransaction().commit();*/
 /*ELIMINAR (Tiene que estar gestionado)
        Usuario eliminar = em.find(Usuario.class, 1);
        eliminar.setId(1);
        em.remove(em);
        em.getTransaction().commit();*/
 /*PRUEBAS*/
 /*Usuario u1 = new Usuario();
        u1.setNombre("María");
        u1.setApellidos("García López");
        u1.setEmail("mgarcia@zabalburu.org");
        u1.setFechaAlta(LocalDate.now());
        u1.setSueldo(4800.0);
        em.persist(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Carlos");
        u2.setApellidos("Martínez Ruiz");
        u2.setEmail("cmartinez@zabalburu.org");
        u2.setFechaAlta(LocalDate.now());
        u2.setSueldo(5100.0);
        em.persist(u2);

        Usuario u3 = new Usuario();
        u3.setNombre("Lucía");
        u3.setApellidos("Fernández Pérez");
        u3.setEmail("lfernandez@zabalburu.org");
        u3.setFechaAlta(LocalDate.now());
        u3.setSueldo(6200.0);
        em.persist(u3);

        Usuario u4 = new Usuario();
        u4.setNombre("Andoni");
        u4.setApellidos("Etxebarria Aguirre");
        u4.setEmail("aetxebarria@zabalburu.org");
        u4.setFechaAlta(LocalDate.now());
        u4.setSueldo(4950.0);
        em.persist(u4);
        //em.getTransaction().commit();
        /*CONSULTAS JPQL*/
        //Todos los usuarios (con o sin condición)
        /*Query q = em.createQuery("""
                                 SELECT u
                                 FROM Usuario u
                                 """);
        List<Usuario> usuarios = q.getResultList();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
            
        }
        em.getTransaction().commit();*/
        //Consultas con un único resultado (Parámetros)
        Query qEmail = em.createQuery("""
                                      SELECT u
                                      FROM Usuario u
                                      WHERE u.email = :email
                                      """, Usuario.class);
        qEmail.setParameter("email", "mgarcia@zabalburu.org");
        Usuario u = null;
        /*try {
            u = (Usuario) qEmail.getSingleResult();
        } catch (NoResultException ex) {
        }*/
        u = (Usuario) qEmail.getSingleResultOrNull();
        System.out.println(u);
        //List<Usuario> usuarios = (List<Usuario>) em.createNamedQuery("usuario.buscarTodos", Usuario.class);

        // Tareas de María García López
        /*Tarea t1 = new Tarea();
        t1.setTitulo("Revisión nóminas marzo");
        t1.setDescripcion("Revisar y validar las nóminas del personal para el mes de marzo");
        t1.setFecha(LocalDate.now());
        t1.setFinalizada(false);
        t1.setUsuario(u1);
        em.persist(t1);

        Tarea t2 = new Tarea();
        t2.setTitulo("Informe trimestral RRHH");
        t2.setDescripcion("Elaborar el informe de recursos humanos del primer trimestre");
        t2.setFecha(LocalDate.now().minusDays(3));
        t2.setFinalizada(true);
        t2.setUsuario(u1);
        em.persist(t2);

        Tarea t3 = new Tarea();
        t3.setTitulo("Entrevistas candidatos backend");
        t3.setDescripcion("Realizar entrevistas para cubrir la vacante de desarrollador backend");
        t3.setFecha(LocalDate.now().plusDays(2));
        t3.setFinalizada(false);
        t3.setUsuario(u1);
        em.persist(t3);

        Tarea t4 = new Tarea();
        t4.setTitulo("Actualizar políticas de empresa");
        t4.setDescripcion("Revisar y actualizar el manual de políticas internas de la empresa");
        t4.setFecha(LocalDate.now().minusDays(7));
        t4.setFinalizada(true);
        t4.setUsuario(u1);
        em.persist(t4);

// Tareas de Carlos Martínez Ruiz
        Tarea t5 = new Tarea();
        t5.setTitulo("Despliegue microservicio pagos");
        t5.setDescripcion("Desplegar la nueva versión del microservicio de pagos en producción");
        t5.setFecha(LocalDate.now().plusDays(1));
        t5.setFinalizada(false);
        t5.setUsuario(u2);
        em.persist(t5);

        Tarea t6 = new Tarea();
        t6.setTitulo("Migración base de datos");
        t6.setDescripcion("Ejecutar el script de migración de Oracle 19c a Oracle 21c");
        t6.setFecha(LocalDate.now().minusDays(5));
        t6.setFinalizada(true);
        t6.setUsuario(u2);
        em.persist(t6);

        Tarea t7 = new Tarea();
        t7.setTitulo("Revisión código pull request");
        t7.setDescripcion("Revisar y aprobar los pull requests pendientes del sprint actual");
        t7.setFecha(LocalDate.now());
        t7.setFinalizada(false);
        t7.setUsuario(u2);
        em.persist(t7);

        Tarea t8 = new Tarea();
        t8.setTitulo("Documentar API REST");
        t8.setDescripcion("Completar la documentación Swagger de los endpoints del módulo de facturación");
        t8.setFecha(LocalDate.now().plusDays(4));
        t8.setFinalizada(false);
        t8.setUsuario(u2);
        em.persist(t8);

// Tareas de Lucía Fernández Pérez
        Tarea t9 = new Tarea();
        t9.setTitulo("Presentación cliente Bilbao");
        t9.setDescripcion("Preparar y presentar la propuesta comercial al cliente de Bilbao");
        t9.setFecha(LocalDate.now().plusDays(3));
        t9.setFinalizada(false);
        t9.setUsuario(u3);
        em.persist(t9);

        Tarea t10 = new Tarea();
        t10.setTitulo("Análisis competencia Q1");
        t10.setDescripcion("Analizar el posicionamiento de la competencia durante el primer trimestre");
        t10.setFecha(LocalDate.now().minusDays(10));
        t10.setFinalizada(true);
        t10.setUsuario(u3);
        em.persist(t10);

        Tarea t11 = new Tarea();
        t11.setTitulo("Campaña email marketing");
        t11.setDescripcion("Diseñar y lanzar la campaña de email marketing para el producto nuevo");
        t11.setFecha(LocalDate.now());
        t11.setFinalizada(false);
        t11.setUsuario(u3);
        em.persist(t11);

        Tarea t12 = new Tarea();
        t12.setTitulo("Renovación contrato proveedor");
        t12.setDescripcion("Negociar y firmar la renovación del contrato con el proveedor logístico");
        t12.setFecha(LocalDate.now().minusDays(2));
        t12.setFinalizada(true);
        t12.setUsuario(u3);
        em.persist(t12);

// Tareas de Andoni Etxebarria Aguirre
        Tarea t13 = new Tarea();
        t13.setTitulo("Auditoría de seguridad");
        t13.setDescripcion("Realizar la auditoría de seguridad semestral de los sistemas internos");
        t13.setFecha(LocalDate.now().plusDays(5));
        t13.setFinalizada(false);
        t13.setUsuario(u4);
        em.persist(t13);

        Tarea t14 = new Tarea();
        t14.setTitulo("Configurar VPN oficina Vitoria");
        t14.setDescripcion("Instalar y configurar la VPN corporativa en la nueva oficina de Vitoria");
        t14.setFecha(LocalDate.now().minusDays(1));
        t14.setFinalizada(true);
        t14.setUsuario(u4);
        em.persist(t14);

        Tarea t15 = new Tarea();
        t15.setTitulo("Actualizar certificados SSL");
        t15.setDescripcion("Renovar los certificados SSL de los servidores web antes de su expiración");
        t15.setFecha(LocalDate.now().plusDays(7));
        t15.setFinalizada(false);
        t15.setUsuario(u4);
        em.persist(t15);

        Tarea t16 = new Tarea();
        t16.setTitulo("Backup sistemas críticos");
        t16.setDescripcion("Verificar y documentar el estado de los backups de los sistemas críticos");
        t16.setFecha(LocalDate.now().minusDays(4));
        t16.setFinalizada(true);
        t16.setUsuario(u4);
        em.persist(t16);*/
        em.getTransaction().commit();
    }
}
