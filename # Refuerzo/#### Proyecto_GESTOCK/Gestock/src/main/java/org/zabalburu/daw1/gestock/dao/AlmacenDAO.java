/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.gestock.dao;

import java.util.List;
import java.util.Optional;
import org.zabalburu.daw1.gestock.excepciones.AlmacenNoEncontradoException;
import org.zabalburu.daw1.gestock.excepciones.EliminarAlmacenConProductosException;
import org.zabalburu.daw1.gestock.modelo.Almacen;

/**
 *
 * @author Iker Navarro Pérez+
 */
public interface AlmacenDAO {

    public void addAlmacen(Almacen nuevo);

    public Optional<Almacen> getAlmacen(Integer idAlmacen);

    public List<Almacen> getAlmacenes();

    public List<Almacen> getAlmacenesBusqueda(String textoBusqueda);

    public void modifyAlmacen(Almacen modificar) throws AlmacenNoEncontradoException;

    public void removeAlmacen(Integer idAlmacen) throws EliminarAlmacenConProductosException, AlmacenNoEncontradoException;
}
