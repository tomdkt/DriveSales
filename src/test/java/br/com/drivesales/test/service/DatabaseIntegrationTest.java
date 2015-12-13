/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.test.service;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.domain.Company;
import br.com.drivesales.domain.Sale;
import br.com.drivesales.dto.BranchMostSoldDTO;
import br.com.drivesales.repository.BranchRepository;
import br.com.drivesales.repository.CompanyRepository;
import br.com.drivesales.repository.SaleRepository;
import br.com.drivesales.service.ProcessStream;
import br.com.drivesales.test.RepositoryConfiguration;
import br.com.drivesales.util.DelimitersEnum;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author thomas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
@TestPropertySource(locations="classpath:applicationtest.properties")
public class DatabaseIntegrationTest{
    
    private final Logger logger = LoggerFactory.getLogger(DatabaseIntegrationTest.class);
    
    ProcessStream<Company> processStream = new ProcessStream<>();
    
    private CompanyRepository companyRepository;
    private BranchRepository branchRepository;
    private SaleRepository saleRepository;
    
    
    
    
    @Test
    @Ignore
    public void shouldCreateCompanyInDatabaseMemory() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
        logger.info("shouldCreateDatabase");
        System.out.println("oi");
        
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        
        for (Branch b : company.getBranchs()) {
            logger.info("\tLOCATION: " + b.getLocation());
            for (Sale s : b.getSales()) {
                logger.info("\t\tTotal: " + s.getTotal());
                logger.info("\t\t\tInicialDate: " + s.getInicialDate());
                logger.info("\t\t\tFinalDate: " + s.getFinalDate());
            }
        }
        
        try {
            companyRepository.save(company);
        } catch (DataIntegrityViolationException data) {
            logger.error("Error de CONSTRAINT:", data);
        }catch (Exception e) {
            logger.error("Error on insert:", e);
        }
        
        
        
        Company companyTemp = companyRepository.findOne(1L);
        Branch branchTemp = branchRepository.findOne(1L);
        Sale saleTemp = saleRepository.findOne(1L);
        
        Assert.assertNotNull(companyTemp);
        Assert.assertNotNull(branchTemp);
        Assert.assertNotNull(saleTemp);
        
        logger.info("shouldCreateDatabase");
    }
    
    @Test
    public void shouldQueryBranchMostSell() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
        logger.info("shouldQueryBranchMostSell");
        System.out.println("oi");
        
        List<BranchMostSoldDTO> branchs = branchRepository.getMostSold();
        
        Assert.assertNotNull(branchs);
        Assert.assertTrue(!branchs.isEmpty());
        
        BranchMostSoldDTO mostSold = branchs.iterator().next();
        Assert.assertNotNull(mostSold);
        Assert.assertEquals(new BigDecimal("2466654.00"), mostSold.getTotal());
        
        logger.info("shouldQueryBranchMostSell");
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setBranchRepository(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Autowired
    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }
}
