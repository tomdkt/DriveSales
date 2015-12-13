/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Thomas Daniel Kaneko Teixeira(tomdkt)
 */
public class MonthTopSalles {
    private Date inicialDate;
    private BigDecimal totalMonth;

    public MonthTopSalles(Date inicialDate, BigDecimal totalMonth) {
        this.inicialDate = inicialDate;
        this.totalMonth = totalMonth;
    }

    public Date getInicialDate() {
        return inicialDate;
    }

    public void setInicialDate(Date inicialDate) {
        this.inicialDate = inicialDate;
    }

    public BigDecimal getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(BigDecimal totalMonth) {
        this.totalMonth = totalMonth;
    }
}
