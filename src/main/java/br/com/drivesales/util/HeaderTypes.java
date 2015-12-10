/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import br.com.drivesales.parsable.types.FilialPeriodoTotal;

/**
 *
 * @author thomas
 */
public enum HeaderTypes {
    FILIAL_PERIODO_TOTAL(FilialPeriodoTotal.class),
    GENERIC(Object.class);
    
    private final Class value;
    
    HeaderTypes(Class clazz){
        this.value = clazz;
    }

    public Class getValue() {
        return value;
    }
}
