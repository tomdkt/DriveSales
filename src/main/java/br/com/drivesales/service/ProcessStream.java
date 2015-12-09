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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thomas
 */
public class ProcessStream<T> {
    
    HeaderDelimitedFinder headerFinder = new HeaderDelimitedFinder();
    
    private final Logger logger = LoggerFactory.getLogger(ProcessStream.class);
    
    
    public T parseToEntity(InputStreamReader in, DelimitersEnum delimiter) throws IOException{
        logger.debug("ENTER parseToEntity");
        FilialPeriodoTotalProcess filialProcess = null;
        
        try (BufferedReader br = new BufferedReader(in)) {
            String header = br.readLine();
            
            HeaderTypes headerType = headerFinder.getTypeHeader(header, DelimitersEnum.TAB);
            filialProcess = new FilialPeriodoTotalProcess(headerType,delimiter);
            
            String line;
            while ((line = br.readLine()) != null) {
                filialProcess.parse(line);
            }
            br.close();
        }
        
        logger.debug("EXIT parseToEntity");
        return (T) filialProcess.build();
    }
    
}
