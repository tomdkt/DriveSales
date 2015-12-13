/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.dto;

import java.math.BigDecimal;

/**
 *
 * @author Thomas Daniel Kaneko Teixeira(tomdkt)
 */
public class LocationAndTotalDTO {
    private String location;
    private BigDecimal total;

    public LocationAndTotalDTO(String location, BigDecimal total) {
        this.location = location;
        this.total = total;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
