/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.test.service;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.domain.Company;
import br.com.drivesales.domain.Sale;
import br.com.drivesales.dto.LocationAndTotalDTO;
import br.com.drivesales.dto.MonthTopSalles;
import br.com.drivesales.repository.BranchRepository;
import br.com.drivesales.repository.CompanyRepository;
import br.com.drivesales.repository.SaleRepository;
import br.com.drivesales.service.ProcessStream;
import br.com.drivesales.test.BaseTest;
import br.com.drivesales.util.DelimitersEnum;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author thomas
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
//@TestPropertySource(locations="classpath:applicationtest.properties")
public class DatabaseIntegrationTest extends BaseTest{
    
    private final Logger logger = LoggerFactory.getLogger(DatabaseIntegrationTest.class);
    
    private ProcessStream<Company> processStream;
    private CompanyRepository companyRepository;
    private BranchRepository branchRepository;
    private SaleRepository saleRepository;

    @Autowired
    public void setProcessStream(ProcessStream<Company> processStream) {
        this.processStream = processStream;
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
    
    
    @Test
    public void shouldCreateCompanyInDatabaseMemory() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
        logger.info("shouldCreateDatabase");
        
        this.companyRepository.deleteAll();
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
        this.companyRepository.deleteAll();
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        this.companyRepository.save(company);
        
        List<LocationAndTotalDTO> branchs = branchRepository.getMostSold();
        
        Assert.assertNotNull(branchs);
        Assert.assertTrue(!branchs.isEmpty());
        
        LocationAndTotalDTO mostSold = branchs.iterator().next();
        Assert.assertNotNull(mostSold);
        Assert.assertEquals(new BigDecimal("2466654.00"), mostSold.getTotal());
        
        logger.info("shouldQueryBranchMostSell");
    }
    
    @Test
    public void shouldMonthWithMoreSales() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
        logger.info("shouldMonthWithMoreSales");
        this.companyRepository.deleteAll();
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        this.companyRepository.save(company);
        
        List<MonthTopSalles> topSalles = this.saleRepository.getMonthWithMoreSalesDTO();
        
        Assert.assertNotNull(topSalles);
        Assert.assertTrue(!topSalles.isEmpty());
        
        MonthTopSalles topSallesMonth = topSalles.iterator().next();
        Assert.assertNotNull(topSallesMonth);
        Assert.assertEquals(new BigDecimal("3250085.00"), topSallesMonth.getTotalMonth());
        
        
        logger.info("shouldMonthWithMoreSales");
    }
    
    @Test
    public void shouldGetBranchMoreIncrease() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
        logger.info("shouldMonthWithMoreSales");
        this.companyRepository.deleteAll();
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        this.companyRepository.save(company);
        
        List<LocationAndTotalDTO> branchsMoreIncrease = this.branchRepository.getBranchMoreIncrease();
        
        Assert.assertNotNull(branchsMoreIncrease);
        Assert.assertTrue(!branchsMoreIncrease.isEmpty());
        
        LocationAndTotalDTO mostIncrease = branchsMoreIncrease.iterator().next();
        Assert.assertNotNull(mostIncrease);
        Assert.assertEquals("Belo Horizonte", mostIncrease.getLocation());
        Assert.assertEquals(new BigDecimal("2455586.00"), mostIncrease.getTotal());
        
        logger.info("\tMOST INCREASE: " + mostIncrease.getLocation());
        
        logger.info("shouldMonthWithMoreSales");
    }
    
    @Test
    public void shouldGetBranchLessIncrease() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException{
        logger.info("shouldMonthWithMoreSales");
        this.companyRepository.deleteAll();
        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        this.companyRepository.save(company);
        
        List<LocationAndTotalDTO> branchsLessIncrease = this.branchRepository.getBranchMoreIncrease();
        
        Assert.assertNotNull(branchsLessIncrease);
        Assert.assertTrue(!branchsLessIncrease.isEmpty());
        
        LocationAndTotalDTO mostIncrease = branchsLessIncrease.get(branchsLessIncrease.size() -1);
        Assert.assertNotNull(mostIncrease);
        Assert.assertEquals("São Paulo", mostIncrease.getLocation());
        Assert.assertEquals(new BigDecimal("14558.00"), mostIncrease.getTotal());
        
        logger.info("\tMOST INCREASE: " + mostIncrease.getLocation());
        
        logger.info("shouldMonthWithMoreSales");
    }
}
