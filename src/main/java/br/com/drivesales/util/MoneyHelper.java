/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import java.math.BigDecimal;

/**
 *
 * @author thomas
 */
public class MoneyHelper {
    public static BigDecimal convertFromBrazilian(String value){
        value = value.replaceAll("\\.", "");
        value = value.replaceAll(",", ".");
        return new BigDecimal(value);
        
    }
}
