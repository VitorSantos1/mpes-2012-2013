/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.support;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author vitorsantos
 */
public class CalendarSupport {

    public static String writeCurrentTime() {
        Calendar cal = new GregorianCalendar();
        return Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + 
                "/" +
                Integer.toString((cal.get(Calendar.MONTH) + 1)) +
                "/" + 
                Integer.toString(cal.get(Calendar.YEAR)) +
                " " +
                Integer.toString(cal.get(Calendar.HOUR_OF_DAY)) +
                ":" +
                Integer.toString(cal.get(Calendar.MINUTE));
    }
    
}
