/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.repository;

import br.com.drivesales.domain.MonthPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author thomas
 */
public interface MonthPeriodRepository extends JpaRepository<MonthPeriod, Long>{
    
}