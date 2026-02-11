/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.zabalburu.daw1.actividad25.corriente_datos.dao;

import java.util.List;
import org.zabalburu.daw1.actividad25.corriente_datos.modelo.Envio;

/**
 *
 * @author Iker Navarro Pérez
 */
public interface EnvioDAO {
    public void addEnvio(Envio nuevo);
    public Envio getEnvio(Integer idEnvio);
    public List<Envio> getEnvios();
    public void modifyEnvio(Envio modificar);
    public void removeEnvio(Integer idEnvio);
}
