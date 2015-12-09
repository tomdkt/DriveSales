/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.test.service;

import br.com.drivesales.domain.Company;
import br.com.drivesales.service.HeaderDelimitedFinder;
import br.com.drivesales.service.ProcessStream;
import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.util.HeaderTypes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thomas
 */
public class MovimentacoesTxtTest {
    private final Logger logger = LoggerFactory.getLogger(MovimentacoesTxtTest.class);
    
    HeaderDelimitedFinder headerFinder = new HeaderDelimitedFinder();
    ProcessStream<Company> processStream = new ProcessStream<>();
    
    
    @Test
    @Ignore
    public void shouldReadATextAndSeparatesItByADelimiter() throws UnsupportedEncodingException, IOException {
        logger.info("ENTER shouldReadATextAndSeparatesItByADelimiter");
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split(DelimitersEnum.TAB.getValue());
                for (String s : splited) {
                    logger.info("\t" + s);
                }
                logger.info("\t" + "END LINE");
            }
            br.close();
        }
        
//        assertEquals(result, expected);
        logger.info("\t" + "SUCESS");
        logger.info("END shouldReadATextAndSeparatesItByADelimiter");
    }
    
    @Test
    @Ignore
    public void shouldReadATextAndFindItsHeaders() throws UnsupportedEncodingException, IOException {
        logger.info("ENTER shouldReadATextAndSeparatesItByADelimiter");
        HeaderTypes headerType = null;
        
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        headerType = headerFinder.getTypeHeader(in, DelimitersEnum.TAB);
        logger.info("\t" + headerType);
        
        assertEquals(headerType, HeaderTypes.FILIAL_PERIODO_TOTAL);
        logger.info("\t" + "SUCESS");
        logger.info("END shouldReadATextAndFindItsHeaders");
    }
    
    @Test
//    @Ignore
    public void shouldReadATextAndFindItsHeadersAndDomain() throws UnsupportedEncodingException, IOException {
        logger.info("ENTER shouldReadATextAndFindItsHeadersAndDomain");
        
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        
        Assert.assertNotNull(company);
        logger.info("\t" + "SUCESS");
        logger.info("END shouldReadATextAndFindItsHeadersAndDomain");
    }
}
