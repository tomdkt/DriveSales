/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.service;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.domain.Company;
import br.com.drivesales.domain.MonthPeriod;
import br.com.drivesales.domain.Sale;
import br.com.drivesales.parsable.template.FilialPeriodoTotalTemplate;
import br.com.drivesales.reflection.ParseReflectionService;
import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.util.HeaderTypes;
import br.com.drivesales.util.MoneyHelper;
import br.com.drivesales.util.MonthConverter;
import br.com.drivesales.util.MonthEnum;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author thomas
 */
public class FilialPeriodoTotalProcess {
    private final DelimitersEnum delimited;
    private final HeaderTypes header;
    private Company company;
    
    private MonthConverter monthConverter = new MonthConverter();
    private ParseReflectionService parseReflectionService = new ParseReflectionService();

    public FilialPeriodoTotalProcess(HeaderTypes header, DelimitersEnum delimited) {
        this.header = header;
        this.delimited = delimited;
        this.company = new Company();
    }
    
    public void parse(String line) throws InstantiationException, IllegalAccessException{
        if(line == null || line.isEmpty()){
            return;
        }
        String[] parsed = line.split(delimited.getValue(), -1);
        
        FilialPeriodoTotalTemplate template = parseReflectionService.getEntity(parsed, header);
        
        String location = template.getFilial();
        String month = template.getPeriodo();
        String total = template.getTotal();
        
        
        Branch branch = new Branch();
        branch.setName("");
        branch.setLocation(location);
        branch = getBranch(branch, company.getBranchs());
        if(branch == null){
            
        }
        
        Sale sale = new Sale();
        MonthPeriod currentMonth = monthConverter.getMonthPeriodFromMonthEnum(monthConverter.getMonth(month), null);
        sale.setInicialDate(currentMonth.getInicialDate());
        sale.setFinalDate(currentMonth.getFinalDate());
        sale.setTotal(MoneyHelper.convertFromBrazilian(total));
        branch.getSales().add(sale);
        
        this.company.getBranchs().add(branch);
    }
    
    public Company build(){
        return this.company;
    }
    
    private Branch getBranch(Branch branch, Set<Branch> set){
        if(!set.contains(branch)){
            return branch;
        }
        for (Branch b : set) {
            if(b.equals(branch)){
                Branch temp = b;
                set.remove(b);
                return temp;
            }
        }
        return branch;
    }
    
}
