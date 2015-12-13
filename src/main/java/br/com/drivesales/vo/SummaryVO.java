/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.vo;

import br.com.drivesales.dto.LocationAndTotalDTO;
import br.com.drivesales.dto.MonthTopSalles;
import br.com.drivesales.util.DateParser;

/**
 *
 * @author Thomas Daniel Kaneko Teixeira(tomdkt)
 */
public class SummaryVO {
    private LocationAndTotalDTO branchMostSold;
    private MonthTopSalles monthTopSalles;
    private LocationAndTotalDTO branchMoreIncrease;
    private LocationAndTotalDTO branchLessIncrease;

    public LocationAndTotalDTO getBranchMostSold() {
        return branchMostSold;
    }

    public void setBranchMostSold(LocationAndTotalDTO branchMostSold) {
        this.branchMostSold = branchMostSold;
    }

    public MonthTopSalles getTopSalles() {
        return monthTopSalles;
    }

    public void setTopSalles(MonthTopSalles topSalles) {
        this.monthTopSalles = topSalles;
    }

    public LocationAndTotalDTO getBranchMoreIncrease() {
        return branchMoreIncrease;
    }

    public void setBranchMoreIncrease(LocationAndTotalDTO branchMoreIncrease) {
        this.branchMoreIncrease = branchMoreIncrease;
    }

    public LocationAndTotalDTO getBranchLessIncrease() {
        return branchLessIncrease;
    }

    public void setBranchLessIncrease(LocationAndTotalDTO branchLessIncrease) {
        this.branchLessIncrease = branchLessIncrease;
    }
    
    //TODO remove toString
    @Override
    public String toString() {
        return 
                "-Filial que mais vendeu no periodo: <b>" + branchMostSold.getLocation() + "</b><br /> " +
                "-Filial que teve o maior crescimento: <b>" + branchMoreIncrease.getLocation()+ "</b><br /> " +
                "-Filial que teve a maior queda nas vendas: <b>" + branchLessIncrease.getLocation()+ "</b><br /> " +
                "-Mês em que a empresa mais vendeu: <b>" + DateParser.getMonthNameFromBrazil(monthTopSalles.getInicialDate())+ "</b><br /> ";
    }
    
    
}
