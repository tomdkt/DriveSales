/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
public class DateParser {
    private static final SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
    
    public final static Locale BRAZIL = new Locale("pt","BR");
    
    public static Date parseStringToDate_dd_MM_yyyy(String date) {
        try {
            return df2.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getCurrentYear() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return String.valueOf(year);
    }
    
    public static String getMonthNameFromBrazil(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "MMMM", BRAZIL);
        return dateFormat.format( date );
    }

    public static Date trimDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);

        return calendar.getTime();
    }
    
    public static Date getLastDayofMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return trimDate(c.getTime());
    }
}
