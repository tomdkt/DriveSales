/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.service;

/**
 *
 * @author Thomas Daniel Kaneko Teixeira(tomdkt)
 */
public interface DelimitedProcess<T> {

    T build();
    void parse(String line) throws InstantiationException, IllegalAccessException;
    
}
