/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.parsable.template;

import br.com.drivesales.parsable.Position;
import br.com.drivesales.parsable.types.FilialPeriodoTotal;
import br.com.drivesales.parsable.types.POSITION;

/**
 *
 * @author thomas
 */
@FilialPeriodoTotal
public class FilialPeriodoTotalTemplate {
    
    @Position(startIn = POSITION.ZERO)
    private String filial;
    
    @Position(startIn = POSITION._1)
    private String periodo;
    
    @Position(startIn = POSITION._2, isDecimal = true)
    private String total;

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
}
