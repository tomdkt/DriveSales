/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.test.service;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.domain.Company;
import br.com.drivesales.repository.BranchRepository;
import br.com.drivesales.repository.CompanyRepository;
import br.com.drivesales.repository.MonthPeriodRepository;
import br.com.drivesales.repository.SaleRepository;
import br.com.drivesales.test.RepositoryConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author thomas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
@TestPropertySource(locations="classpath:applicationtest.properties")
public class DatabaseIntegrationTest{
    
    private final Logger logger = LoggerFactory.getLogger(DatabaseIntegrationTest.class);
    
    private CompanyRepository companyRepository;
    private BranchRepository branchRepository;
    private SaleRepository saleRepository;
    private MonthPeriodRepository monthPeriodRepository;
    
    
    
    @Test
    public void shouldCreateDatabase(){
        logger.info("shouldCreateDatabase");
        System.out.println("oi");
        
        Company company = new Company();
        company.setName("teste Fuck");
        
        Branch branch = new Branch();
        branch.setLocation("caceta");
        company.getBranchs().add(branch);
        
        
        companyRepository.save(company);
        
        Company temp = companyRepository.findOne(1L);
        Branch b = branchRepository.findOne(1L);
        
        Assert.assertNotNull(temp);
        Assert.assertNotNull(temp.getBranchs());
        Assert.assertNotNull(temp.getBranchs().iterator().next());
        
        logger.info("shouldCreateDatabase");
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

    @Autowired
    public void setMonthPeriodRepository(MonthPeriodRepository monthPeriodRepository) {
        this.monthPeriodRepository = monthPeriodRepository;
    }
}
