/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.service;

import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.util.HeaderTypes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thomas
 */
public class HeaderDelimitedFinder {
    
    private final Logger logger = LoggerFactory.getLogger(HeaderDelimitedFinder.class);
    
    
    public HeaderTypes getTypeHeader(String firstLine, DelimitersEnum delimiter){
        if(firstLine == null){
            return null;
        }
        
        String header = firstLine.replaceAll(delimiter.getValue(), DelimitersEnum.JOKER.getValue()).toUpperCase();        
        return HeaderTypes.valueOf(header);
    }
    
    public HeaderTypes getTypeHeader(InputStreamReader in, DelimitersEnum delimiter) throws IOException{
        try (BufferedReader br = new BufferedReader(in)) {
            String header = br.readLine();
            return getTypeHeader(header, delimiter);
        }
    }
}
