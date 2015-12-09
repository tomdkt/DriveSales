/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import br.com.drivesales.domain.MonthPeriod;
import java.util.Date;

/**
 *
 * @author thomas
 */
public class MonthConverter {
    
    public MonthEnum getMonth(String month) {
        return MonthEnum.getValue(StringHelper.removeAccent(month).toLowerCase());
    }
    
    /**
     * Retorna a data inicial ou final de um mes dependendo dos parametros.
     */
    public Date getDateFromMonthEnum(MonthEnum month, MonthPeriodEnum monthPeriod, String year) {
        if(year == null){
            year = DateParser.getCurrentYear();
        }
        Date date = null;
        switch (month) {
            case JANEIRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/01" + year);
                break;
            case FEVEREIRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/02" + year);
                break;
            case MARCO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/03" + year);
                break;
            case ABRIL:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/04" + year);
                break;
            case MAIO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/05" + year);
                break;
            case JUNHO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/06" + year);
                break;
            case JULHO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/07" + year);
                break;
            case AGOSTO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/08" + year);
                break;
            case SETEMBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/09" + year);
                break;
            case OUTUBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/10" + year);
                break;
            case NOVEMBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/11" + year);
                break;
            case DEZEMBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/12" + year);
                break;
        }
        
        if (monthPeriod.equals(monthPeriod.FINAL)) {
            date = DateParser.getLastDayofMonth(date);
        }
        
        return date;
    }
    
    /**
     * Retorna {@link  br.com.drivesales.domain.MonthPeriod} com data inicial e final populados dependendo do mes({@link  br.com.drivesales.util.MonthEnum}).
     * @param month
     * @param year
     * @return 
     */
    public MonthPeriod getMonthPeriodFromMonthEnum(MonthEnum month,  String year) {
        if(year == null){
            year = DateParser.getCurrentYear();
        }
        Date date = null;
        switch (month) {
            case JANEIRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/01/" + year);
                break;
            case FEVEREIRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/02/" + year);
                break;
            case MARCO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/03/" + year);
                break;
            case ABRIL:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/04/" + year);
                break;
            case MAIO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/05/" + year);
                break;
            case JUNHO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/06/" + year);
                break;
            case JULHO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/07/" + year);
                break;
            case AGOSTO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/08/" + year);
                break;
            case SETEMBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/09/" + year);
                break;
            case OUTUBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/10/" + year);
                break;
            case NOVEMBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/11/" + year);
                break;
            case DEZEMBRO:
                date = DateParser.parseStringToDate_dd_MM_yyyy("01/12/" + year);
                break;
        }
        MonthPeriod m = new MonthPeriod();
        m.setInicialDate(date);
        m.setFinalDate(DateParser.getLastDayofMonth(date));
        
        return m;
    }
}
