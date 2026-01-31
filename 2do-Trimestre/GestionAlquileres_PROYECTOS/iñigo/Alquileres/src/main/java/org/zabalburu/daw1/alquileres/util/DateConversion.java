/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.zabalburu.daw1.alquileres.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author ichueca
 */
public class DateConversion {
    public static Date fromLocalDate(LocalDate ld){
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static LocalDate fromDate(Date d){
        return d.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
