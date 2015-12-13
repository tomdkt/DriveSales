/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.service;

import br.com.drivesales.exception.LineErrorException;
import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.util.HeaderTypes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author thomas
 */
@Component
public class ProcessStream<T> {
    
    private final Logger logger = LoggerFactory.getLogger(ProcessStream.class);
    private final HeaderDelimitedFinder headerFinder;

    @Autowired
    public ProcessStream(HeaderDelimitedFinder headerFinder) {
        this.headerFinder = headerFinder;
    }
    
    public T parseToEntity(InputStreamReader in, DelimitersEnum delimiter) throws IOException, InstantiationException, IllegalAccessException{
        logger.debug("ENTER parseToEntity");
        FilialPeriodoTotalProcess filialProcess = null;
        
        String currentLine = "";
        Long lineNumber = 1L;
        try (BufferedReader br = new BufferedReader(in)) {
            String header = br.readLine();
            lineNumber++;
            
            HeaderTypes headerType = headerFinder.getTypeHeader(header, DelimitersEnum.TAB);
            filialProcess = new FilialPeriodoTotalProcess(headerType,delimiter);
            
            while ((currentLine = br.readLine()) != null) {
                filialProcess.parse(currentLine);
                lineNumber++;
            }
            br.close();
        }catch(Exception e ){
            logger.error("Erro ao processar linha: " + currentLine);
            throw new LineErrorException(currentLine, lineNumber, e);
        }
        
        logger.debug("EXIT parseToEntity");
        return (T) filialProcess.build();
    }
    
    public T parseToEntity(String line, HeaderTypes headerType, DelimitersEnum delimiter) throws IOException, InstantiationException, IllegalAccessException{
        logger.debug("ENTER parseToEntity");
        FilialPeriodoTotalProcess filialProcess = null;
        
        String currentLine = line;
        Long lineNumber = 1L;
        try {
            filialProcess = new FilialPeriodoTotalProcess(headerType, delimiter);

            filialProcess.parse(currentLine);
            lineNumber++;
        }catch(Exception e ){
            logger.error("Error process line: \"" + currentLine + "\"");
            throw new LineErrorException(currentLine, lineNumber, e);
        }
        
        logger.debug("EXIT parseToEntity");
        return (T) filialProcess.build();
    }
    
}
