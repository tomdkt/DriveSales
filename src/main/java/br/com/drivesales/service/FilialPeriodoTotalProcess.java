/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.service;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.domain.Company;
import br.com.drivesales.domain.Sale;
import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.util.HeaderTypes;
import br.com.drivesales.util.MoneyHelper;
import br.com.drivesales.util.MonthConverter;
import br.com.drivesales.util.MonthEnum;

/**
 *
 * @author thomas
 */
public class FilialPeriodoTotalProcess {
    private final DelimitersEnum delimited;
    private final HeaderTypes header;
    private Company company;
    
    private MonthConverter monthConverter = new MonthConverter();

    public FilialPeriodoTotalProcess(HeaderTypes header, DelimitersEnum delimited) {
        this.header = header;
        this.delimited = delimited;
        this.company = new Company();
    }
    
    public void parse(String line){
        String[] parsed = line.split(delimited.getValue(), -1);
        
        String location = parsed[0].trim();
        String month = parsed[1].trim();
        String total = parsed[2].trim();
        
        Branch branch = new Branch();
        branch.setName("");
        branch.setLocation(location);
        
        Sale sale = new Sale();
        MonthEnum currentMonth = monthConverter.getMonth(month);
        sale.setPeriod(monthConverter.getMonthPeriodFromMonthEnum(currentMonth, null));
        sale.setTotal(MoneyHelper.convertFromBrazilian(total));
        
        
        branch.getSales().add(sale);
        this.company.getBranchs().add(branch);
    }
    
    public Company build(){
        return this.company;
    }
    
}
