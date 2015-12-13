/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.controller;

import br.com.drivesales.domain.Company;
import br.com.drivesales.dto.LocationAndTotalDTO;
import br.com.drivesales.dto.MonthTopSalles;
import br.com.drivesales.repository.BranchRepository;
import br.com.drivesales.repository.CompanyRepository;
import br.com.drivesales.repository.SaleRepository;
import br.com.drivesales.service.ProcessStream;
import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.vo.SummaryVO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Thomas Daniel Kaneko Teixeira(tomdkt)
 */
@Controller
public class IndexController {
    
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    private final ProcessStream<Company> processStream;
    private final CompanyRepository companyRepository;
    private final BranchRepository branchRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public IndexController(ProcessStream<Company> processStream, CompanyRepository companyRepository, BranchRepository branchRepository, SaleRepository saleRepository) {
        this.processStream = processStream;
        this.companyRepository = companyRepository;
        this.branchRepository = branchRepository;
        this.saleRepository = saleRepository;
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("tempFile") MultipartFile file) {
        if (!file.isEmpty()) {
            String name = file.getName();
            try {
                
                this.companyRepository.deleteAll(); //clean database, because we dont have logic business set
                
                InputStreamReader in = new InputStreamReader(file.getInputStream(), "latin1");
                Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
                
                this.companyRepository.save(company);
                
                SummaryVO summary = this.getSummaryVO();
                
                
                return summary.toString();
            } catch (IOException | InstantiationException | IllegalAccessException e) {
                logger.error("Error in handleFileUpload", e);
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "The selected file was empty and could not be uploaded.";
        }
    }
    
    private SummaryVO getSummaryVO(){
        SummaryVO summary = new SummaryVO();
        
        List<LocationAndTotalDTO> branchs = this.branchRepository.getMostSold();
        if(branchs != null && !branchs.isEmpty()){
            summary.setBranchMostSold(branchs.iterator().next());
        }
        List<MonthTopSalles> topSalles = this.saleRepository.getMonthWithMoreSalesDTO();
        if(topSalles != null && !topSalles.isEmpty()){
            summary.setTopSalles(topSalles.iterator().next());
        }

        //TODO improve query
        List<LocationAndTotalDTO> branchsMoreIncrease = this.branchRepository.getBranchMoreIncrease();
        if(branchsMoreIncrease != null && !branchsMoreIncrease.isEmpty()){
            summary.setBranchMoreIncrease(branchsMoreIncrease.get(0));
            summary.setBranchLessIncrease(branchsMoreIncrease.get(branchsMoreIncrease.size() - 1));
        }
        
        return summary;
    }
}
