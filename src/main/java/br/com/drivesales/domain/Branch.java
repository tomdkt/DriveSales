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

/**
 *
 * @author thomas
 */
public class Branch implements Serializable {
    private String name;
    private String location;
    private List<Sale> sales;

    public Branch() {
        this.sales = new ArrayList<>();
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
