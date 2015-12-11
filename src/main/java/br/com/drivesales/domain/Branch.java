/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.domain;

import br.com.drivesales.parsable.Location;
import br.com.drivesales.parsable.Parsable;
import br.com.drivesales.parsable.Position;
import br.com.drivesales.parsable.types.FilialPeriodoTotal;
import br.com.drivesales.parsable.types.POSITION;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thomas
 */
@Entity
@Table
public class Branch implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "BRANCH_ID", nullable = false)
    private Long id;
    
    private String name;
    
    @Column(name = "LOCATION", nullable = false)
    private String location;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="SALE_ID")
    private List<Sale> sales;

    public Branch() {
        this.sales = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
