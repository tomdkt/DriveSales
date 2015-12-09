/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author thomas
 */
public class StringHelper {
    
    public static String removeAccent(String str) {
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalize).replaceAll("");
    }
}
