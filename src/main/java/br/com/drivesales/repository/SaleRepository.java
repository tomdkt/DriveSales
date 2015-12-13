/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.repository;

import br.com.drivesales.domain.Sale;
import br.com.drivesales.dto.MonthTopSalles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author thomas
 */
public interface SaleRepository extends JpaRepository<Sale, Long>{
    @Query("SELECT new br.com.drivesales.dto.MonthTopSalles(inicialDate, sum(total) as totalMonth) FROM Sale s group by s.inicialDate order by totalMonth desc")
    List<MonthTopSalles> getMonthWithMoreSalesDTO();
    
}
