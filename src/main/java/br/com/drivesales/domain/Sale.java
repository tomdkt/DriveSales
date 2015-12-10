/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author thomas
 */
public class Sale implements Serializable {
    private MonthPeriod period;
    private BigDecimal total;

    public MonthPeriod getPeriod() {
        return period;
    }

    public void setPeriod(MonthPeriod period) {
        this.period = period;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
