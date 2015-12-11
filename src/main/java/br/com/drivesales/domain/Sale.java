/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author thomas
 */
@Entity
@Table
public class Sale implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "SALE_ID", nullable = false)
    private Long id;
    
    @OneToOne
    private MonthPeriod monthPeriod;
    
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonthPeriod getMonthPeriod() {
        return monthPeriod;
    }

    public void setMonthPeriod(MonthPeriod monthPeriod) {
        this.monthPeriod = monthPeriod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
