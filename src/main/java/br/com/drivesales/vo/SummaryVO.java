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
    private String monthTopSalles;
    private LocationAndTotalDTO branchMoreIncrease;
    private LocationAndTotalDTO branchLessIncrease;

    public LocationAndTotalDTO getBranchMostSold() {
        return branchMostSold;
    }

    public String getMonthTopSalles() {
        return monthTopSalles;
    }

    public void setMonthTopSalles(String monthTopSalles) {
        this.monthTopSalles = monthTopSalles;
    }

    public void setBranchMostSold(LocationAndTotalDTO branchMostSold) {
        this.branchMostSold = branchMostSold;
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
}
